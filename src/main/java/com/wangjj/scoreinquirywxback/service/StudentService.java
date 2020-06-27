package com.wangjj.scoreinquirywxback.service;

import com.alibaba.excel.EasyExcel;
import com.wangjj.scoreinquirywxback.dao.ClazzRepository;
import com.wangjj.scoreinquirywxback.dao.GradeRepository;
import com.wangjj.scoreinquirywxback.dao.ParentRepository;
import com.wangjj.scoreinquirywxback.dao.StudentRepository;
import com.wangjj.scoreinquirywxback.excel.StudentDataListener;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.bo.GradeAndClazz;
import com.wangjj.scoreinquirywxback.pojo.dto.StudentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Clazz;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
import com.wangjj.scoreinquirywxback.pojo.entity.Parent;
import com.wangjj.scoreinquirywxback.pojo.entity.Student;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName : StudentServiceImpl
 * @Author : wangJJ
 * @Date : 2019/12/24 23:28
 * @Description : 学生的业务类
 */
@Slf4j
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ClazzRepository clazzRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private ParentRepository parentRepository;

	@Transactional
	public void saveStudent(StudentDTO studentDTO) {
		check(studentDTO);
		Grade grade = gradeRepository.getOne(studentDTO.getGradeId());
		Clazz clazz = clazzRepository.getOne(studentDTO.getClazzId());

		Student student = studentRepository.existsById(studentDTO.getId()) ?
				studentRepository.getOne(studentDTO.getId()) : new Student();

		PropertyUtils.copyNoNullProperties(studentDTO,student);
		student.setClazz(clazz);
		student.setGrade(grade);

		studentRepository.save(student);
	}

	private void check(StudentDTO studentDTO) {
		if(!gradeRepository.existsById(studentDTO.getGradeId())) {
			throw new GlobalException(String.format("不存在gradeId为%s的年级",studentDTO.getGradeId()));
		}
		if(!clazzRepository.existsById(studentDTO.getClazzId())) {
			throw new GlobalException(String.format("不存在clazzId为%s的班级",studentDTO.getGradeId()));
		}
	}

	@Transactional
	public void deleteStudent(Long studentId) {
		if(!studentRepository.existsById(studentId)) {
			throw new GlobalException("该学生不存在");
		}
		Student student = studentRepository.getOne(studentId);
		/*删除所有的该同学的家长关联数据 */
		student.getParents().forEach(p -> {
			p.getStudents().remove(student);
		});
		studentRepository.deleteById(studentId);
	}



	public List<StudentDTO> findStudentList(StudentDTO studentDTO) {
		List<Student> students = studentRepository.findAll(getStudentSpecification(studentDTO));
		return PropertyUtils.convert(students,this::getStudentDTO);
	}

	/**
	 * 根据条件查询
	 * @param student 条件
	 * @return 结果
	 */
	private Specification<Student> getStudentSpecification(StudentDTO student) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if(Objects.nonNull(student.getId())) {
				predicates.add(criteriaBuilder.equal(root.get("id"),student.getId()));
			}

			if(Objects.nonNull(student.getStudentName())) {
				predicates.add(criteriaBuilder.like(root.get("studentName"),student.getStudentName()+"%"));
			}

			if (Objects.nonNull(student.getClazzId())) {
				predicates.add(criteriaBuilder.equal(root.get("clazz").get("id"), student.getClazzId()));
			}

			if (Objects.nonNull(student.getGradeId())) {
				predicates.add(criteriaBuilder.equal(root.get("grade").get("id"), student.getGradeId()));
			}

			if(Objects.nonNull(student.getParentId())) {
				predicates.add(criteriaBuilder.equal(root.joinList("parents").get("id"),student.getParentId()));
			}

			if (!CollectionUtils.isEmpty(predicates)) {
				query.where(predicates.toArray(new Predicate[predicates.size()]));
			}
			query.orderBy(criteriaBuilder.desc(root.get("grade").get("id")),
					criteriaBuilder.asc(root.get("clazz").get("id")),criteriaBuilder.asc(root.get("id")));
			return null;
		};
	}

	public PageResult<StudentDTO> findStudentPage(StudentDTO studentDTO, Pageable pageable) {
		Page<Student> students = studentRepository.findAll(getStudentSpecification(studentDTO), pageable);
		return  PropertyUtils.convert(students,this::getStudentDTO);
	}

	/**
	 * 转实体对象为数据传输对象
	 * @param students
	 * @return
	 */
	private StudentDTO getStudentDTO(Student students) {
		StudentDTO studentDTO = new StudentDTO();
		PropertyUtils.copyNoNullProperties(students,studentDTO);
		studentDTO.setClazzId(students.getClazz().getId());
		studentDTO.setGradeId(students.getGrade().getId());
		return studentDTO;
	}

	public StudentDTO findStudentById(Long id) {
		if(!studentRepository.existsById(id)) {
			throw new GlobalException("学生不存在");
		}
		Student student = studentRepository.getOne(id);

		return getStudentDTO(student);
	}


	public String importStudentList(InputStream inputStream, StudentDTO studentDTO) {
		check(studentDTO);
		Grade grade = gradeRepository.getOne(studentDTO.getGradeId());
		Clazz clazz = clazzRepository.getOne(studentDTO.getClazzId());
		GradeAndClazz gradeAndClazz = new GradeAndClazz();
		gradeAndClazz.setGrade(grade);
		gradeAndClazz.setClazz(clazz);
		log.info("导入学生信息excel");
		StudentDataListener studentDataListener;
		 EasyExcel.read(inputStream, Student.class,
				 studentDataListener = new StudentDataListener(studentRepository,gradeAndClazz))
				.sheet().doRead();
		return studentDataListener.getResult();
	}



	public List<StudentDTO> findStudentOfParent(Long parentId) {
		if(!parentRepository.existsById(parentId)) {
			throw new GlobalException("家长不存在");
		}
		Parent parent = parentRepository.getOne(parentId);
		Set<Student> students = parent.getStudents();

		return PropertyUtils.convert(students, student -> {
			StudentDTO studentDTO = new StudentDTO();
			PropertyUtils.copyNoNullProperties(student,studentDTO);
			return studentDTO;
		});
	}

}
