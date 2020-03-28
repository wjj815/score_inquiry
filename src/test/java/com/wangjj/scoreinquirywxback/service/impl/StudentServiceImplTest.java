package com.wangjj.scoreinquirywxback.service.impl;


import com.wangjj.scoreinquirywxback.pojo.dto.StudentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
class StudentServiceImplTest {

	@Autowired
	private StudentService studentService;



	@Test
	void importStudentList() {
		try {
			long l = System.currentTimeMillis();
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setGradeId(2019L);
			studentDTO.setClazzId(3L);
			studentService.importStudentList(new FileInputStream("C:\\Users\\1090086767\\Desktop\\毕业设计\\importStudentTemplate.xlsx"), studentDTO);
			System.out.println(System.currentTimeMillis() - l);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	void addStudent() {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(20190102L);
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
		List<StudentDTO> student = studentService.findStudentList(studentDTO);
		System.out.println(student);
	}

	@Test
	void findStudentById() {
	}


	@Test
	void findStudentById1() {
	}





	@Test
	void findStudentOfParent() {
		System.out.println(studentService.findStudentOfParent(113L));
	}


	@Test
	void findStudent2() {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setGradeId(2019L);
		PageResult<StudentDTO> student = studentService.findStudentPage(studentDTO, PageRequest.of(0, 10));
		System.out.println(student);
	}
}