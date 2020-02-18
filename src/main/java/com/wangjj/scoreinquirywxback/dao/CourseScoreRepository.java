package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.CourseScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName : CourseScoreRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:03
 * @Description : 考试科目成绩
 */
public interface CourseScoreRepository extends JpaRepository<CourseScore,Long>, JpaSpecificationExecutor<CourseScore> {
}
