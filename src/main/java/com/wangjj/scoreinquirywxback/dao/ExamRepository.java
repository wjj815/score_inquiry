package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName : ExamRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:01
 * @Description : 班级表操作
 */
public interface ExamRepository extends JpaRepository<Exam,Long> {
}
