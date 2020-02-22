package com.wangjj.scoreinquirywxback.service;

import com.alibaba.fastjson.JSON;
import com.wangjj.scoreinquirywxback.pojo.dto.CourseScoreDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.StudentScoreDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseScoreServiceTest {

	@Autowired
	private CourseScoreService courseScoreService;
	@Test
	void saveCourseScore() {
		CourseScoreDTO courseScoreDTO = new CourseScoreDTO();
		courseScoreDTO.setExamId(123L);
		courseScoreDTO.setCourseId(26L);
		courseScoreDTO.setScore(89);
		courseScoreDTO.setTeacherId(2019002L);
		courseScoreDTO.setStudentId(20190103L);
		courseScoreService.saveCourseScore(courseScoreDTO);
	}

	@Test
	void getCourseScoreList() {
		CourseScoreDTO courseScoreDTO = new CourseScoreDTO();
		courseScoreDTO.setExamId(123L);
		courseScoreDTO.setClazzId(2L);
		List<StudentScoreDTO> studentScoreDTOListByExamId = courseScoreService.getStudentScoreDTOList(courseScoreDTO);
		System.out.println(JSON.toJSONString(studentScoreDTOListByExamId));
	}

	@Test
	void importStudentScore() {
		CourseScoreDTO courseScoreDTO = new CourseScoreDTO();
		courseScoreDTO.setExamId(123L);
		courseScoreDTO.setCourseId(27L);
		courseScoreDTO.setClazzId(2L);
		try {
			InputStream i = new FileInputStream("C:\\Users\\1090086767\\Desktop\\模版.xlsx");
			courseScoreService.importStudentScore(i,courseScoreDTO);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	void exportTemplate() {
		CourseScoreDTO courseScoreDTO = new CourseScoreDTO();
		courseScoreDTO.setCourseId(27L);
		courseScoreDTO.setExamId(123L);
		courseScoreDTO.setClazzId(2L);
		try {
			FileOutputStream outputStream = new FileOutputStream("C:\\Users\\1090086767\\Desktop\\模版.xlsx");
			courseScoreService.exportTemplate(outputStream,courseScoreDTO);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}