package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.CourseDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
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



	/**
	 * 分页获取所有课程
	 * @return
	 */

	List<CourseDTO> getCourseList(CourseDTO courseDTO);

	PageResult<CourseDTO> getCoursePage(CourseDTO courseDTO, Pageable pageable);

	/**
	 * 添加课程
	 * @param course
	 */
	 void saveCourse(Course course);

	/**
	 * 批量删除课程
	 * @param courseIds
	 */
	void deleteCourse(String courseIds);
}
