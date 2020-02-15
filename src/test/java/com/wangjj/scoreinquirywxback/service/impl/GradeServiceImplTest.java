package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.pojo.dto.request.GradeCourseParameter;
import com.wangjj.scoreinquirywxback.service.GradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GradeServiceImplTest {

	@Autowired
	private GradeService gradeService;

	@Test
	void addGrade() {

	}

	@Test
	void isExist() {
	}

	@Test
	void findByGradeName() {
	}

	@Test
	void deleteGradeById() {
	}

	@Test
	void findGradeList() {
	}

	@Test
	void findGradePage() {
	}

	@Test
	void addGradeCourse() {
		GradeCourseParameter gradeCourseParameter = new GradeCourseParameter();
		gradeCourseParameter.setGradeId(2019L);
		gradeCourseParameter.setCourseIds("25,26,27,28");
		gradeService.saveGradeCourse(gradeCourseParameter);
	}
}