package com.wangjj.scoreinquirywxback.dao;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseScoreRepositoryTest {


	@Autowired
	private CourseScoreRepository courseScoreRepository;
	@Test
	void getStudentGradeRankByExamIdAndStudentId() {
		int studentGradeRankByExamIdAndStudentId = courseScoreRepository.getStudentGradeRankByExamIdAndStudentId(123L, 20190210L);
		System.out.println(studentGradeRankByExamIdAndStudentId);
	}

	@Test
	void getStudentClazzRankByExamIdAndClazzIdAndStudentId() {
		int studentClazzRankByExamIdAndClazzIdAndStudentId = courseScoreRepository.getStudentClazzRankByExamIdAndClazzIdAndStudentId(123L, 1L, 20190210L);
		System.out.println(studentClazzRankByExamIdAndClazzIdAndStudentId);
	}
}