package com.wangjj.scoreinquirywxback.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wangjj.scoreinquirywxback.entity.Exam;
import com.wangjj.scoreinquirywxback.entity.ExamScore;
import com.wangjj.scoreinquirywxback.entity.Student;
import lombok.*;

import java.util.List;

/**
 * @ClassName : StudentExamScore
 * @Author : wangJJ
 * @Date : 2020/2/12 21:02
 * @Description : 学生成绩封装
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StudentExamScore {

	private Exam exam;
	List<StudentScore> studentScoreList;
}


