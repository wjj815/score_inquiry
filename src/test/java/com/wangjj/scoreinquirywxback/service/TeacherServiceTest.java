package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.TeacherDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherServiceTest {

	@Autowired
	private TeacherService teacherService;

	@Test
	void saveTeacher() {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(2019005L);
		teacherDTO.setPhone("12345678901");
		teacherDTO.setCourseId(25L);
		teacherDTO.setTeacherName("李小刚");
		teacherService.saveTeacher(teacherDTO);
	}
}