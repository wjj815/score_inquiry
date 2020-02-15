package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.entity.Exam;

import java.util.List;

/**
 * @ClassName : ScoreService
 * @Author : 1090086767
 * @Date : 2019/12/25 17:38
 * @Description : 成绩的业务
 */
public interface ScoreService {


	List<Exam> getExamScoreList();
}
