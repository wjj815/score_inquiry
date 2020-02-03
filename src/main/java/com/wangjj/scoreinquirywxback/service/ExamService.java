package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.Exam;
import com.wangjj.scoreinquirywxback.entity.ExamScore;
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
	List<Exam> getExamList(Exam exam);


	/**
	 * 保存考试
	 * @param exam
	 */
	void saveExam(Exam exam);


	/**
	 * 删除考试
	 */
	void deleteExam(Long id);


	/**
	 * 分页获取考试成绩
	 * @param examScore
	 * @param pageable
	 * @return
	 */
	Page<ExamScore> getExamScorePage(ExamScore examScore, Pageable pageable);

	/**
	 * 获取考试成绩列表
	 * @param examScore
	 * @return
	 */
	List<ExamScore> getExamScoreList(ExamScore examScore);

	/**
	 * 保存成绩
	 * @param examScore
	 */
	void saveExamScore(ExamScore examScore);

	/**
	 * 删除考试成绩
	 * @param id
	 */
	void deleteExamScore(Long id);

}
