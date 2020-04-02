package com.wangjj.scoreinquirywxback.pojo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import javax.persistence.*;

/**
 * 考试成绩类
 *
 */
@ToString(exclude = {"student","exam"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_exam_score")
@org.hibernate.annotations.Table(appliesTo = "t_exam_score",comment = "考试成绩类")
public class CourseScore {
	@ExcelIgnore
	@Id
	private Long id; //ID
	/** 成绩 */
	@ExcelProperty(value = "成绩")
	private Integer score ;
	/**考试科目*/
	@ExcelIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private Course course;
	@ManyToOne(fetch = FetchType.LAZY)
/*	private Teacher teacher;
	@ManyToOne(fetch = FetchType.LAZY)*/
	@ExcelIgnore
	private Student student;
	@ExcelIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Exam exam;
	@ExcelProperty(value = "学号")
	@Transient
	private Long studentId;
}
