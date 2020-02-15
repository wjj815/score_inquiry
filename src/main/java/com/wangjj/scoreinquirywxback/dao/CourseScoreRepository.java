package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.CourseScore;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName : CourseScoreRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:03
 * @Description : 考试成绩表
 */
public interface CourseScoreRepository extends JpaRepository<CourseScore,Long> {
}
