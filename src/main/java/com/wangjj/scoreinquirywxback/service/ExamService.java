package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.ExamDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.CourseScore;
import com.wangjj.scoreinquirywxback.pojo.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ClassName : ExamService
 * @Author : wangJJ
 * @Date : 2019/12/25 17:16
 * @Description : 考试业务
 */
public interface ExamService {

	/**
	 * 分页获取考试信息
	 * @param pageable
	 * @return
	 */
	Page<Exam> getExamPage(Exam exam,Pageable pageable);

	/**
	 * 获取考试信息列表
	 * @return
	 */
	List<ExamDTO> getExamList(ExamDTO examDTO);


	/**
	 * 保存考试
	 * @param examDTO
	 */
	void saveExam(ExamDTO examDTO);


	/**
	 * 删除考试
	 */
	void deleteExam(Long id);


	/**
	 * 分页获取考试成绩
	 * @param examDTO
	 * @param pageable
	 * @return
	 */
	PageResult<ExamDTO> getExamScorePage(ExamDTO examDTO, Pageable pageable);

	/**
	 * 获取考试成绩列表
	 * @param courseScore
	 * @return
	 */
	List<CourseScore> getExamScoreList(CourseScore courseScore);

	/**
	 * 保存成绩
	 * @param courseScore
	 */
	void saveExamScore(CourseScore courseScore);

	/**
	 * 删除考试成绩
	 * @param id
	 */
	void deleteExamScore(Long id);

}
