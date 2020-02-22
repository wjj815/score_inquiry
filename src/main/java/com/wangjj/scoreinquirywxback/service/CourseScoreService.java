package com.wangjj.scoreinquirywxback.service;

import com.alibaba.excel.EasyExcel;
import com.wangjj.scoreinquirywxback.dao.*;
import com.wangjj.scoreinquirywxback.excel.ScoreDataListener;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.bo.StudentCourse;
import com.wangjj.scoreinquirywxback.pojo.dto.CourseScoreDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.StudentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.StudentScoreDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.*;
import com.wangjj.scoreinquirywxback.pojo.excel.ScoreExcel;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

	@Autowired
	private ClazzRepository clazzRepository;

	@Autowired
	private StudentService studentService;
	/**
	 * 保存成绩
	 * @param courseScoreDTO
	 */
	@Transactional
	void saveCourseScore(CourseScoreDTO courseScoreDTO){

		check(courseScoreDTO);
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
			courseScore.setId(getId(exam.getId(),student.getId(),course.getId()));
			courseScore.setCourse(course);
			courseScore.setExam(exam);
			courseScore.setStudent(student);
		}

		courseScore.setScore(courseScoreDTO.getScore());

		courseScoreRepository.save(courseScore);

	}

	public Long getId(Long examId, Long studentId,Long courseId) {
		StringBuilder id = new StringBuilder();
		id.append(examId).append(studentId).append(courseId);
		return Long.parseLong(id.toString());
	}

	List<CourseScoreDTO> getCourseScoreList(CourseScoreDTO courseScoreDTO){

		List<CourseScore> courseScores = courseScoreRepository.findAll(getCourseScoreSpecification(courseScoreDTO));
		return PropertyUtils.convert(courseScores,this::getCourseScoreDTO);
	}

	private CourseScoreDTO getCourseScoreDTO(CourseScore u) {
		CourseScoreDTO dto = new CourseScoreDTO();
		PropertyUtils.copyNoNullProperties(u, dto);
		dto.setStudentId(u.getStudent().getId());
		dto.setCourseId(u.getCourse().getId());
		dto.setExamId(u.getExam().getId());
		/*dto.setTeacherId(u.getTeacher().getId());*/
		dto.setCourseName(u.getCourse().getCourseName());
		return dto;
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

			if(Objects.nonNull(courseScoreDTO.getClazzId())) {
				predicates.add(criteriaBuilder.equal(root.join("student").get("clazz").get("id"),courseScoreDTO.getClazzId()));
			}

			if(Objects.nonNull(courseScoreDTO.getStudentId())) {
				predicates.add(criteriaBuilder.equal(root.get("student").get("id"),courseScoreDTO.getStudentId()));
			}

			if(Objects.nonNull(courseScoreDTO.getTeacherId())) {
				predicates.add(criteriaBuilder.equal(root.join("course").joinSet("teachers").get("id"),courseScoreDTO.getTeacherId()));
			}
			query.where(predicates.toArray(new Predicate[predicates.size()]));
			return null;};
	}

	/**
	 * 通过考试id或者考试id和班级id查询学生信息
	 * @param courseScore
	 * @return
	 */
	public List<StudentScoreDTO> getStudentScoreDTOList(CourseScoreDTO courseScore) {

		if(!examRepository.existsById(courseScore.getExamId())) {
			throw new GlobalException("考试不存在");
		}
		Exam  exam = examRepository.getOne(courseScore.getExamId());
		Set<Student> students;
		//如果班级id不为空
		if(Objects.nonNull(courseScore.getClazzId())) {
			if(!clazzRepository.existsById(courseScore.getClazzId())) {
				throw new GlobalException("班级不存在");
			}
			Clazz clazz = clazzRepository.getOne(courseScore.getClazzId());
			students = clazz.getStudents();
		} else {
			students = exam.getGrade().getStudents();
		}
		List<StudentScoreDTO> studentScoreDTOS = new ArrayList<>();
		students.forEach(e->{
			StudentScoreDTO studentScoreDTO = new StudentScoreDTO();

			//-------设置学生信息
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setId(e.getId());
			studentDTO.setStudentName(e.getStudentName());
			studentDTO.setClazzId(e.getClazz().getId());
			studentDTO.setGradeId(e.getGrade().getId());
			studentScoreDTO.setStudentDTO(studentDTO);

			//----设置学生的成绩
			CourseScoreDTO courseScoreDTO = new CourseScoreDTO();
			courseScoreDTO.setExamId(courseScore.getExamId());
			courseScoreDTO.setStudentId(e.getId());
			/*总分*/
			AtomicInteger totalScore = new AtomicInteger();
			List<CourseScore> courseScores = courseScoreRepository.findAll(getCourseScoreSpecification(courseScoreDTO));
			List<CourseScoreDTO> courseScoreDTOS = PropertyUtils.convert(courseScores, cs -> {
				CourseScoreDTO courseScoreDTO1 = new CourseScoreDTO();
				courseScoreDTO1.setCourseId(cs.getCourse().getId());
				Integer score = Objects.nonNull(cs.getScore())?cs.getScore() : 0;
				totalScore.addAndGet(score);
				courseScoreDTO1.setScore(cs.getScore());
				return courseScoreDTO1;
			});

			studentScoreDTO.setTotalScore(totalScore.get());
			studentScoreDTO.setScoreDTOList(courseScoreDTOS);
			//----
			studentScoreDTOS.add(studentScoreDTO);

		});
		/*排名*/
		studentScoreDTOS.sort((o1, o2) -> o2.getTotalScore() - o1.getTotalScore());
		return studentScoreDTOS;
	}

	public void importStudentScore(InputStream inputStream,CourseScoreDTO courseScoreDTO) {
		check(courseScoreDTO);
		StudentCourse studentCourse = new StudentCourse();
		Exam exam = examRepository.getOne(courseScoreDTO.getExamId());
		Course course = courseRepository.getOne(courseScoreDTO.getCourseId());
		List<Student> students= studentRepository.findAllByClazzId(courseScoreDTO.getClazzId());
		Map<Long, Student> collect = students.stream().collect(Collectors.toMap(Student::getId, student -> student));
		studentCourse.setCourse(course);
		studentCourse.setExam(exam);
		studentCourse.setStudentMap(collect);
		EasyExcel.read(inputStream, CourseScore.class,new ScoreDataListener(courseScoreRepository,studentCourse))
				.sheet().doRead();
	}

	private void check(CourseScoreDTO courseScoreDTO) {
		if(!examRepository.existsById(courseScoreDTO.getExamId())) {
			throw new GlobalException("考试不存在");
		}
		if(!courseRepository.existsById(courseScoreDTO.getCourseId())) {
			throw new GlobalException("课程不存在");
		}
	/*	if(!clazzRepository.existsById(courseScoreDTO.getClazzId())) {
			throw new GlobalException("班级不存在");
		}*/
	}

	public void exportTemplate(OutputStream outputStream,CourseScoreDTO courseScoreDTO) {
		check(courseScoreDTO);
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setClazzId(courseScoreDTO.getClazzId());
		List<StudentDTO> studentList = studentService.findStudentList(studentDTO);
		Course course = courseRepository.getOne(courseScoreDTO.getCourseId());
		List<ScoreExcel> scoreExcels =new ArrayList<>();
		studentList.forEach(e -> {
			ScoreExcel scoreExcel = new ScoreExcel();
			scoreExcel.setCourseName(course.getCourseName());
			scoreExcel.setStudentId(e.getId());
			scoreExcel.setStudentName(e.getStudentName());
			scoreExcels.add(scoreExcel);
		});
		EasyExcel.write(outputStream,ScoreExcel.class).sheet().doWrite(scoreExcels);
	}
}
