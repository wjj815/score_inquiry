package com.wangjj.scoreinquirywxback.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangjj.scoreinquirywxback.dao.ExamRepository;
import com.wangjj.scoreinquirywxback.dao.GradeRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.ExamDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.pojo.entity.CourseScore;
import com.wangjj.scoreinquirywxback.pojo.entity.Exam;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
import com.wangjj.scoreinquirywxback.service.ExamService;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName : ExamServiceImpl
 * @Author : wangJJ
 * @Date : 2020/2/14 18:16
 * @Description : 考试业务的实现类
 */
@Slf4j
@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private GradeRepository gradeRepository;



	@Override
	public Page<Exam> getExamPage(Exam exam, Pageable pageable) {

		return null;
	}

	@Override
	public List<ExamDTO> getExamList(ExamDTO examDTO) {
		return null;
	}

	@Override
	public void saveExam(ExamDTO examDTO) {
		/*如果id不为空，则修改考试信息*/
		if(Objects.nonNull(examDTO.getId())) {
			Exam origin = examRepository.getOne(examDTO.getId());
			BeanUtils.copyProperties(examDTO,origin, PropertyUtils.getNullPropertyNames(examDTO));
			examRepository.save(origin);
		}
		/*如果id为空，则新增考试信息*/
		Grade grade = gradeRepository.getOne(examDTO.getGradeId());
		Exam exam = new Exam();
		BeanUtils.copyProperties(examDTO,exam, PropertyUtils.getNullPropertyNames(examDTO));
		exam.setGrade(grade);
		examRepository.save(exam);
	}

	@Override
	public void deleteExam(Long examId) {
		examRepository.deleteById(examId);
	}

	@Override
	public PageResult<ExamDTO> getExamScorePage(ExamDTO examDTO, Pageable pageable) {


		return null;
	}

	@Override
	public List<CourseScore> getExamScoreList(CourseScore courseScore) {
		return null;
	}

	@Override
	public void saveExamScore(CourseScore courseScore) {

	}

	@Override
	public void deleteExamScore(Long id) {

	}
}
