package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.CourseScoreDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseScoreServiceTest {

	@Autowired
	private CourseScoreService courseScoreService;
	@Test
	void saveCourseScore() {
		CourseScoreDTO courseScoreDTO = new CourseScoreDTO();
		courseScoreDTO.setExamId(117L);
		courseScoreDTO.setCourseId(26L);
		courseScoreDTO.setScore(89);
		courseScoreDTO.setTeacherId(2019002L);
		courseScoreDTO.setStudentId(20190103L);
		courseScoreService.saveCourseScore(courseScoreDTO);
	}

	@Test
	void getCourseScoreList() {
		CourseScoreDTO courseScoreDTO = new CourseScoreDTO();
		courseScoreDTO.setTeacherId(2019002L);
//		courseScoreDTO.setCourseId(25L);
//		courseScoreDTO.setScore(89);
//		courseScoreDTO.setStudentId(20190110L);

		List<CourseScoreDTO> courseScoreList = courseScoreService.getCourseScoreList(courseScoreDTO);
		System.out.println(courseScoreList);
	}
}