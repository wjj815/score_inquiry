package com.wangjj.scoreinquirywxback.service.impl;


import com.wangjj.scoreinquirywxback.entity.Student;
import com.wangjj.scoreinquirywxback.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceImplTest {

	@Autowired
	private StudentService studentService;

	@Test
	void addStudentParent() {

	}

	@Test
	void importStudentList() {
		try {
			studentService.importStudentList(new FileInputStream("C:\\Users\\1090086767\\Desktop\\导入学生模版.xlsx"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	void addStudent() {
	}

	@Test
	void updateStudent() {
	}

	@Test
	void findStudent() {
		Page<Student> student = studentService.findStudent(new Student().toBuilder().clazzId(201901L).build(), PageRequest.of(0,20));
		System.out.println(student.getContent());
	}

	@Test
	void findStudentById() {
	}
}