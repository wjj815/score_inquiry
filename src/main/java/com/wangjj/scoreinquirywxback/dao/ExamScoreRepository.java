package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.ExamScore;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName : ExamScoreRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:03
 * @Description : 考试成绩表
 */
public interface ExamScoreRepository extends JpaRepository<ExamScore,Long> {
}
