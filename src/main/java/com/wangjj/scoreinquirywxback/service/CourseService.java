package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.Course;
import com.wangjj.scoreinquirywxback.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ClassName : CourseService
 * @Author : 1090086767
 * @Date : 2019/12/25 17:34
 * @Description : 课程业务
 */
public interface CourseService {

	/**
	 * 获取所有课程
	 * @return
	 */
	List<Course> getCourseList();


	/**
	 * 分页获取所有课程
	 * @return
	 */
	Page<Course> getCoursePage(Pageable pageable);

	/**
	 * 添加课程
	 * @param course
	 */
	 void saveCourse(Course course);

	/**
	 * 删除课程
	 * @param courseId
	 */
	void deleteCourse(Long courseId);
}
