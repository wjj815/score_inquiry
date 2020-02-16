package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.pojo.dto.TeacherDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
class TeacherServiceImplTest {

	@Autowired
	private TeacherService teacherService;
	@Test
	void saveClazzCourse() {
	}

	@Test
	void findClazzCoursePageByTeacherId() {
	}

	@Test
	void saveTeacher() {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setId(20190010L);
		teacherDTO.setTeacherName("王大锤");
		teacherDTO.setCourseId(25L);
		teacherService.saveTeacher(teacherDTO);
	}



	@Test
	void getTeacherPage() {
		TeacherDTO teacherDTO = new TeacherDTO();
//		teacherDTO.setCourseId(25L);
		teacherDTO.setClazzId("1");
		PageResult<TeacherDTO> teacherPage = teacherService.getTeacherPage(teacherDTO, PageRequest.of(0, 10));
		System.out.println(teacherPage);
	}

	@Test
	void deleteTeacher() {
		teacherService.deleteTeacher("2019001");
	}

	@Test
	void importTeacherList() {
	}

	@Test
	void getTeacherList() {
	}

	@Test
	void getTeacherById() {
	}

	@Test
	void saveClazzCourse1() {
	}

	@Test
	void delete() {
	}

	@Test
	void findClazzCoursePageByTeacherId1() {
	}

	@Test
	void getTeacherList1() {
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setCourseId(25L);
		teacherDTO.setClazzId("201901");
		List<TeacherDTO> teacherList = teacherService.getTeacherList(teacherDTO);
		System.out.println(teacherList);
	}
}