package com.wangjj.scoreinquirywxback.service;

import com.alibaba.excel.EasyExcel;
import com.wangjj.scoreinquirywxback.dao.ClazzRepository;
import com.wangjj.scoreinquirywxback.dao.GradeRepository;
import com.wangjj.scoreinquirywxback.dao.ParentRepository;
import com.wangjj.scoreinquirywxback.dao.StudentRepository;
import com.wangjj.scoreinquirywxback.excel.StudentDataListener;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.dto.ParentDTO;
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
	public void removeRelevanceOfStudentAndParent(Long studentId, Long parentId) {

		check(studentId, parentId);
		Student student = studentRepository.getOne(studentId);
		Parent parent = parentRepository.getOne(parentId);
//		student.getParents().remove(parent);
		parent.getStudents().remove(student);
	}

	private void check(Long studentId, Long parentId) {
		if (!studentRepository.existsById(studentId)) {
			throw new GlobalException(String.format("不存在学号为%s的学生", studentId));
		}
		if (!parentRepository.existsById(parentId)) {
			throw new GlobalException(String.format("不存在该家长信息！"));
		}
	}

	@Transactional
	public void saveRelevanceOfStudentAndParent(Long studentId, Long parentId) {

		check(studentId, parentId);
		Student student = studentRepository.getOne(studentId);
		Parent parent = parentRepository.getOne(parentId);
		parent.getStudents().add(student);
//		parentRepository.save(parent);
		/*Parent savedParent = parentRepository.save(parent);
		if(Objects.isNull(id)) {
			log.info("添加学生和家长的关联信息");
			StudentParent sp = StudentParent.builder()
					.studentId(studentId)
					.parentId(savedParent.getId()).build();
			studentParentRepository.save(sp);
		}*/
	}

	@Transactional
	public void saveParent(ParentDTO parentDTO) {

		Parent parent;
		if(Objects.nonNull(parentDTO.getId()) && parentRepository.existsById(parentDTO.getId())) {
			parent = parentRepository.getOne(parentDTO.getId());
		} else {
			parent = new Parent();
		}
		PropertyUtils.copyNoNullProperties(parentDTO,parent);
		parentRepository.save(parent);

		/*Parent savedParent = parentRepository.save(parent);
		if(Objects.isNull(id)) {
			log.info("添加学生和家长的关联信息");
			StudentParent sp = StudentParent.builder()
					.studentId(studentId)
					.parentId(savedParent.getId()).build();
			studentParentRepository.save(sp);
		}*/
	}

	public void saveStudent(StudentDTO studentDTO) {
		if(!gradeRepository.existsById(studentDTO.getGradeId())) {
			throw new GlobalException(String.format("不存在gradeId为%s的年级",studentDTO.getGradeId()));
		}
		if(!clazzRepository.existsById(studentDTO.getClazzId())) {
			throw new GlobalException(String.format("不存在clazzId为%s的班级",studentDTO.getGradeId()));
		}
		Grade grade = gradeRepository.getOne(studentDTO.getGradeId());
		Clazz clazz = clazzRepository.getOne(studentDTO.getClazzId());

		Student student = studentRepository.existsById(studentDTO.getId()) ?
				studentRepository.getOne(studentDTO.getId()) : new Student();

		PropertyUtils.copyNoNullProperties(studentDTO,student);
		student.setClazz(clazz);
		student.setGrade(grade);

		studentRepository.save(student);
	}

	@Transactional
	public void deleteStudent(Long studentId) {
		if(!studentRepository.existsById(studentId)) {
			throw new GlobalException("该学生不存在");
		}
		Student student = studentRepository.getOne(studentId);
		/*删除所有的该同学的家长关联数据 */
		student.setParents(new ArrayList<>());
		studentRepository.deleteById(studentId);
	}

	public void deleteParent(Long parentId) {
		if(!parentRepository.existsById(parentId)) {
			throw new GlobalException("该家长不存在");
		}
		Parent parent = parentRepository.getOne(parentId);
		parent.getStudents().forEach(e->e.getParents().remove(parent));
		parentRepository.deleteById(parentId);
	}


	public List<StudentDTO> findStudent(StudentDTO studentDTO) {
		List<Student> students = studentRepository.findAll(getStudentSpecification(studentDTO));
		return getStudentDTO(students);
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

			if (!CollectionUtils.isEmpty(predicates)) {
				query.where(predicates.toArray(new Predicate[predicates.size()]));
			}
			query.orderBy(criteriaBuilder.desc(root.get("grade").get("id")),
					criteriaBuilder.asc(root.get("clazz").get("id")));
			return null;
		};
	}

	public PageResult<StudentDTO> findStudent(StudentDTO studentDTO, Pageable pageable) {
		PageResult<StudentDTO> pageResult = new PageResult<>();

		Page<Student> students = studentRepository.findAll(getStudentSpecification(studentDTO), pageable);
		List<StudentDTO> studentDTO1 = getStudentDTO(students.getContent());
		PropertyUtils.copyNoNullProperties(students,pageResult);
		pageResult.setContent(studentDTO1);

		return  pageResult;
	}

	/**
	 * 转实体对象为数据传输对象
	 * @param students
	 * @return
	 */
	private List<StudentDTO> getStudentDTO(List<Student> students) {

		List<StudentDTO> convert = PropertyUtils.convert(students, u -> {
			StudentDTO s = new StudentDTO();
			PropertyUtils.copyNoNullProperties(u, s);
			s.setGradeId(u.getGrade().getId());
			s.setClazzId(u.getClazz().getId());
			return s;
		});
		return convert;
	}

	public Student findStudentById(Long id) {
		return studentRepository.getOne(id);
	}


	public String importStudentList(InputStream inputStream) {
		log.info("导入学生信息excel");
		StudentDataListener studentDataListener;
		 EasyExcel.read(inputStream, Student.class,
				 studentDataListener = new StudentDataListener(studentRepository))
				.sheet().doRead();
		return studentDataListener.getResult();
	}

	public List<ParentDTO> findParentOfStudent(Long studentId) {
		if(!studentRepository.existsById(studentId)) {
			throw new GlobalException(String.format("学号为%s的学生不存在",studentId));
		}
		Student student = studentRepository.getOne(studentId);
		List<Parent> parents = student.getParents();

		return PropertyUtils.convert(parents, parent -> {
			ParentDTO parentDTO = new ParentDTO();
			PropertyUtils.copyNoNullProperties(parent,parentDTO);
			return parentDTO;
		});
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

	public Parent findParentByStudentIdAndParentId(Long studentId, Long parentId) {
		return null;
	}

}
