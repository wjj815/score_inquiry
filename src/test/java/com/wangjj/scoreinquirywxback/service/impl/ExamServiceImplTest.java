package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.pojo.dto.ExamDTO;
import com.wangjj.scoreinquirywxback.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
class ExamServiceImplTest {

	@Autowired
	private ExamService examService;

	@Test
	void getExamPage() {
	}

	@Test
	void getExamList() {
	}

	@Test
	void saveExam() {
		long start = System.currentTimeMillis();
		ExamDTO examDTO = ExamDTO.builder()
				.gradeId(2019L)
				.examName("第三次期末考试")
				.examType(1)
				.examTime(new Date())
				.remark("测试")
				.build();
		examService.saveExam(examDTO);
		System.out.println("用时："+(System.currentTimeMillis() - start));
	}

	@Test
	void deleteExam() {
	}

	@Test
	void getExamScorePage() {
	}

	@Test
	void getExamScoreList() {
	}

	@Test
	void saveExamScore() {
	}

	@Test
	void deleteExamScore() {
	}
}