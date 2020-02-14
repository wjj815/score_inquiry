package com.wangjj.scoreinquirywxback.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wangjj.scoreinquirywxback.entity.Exam;
import com.wangjj.scoreinquirywxback.entity.ExamScore;
import com.wangjj.scoreinquirywxback.entity.Student;
import lombok.*;

import java.util.List;

/**
 * @ClassName : StudentScore
 * @Author : wangJJ
 * @Date : 2020/2/13 13:00
 * @Description : TODO
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StudentScore {

	private Student student;

	List<ExamScore> examScoreList;

	private Integer totalScore;

	private Integer clazzRank;

	private Integer gradeRank;
}
