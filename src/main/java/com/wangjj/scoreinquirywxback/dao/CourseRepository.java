package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName : CourseRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:05
 * @Description : 课程表操作
 */
public interface CourseRepository extends JpaRepository<Course,Long> {
}
