package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.Course;
import com.wangjj.scoreinquirywxback.entity.Grade;

import java.util.List;

/**
 * @ClassName : CourseService
 * @Author : 1090086767
 * @Date : 2019/12/25 17:34
 * @Description : 课程业务
 */
public interface CourseService {

	/**
	 * 获取年级的所有课程
	 * @param grade
	 * @return
	 */
	List<Course> getCourseListByGrade(Grade grade);


	/**
	 * 添加课程
	 * @param course
	 */
	 void addCourse(Course course);

	/**
	 * 删除课程
	 * @param courseId
	 */
	void deleteCourse(Long courseId);
}
