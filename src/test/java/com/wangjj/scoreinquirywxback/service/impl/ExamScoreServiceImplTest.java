package com.wangjj.scoreinquirywxback.service.impl;
import	java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangjj.scoreinquirywxback.entity.ExamScore;
import com.wangjj.scoreinquirywxback.service.ExamScoreService;
import com.wangjj.scoreinquirywxback.service.ExamService;
import com.wangjj.scoreinquirywxback.vo.response.PageResult;
import com.wangjj.scoreinquirywxback.vo.response.StudentExamScore;
import com.wangjj.scoreinquirywxback.vo.response.StudentScore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamScoreServiceImplTest {

	@Autowired
	private ExamScoreService examScoreService;

	@Test
	void getExamScoreList() {
//		PageResult<StudentScore> examScoreList = examScoreService.getExamScoreListByPage(39L,0,10);
//		System.out.println(JSONObject.toJSONString(examScoreList));
		/*StudentExamScore examScoreListByStudentIdAndExamId = examScoreService.getExamScoreListByStudentIdAndExamId(39L, 20190101L);
		System.out.println(JSON.toJSONString(examScoreListByStudentIdAndExamId));*/
	}

	@Test
	void saveExamScore() {
		List<ExamScore> examScores = new ArrayList<> ();
 		examScores.add(ExamScore.builder()
				.examId(39L)
				.courseId(25L)
				.studentId(20190101L)
				.score(80)
		.build());
		examScores.add(ExamScore.builder()
				.examId(39L)
				.courseId(25L)
				.studentId(20190102L)
				.score(60)
				.build());
		examScores.add(ExamScore.builder()
				.examId(39L)
				.courseId(25L)
				.studentId(20190103L)
				.score(82).build());
		examScores.add(ExamScore.builder()
				.examId(39L)
				.courseId(26L)
				.studentId(20190101L)
				.score(80).build());
		examScores.add(ExamScore.builder()
				.examId(39L)
				.courseId(26L)
				.studentId(20190102L)
				.score(60).build());
		examScores.add(ExamScore.builder()
				.examId(39L)
				.courseId(26L)
				.studentId(20190103L)
				.score(82).build());
		examScoreService.saveExamScoreList(examScores);
	}
}