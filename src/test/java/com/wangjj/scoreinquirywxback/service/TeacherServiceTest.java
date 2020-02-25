package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.TeacherDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherServiceTest {

	@Autowired
	private TeacherService teacherService;

	@Test
	void saveTeacher() {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(2019006L);
		teacherDTO.setPhone("12345678902");
		teacherDTO.setCourseId(26L);
		teacherDTO.setTeacherName("苏小雨");
		teacherService.saveTeacher(teacherDTO);
	}

	@Test
	void importTeacherList() {

		try {
			FileInputStream fileInputStream = new FileInputStream("");
			teacherService.importTeacherList(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}