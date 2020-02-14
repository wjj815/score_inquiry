package com.wangjj.scoreinquirywxback.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.wangjj.scoreinquirywxback.dao.ParentRepository;
import com.wangjj.scoreinquirywxback.dao.StudentParentRepository;
import com.wangjj.scoreinquirywxback.dao.StudentRepository;
import com.wangjj.scoreinquirywxback.entity.Clazz;
import com.wangjj.scoreinquirywxback.entity.Parent;
import com.wangjj.scoreinquirywxback.entity.Student;
import com.wangjj.scoreinquirywxback.entity.StudentParent;
import com.wangjj.scoreinquirywxback.excel.StudentDataListener;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.StudentService;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName : StudentServiceImpl
 * @Author : wangJJ
 * @Date : 2019/12/24 23:28
 * @Description : 学生的业务类
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentParentRepository studentParentRepository;
	@Autowired
	private ParentRepository parentRepository;

	@Transactional
	@Override
	public void saveStudentParent(Long studentId, Parent parent) {
		if (!studentRepository.existsById(studentId)) {
			throw new GlobalException(String.format("学号为%s的学生不存在！", studentId.toString()));
		}
		Long id  = parent.getId();
		log.info("保存家长信息");
		Parent savedParent = parentRepository.save(parent);

		if(Objects.isNull(id)) {
			log.info("添加学生和家长的关联信息");
			StudentParent sp = StudentParent.builder()
					.studentId(studentId)
					.parentId(savedParent.getId()).build();
			studentParentRepository.save(sp);
		}
	}

	@Override
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}


	@Transactional
	@Override
	public void deleteStudent(String ids) {
		List<Long> collect = ParameterUtils.analyse(ids);
		studentRepository.deleteByIdIn(collect);
	}



	@Override
	public List<Student> findStudent(Student student) {

		return studentRepository.findAll(getStudentSpecification(student));
	}

	/**
	 * 根据条件查询
	 * @param student 条件
	 * @return 结果
	 */
	private Specification<Student> getStudentSpecification(Student student) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (Objects.nonNull(student.getClazzId())) {
				predicates.add(criteriaBuilder.equal(root.get("clazzId"), student.getClazzId()));
			}

			if (Objects.nonNull(student.getGradeId())) {
				predicates.add(criteriaBuilder.equal(root.get("gradeId"), student.getGradeId()));
			}

			if (!CollectionUtils.isEmpty(predicates)) {
				query.where(predicates.toArray(new Predicate[predicates.size()]));
			}
			query.orderBy(criteriaBuilder.asc(root.get("gradeId")), criteriaBuilder.asc(root.get("clazzId")));
			return null;
		};
	}

	@Override
	public Page<Student> findStudent(Student student, Pageable pageable) {
		return studentRepository.findAll(getStudentSpecification(student),pageable);
	}

	@Override
	public Student findStudentById(Long id) {
		return studentRepository.getOne(id);
	}


	@Override
	public String importStudentList(InputStream inputStream) {
		log.info("导入学生信息excel");
		StudentDataListener studentDataListener;
		 EasyExcel.read(inputStream, Student.class,
				 studentDataListener = new StudentDataListener(studentRepository))
				.sheet().doRead();
		return studentDataListener.getResult();
	}


	@Override
	public List<Parent> findStudentParent(Long studentId) {
		List<StudentParent> studentParentList = studentParentRepository.findByStudentId(studentId);
		List<Long> ids = studentParentList.stream().map(StudentParent::getParentId).collect(Collectors.toList());
		return parentRepository.findAllById(ids);
	}

	@Override
	public Parent findParentByStudentIdAndParentId(Long studentId,Long parentId) {
		//如果存在学生家长则返回家长信息
		if(studentParentRepository.existsByStudentIdAndAndParentId(studentId,parentId)) {
			return parentRepository.getOne(parentId);
		}
		return Parent.builder().build();
	}

}
