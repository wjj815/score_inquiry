package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.dao.*;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.dto.CourseScoreDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.*;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName : CourseScoreService
 * @Author : wangJJ
 * @Date : 2020/2/17 9:36
 * @Description : TODO
 */
@Service
public class CourseScoreService {

	@Autowired
	private CourseScoreRepository courseScoreRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
    private ExamRepository examRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;
	/**
	 * 保存成绩
	 * @param courseScoreDTO
	 */
	@Transactional
	void saveCourseScore(CourseScoreDTO courseScoreDTO){

		if(!courseRepository.existsById(courseScoreDTO.getCourseId())) {
			throw new GlobalException("课程不存在");
		}
		if(!examRepository.existsById(courseScoreDTO.getExamId())){
			throw new GlobalException("考试不存在");
		}
		if(!studentRepository.existsById(courseScoreDTO.getStudentId())) {
			throw new GlobalException("学生不存在");
		}

		if(!teacherRepository.existsById(courseScoreDTO.getTeacherId())) {
			throw new GlobalException("老师不存在");
		}

		CourseScore courseScore;
		/*如果成绩不存在，则保存成绩记录
		* 如果成绩记录存在，则更新成绩*/
		if(Objects.nonNull(courseScoreDTO.getId())&& courseScoreRepository.existsById(courseScoreDTO.getId())) {
			courseScore = courseScoreRepository.getOne(courseScoreDTO.getId());
		} else {
			courseScore = new CourseScore();
			Course course = courseRepository.getOne(courseScoreDTO.getCourseId());
			Exam exam = examRepository.getOne(courseScoreDTO.getExamId());
			Student student = studentRepository.getOne(courseScoreDTO.getStudentId());
			Teacher teacher = teacherRepository.getOne(courseScoreDTO.getTeacherId());
			courseScore.setCourse(course);
			courseScore.setExam(exam);
			courseScore.setStudent(student);
			courseScore.setTeacher(teacher);
		}

		courseScore.setScore(courseScoreDTO.getScore());

		courseScoreRepository.save(courseScore);

	}

	List<CourseScoreDTO> getCourseScoreList(CourseScoreDTO courseScoreDTO){

		List<CourseScore> courseScores = courseScoreRepository.findAll(getCourseScoreSpecification(courseScoreDTO));
		List<CourseScoreDTO> convert = PropertyUtils.convert(courseScores, u -> {
			CourseScoreDTO dto = new CourseScoreDTO();
			PropertyUtils.copyNoNullProperties(u, dto);
			dto.setStudentId(u.getStudent().getId());
			dto.setCourseId(u.getCourse().getId());
			dto.setExamId(u.getExam().getId());
			dto.setTeacherId(u.getTeacher().getId());
			dto.setCourseName(u.getCourse().getCourseName());
			return dto;
		});

		return convert;
	}

	private Specification<CourseScore> getCourseScoreSpecification(CourseScoreDTO courseScoreDTO) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if(Objects.nonNull(courseScoreDTO.getCourseId())) {
				predicates.add(criteriaBuilder.equal(root.get("course").get("id"),courseScoreDTO.getCourseId()));
			}

			if(Objects.nonNull(courseScoreDTO.getExamId())) {
				predicates.add(criteriaBuilder.equal(root.get("exam").get("id"),courseScoreDTO.getExamId()));
			}

			if(Objects.nonNull(courseScoreDTO.getStudentId())) {
				predicates.add(criteriaBuilder.equal(root.get("student").get("id"),courseScoreDTO.getStudentId()));
			}

			if(Objects.nonNull(courseScoreDTO.getTeacherId())) {
				predicates.add(criteriaBuilder.equal(root.get("teacher").get("id"),courseScoreDTO.getTeacherId()));
			}



			query.where(predicates.toArray(new Predicate[predicates.size()]));
			return null;};
	}
}
