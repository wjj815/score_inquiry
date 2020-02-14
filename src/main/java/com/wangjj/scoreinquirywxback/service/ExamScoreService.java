package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.ExamScore;
import com.wangjj.scoreinquirywxback.vo.response.PageResult;
import com.wangjj.scoreinquirywxback.vo.response.StudentScore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : ExamScoreService
 * @Author : wangJJ
 * @Date : 2020/2/12 14:34
 * @Description : 考试成绩的业务
 */
public interface ExamScoreService {

	/**
	 * 分页获取考试成绩
	 * @param examScore
	 * @param pageable
	 * @return
	 */
	Page<ExamScore> getExamScorePage(ExamScore examScore, Pageable pageable);

	/**
	 * 获取考试成绩列表
	 * @param examId
	 * @return
	 */
	PageResult<Map<String, Object>> getExamScoreListByPage(Long examId, int page, int pageSize);

	StudentScore getExamScoreListByStudentIdAndExamId(Long examId, Long studentId);

	/**
	 * 保存成绩
	 * @param examScore
	 */
	void saveExamScore(ExamScore examScore);

	void saveExamScoreList(List<ExamScore> examScoreList);
	/**
	 * 删除考试成绩
	 * @param id
	 */
	void deleteExamScore(Long id);
}
