package com.wangjj.scoreinquirywxback.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangjj.scoreinquirywxback.pojo.dto.CourseDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseServiceImplTest {

	@Autowired
	private CourseService courseService;
	@Test
	void getCourseList() {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setClazzId(1L);
		List<CourseDTO> courseList = courseService.getCourseList(courseDTO);
		System.out.println(JSON.toJSONString(courseList));
	}

	@Test
	void saveCourse() {
		/*Course course = Course.builder().
				id(126L)
				.courseName("语文")
				.build();*/
//		courseService.saveCourse(courseServiceourse);
	}

	@Test
	void deleteCourse() {

		String ids = "125,";
		courseService.deleteCourse(ids);
	}

	@Test
	void getCourseList1() {
	}

	@Test
	void getCoursePage() {
	}

	@Test
	void saveCourse1() {
	}

	@Test
	void deleteCourse1() {
	}
}