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
	void deleteExam(String ids);
}
