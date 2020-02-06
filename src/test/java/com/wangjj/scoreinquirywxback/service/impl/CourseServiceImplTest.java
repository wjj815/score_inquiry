package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.entity.Course;
import com.wangjj.scoreinquirywxback.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceImplTest {

	@Autowired
	private CourseService courseService;
	@Test
	void getCourseList() {
		System.out.println(courseService.getCourseList());
	}

	@Test
	void saveCourse() {
		Course course = Course.builder().
				id(126L)
				.courseName("语文")
				.build();
		courseService.saveCourse(course);
	}

	@Test
	void deleteCourse() {

		String ids = "125,";
		courseService.deleteCourse(ids);
	}
}