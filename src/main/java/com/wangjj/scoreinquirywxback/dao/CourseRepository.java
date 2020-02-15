package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName : CourseRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:05
 * @Description : 课程表操作
 */
public interface CourseRepository extends JpaRepository<Course,Long>, JpaSpecificationExecutor<Course> {

	void deleteByIdIn(List<Long> ids);
}
