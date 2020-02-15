package com.wangjj.scoreinquirywxback.service.impl;


import com.wangjj.scoreinquirywxback.pojo.dto.ParentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.StudentDTO;
import com.wangjj.scoreinquirywxback.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
class StudentServiceImplTest {

	@Autowired
	private StudentService studentService;

	@Test
	void addStudentParent() {

		studentService.saveStudentParent(20190102L,113L);
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
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(201901011L);
		studentDTO.setStudentName("王大大");
		studentDTO.setGradeId(2019L);
		studentDTO.setClazzId(1L);
		studentService.saveStudent(studentDTO);
	}

	@Test
	void updateStudent() {
	}

	@Test
	void findStudent() {
//		Page<Student> student = studentService.findStudent(new Student().toBuilder().clazzId(201901L).build(), PageRequest.of(0,20));
//		System.out.println(student.getContent());
		/*Student studentById = studentService.findStudentById(201901011L);
		List<Course> course = studentById.getGrade().getCourses();
		System.out.println(course);*/
		StudentDTO studentDTO = new StudentDTO() ;
		studentDTO.setGradeId(2019L);
		studentDTO.setId(20190101L);
		List<StudentDTO> student = studentService.findStudent(studentDTO);
		System.out.println(student);
	}

	@Test
	void findStudentById() {
	}

	@Test
	void findStudent1() {
	}

	@Test
	void findStudentById1() {
	}

	@Test
	void saveParent() {

		ParentDTO parentDTO = new ParentDTO();
		parentDTO.setId(114L);
		parentDTO.setName("王大锤呀123");

		studentService.saveParent(parentDTO);

	}

	@Test
	void findStudentParent() {

		List<ParentDTO> studentParent = studentService.findParentOfStudent(20190102L);
		System.out.println(studentParent);
	}

	@Test
	void findStudentOfParent() {
		System.out.println(studentService.findStudentOfParent(113L));
	}
}