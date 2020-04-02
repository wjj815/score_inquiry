package com.wangjj.scoreinquirywxback.pojo.entity;

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
	@Id
	private Long id; //ID
	/** 成绩 */
	private Integer score ;
	/**考试科目*/
	@OneToOne(fetch = FetchType.LAZY)
	private Course course;
	@ManyToOne(fetch = FetchType.LAZY)
/*	private Teacher teacher;
	@ManyToOne(fetch = FetchType.LAZY)*/
	private Student student;
	@ManyToOne(fetch = FetchType.LAZY)
	private Exam exam;
	@Transient
	private Long studentId;
}
