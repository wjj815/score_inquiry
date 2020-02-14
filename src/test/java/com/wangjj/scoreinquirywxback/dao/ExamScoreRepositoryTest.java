package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.ExamScore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamScoreRepositoryTest {

	@Autowired
	private ExamScoreRepository examScoreRepository;

	@Test
	public void testGradeRank() {
		int studentGradeRankByExamIdAndStudentId = examScoreRepository.getStudentGradeRankByExamIdAndStudentId(47L, 20190110L);
		System.out.println(studentGradeRankByExamIdAndStudentId);
		int studentClazzRankByExamIdAndClazzIdAndStudentId = examScoreRepository.getStudentClazzRankByExamIdAndClazzIdAndStudentId(39L, 201901L, 20190101L);
		System.out.println(studentClazzRankByExamIdAndClazzIdAndStudentId);
	}

	@Test
	public void testGetStudentScore() {

	}

}