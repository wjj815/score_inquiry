package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.Exam;

import java.awt.print.Pageable;
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
	List<Exam> getExamList(Pageable pageable);


	/**
	 * 添加考试
	 * @param exam
	 */
	void addExam(Exam exam);


	/**
	 * 删除考试
	 */
	void deleteExam(Long id);

	/**
	 * 学生考试列表
	 */
	void studentExamList();
}
