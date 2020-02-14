package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.GradeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName : GradeCourseRepository
 * @Author : wangJJ
 * @Date : 2020/2/12 16:40
 * @Description : 年级课程的数据库表
 */
public interface GradeCourseRepository extends JpaRepository<GradeCourse,Long>, JpaSpecificationExecutor<GradeCourse> {

	List<GradeCourse> findByGradeId(Long gradeId);

	int deleteByIdIn(List<Long> collect);
}
