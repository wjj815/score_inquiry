package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.CourseService;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName : CourseServiceImpl
 * @Author : wangJJ
 * @Date : 2020/2/4 19:29
 * @Description : 课程业务的实现类
 */
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getCourseList() {
		return courseRepository.findAll();
	}

	@Override
	public Page<Course> getCoursePage(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}

	@Override
	public void saveCourse(Course course) {
		if(courseRepository.exists(Example.of(course))) {
			throw new GlobalException("课程已存在！");
		}

		courseRepository.save(course);
	}

	@Transactional
	@Override
	public void deleteCourse(String courseIds) {
		List<Long> collect = ParameterUtils.analyse(courseIds);
		courseRepository.deleteByIdIn(collect);
	}
}
