package com.wangjj.scoreinquirywxback.service.impl;
import	java.util.ArrayList;

import com.wangjj.scoreinquirywxback.dao.*;
import com.wangjj.scoreinquirywxback.entity.*;
import com.wangjj.scoreinquirywxback.service.ExamService;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName : ExamServiceImpl
 * @Author : wangJJ
 * @Date : 2020/2/12 15:06
 * @Description : TODO
 */
@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private ExamScoreRepository examScoreRepository;

	@Autowired
	private GradeCourseRepository gradeCourseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Page<Exam> getExamPage(Exam exam, Pageable pageable) {
		return examRepository.findAll(Example.of(exam),pageable);
	}

	@Override
	public List<Exam> getExamList(Exam exam) {
		return examRepository.findAll(Example.of(exam));
	}

	@Transactional
	@Override
	public void saveExam(Exam exam) {

		if(Objects.equals(exam.getExamType(),Exam.GRADE_EXAM_TYPE)){
			List<Student> studentList = studentRepository.findByClazzId(exam.getClazzId());
			List<ExamScore> examScoreList = new ArrayList<>();
			List<GradeCourse> gradeCourseList = gradeCourseRepository.findByGradeId(exam.getGradeId());

			for(Student student:studentList) {
				for(GradeCourse gradeCourse : gradeCourseList){
					examScoreList.add(ExamScore.builder().courseId(gradeCourse.getCourseId())
							.studentId(student.getId())
							.examId(exam.getId())
							.build());
				}
				examScoreRepository.saveAll(examScoreList);
				examScoreList.clear();
			}
		}
		examRepository.save(exam);
	}

	@Transactional
	@Override
	public void deleteExam(String ids) {
		List<Long> analyse = ParameterUtils.analyse(ids);
		//删除该次考试的成绩
		examScoreRepository.deleteByExamIdIn(analyse);
		//删除该次考试信息
		examRepository.deleteByIdIn(analyse);
	}

}
