package com.wangjj.scoreinquirywxback.pojo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
@Table(name = "t_course_score")
public class CourseScore {
	@ExcelIgnore
	@Id
	private Long id; //ID
	/** 成绩 */
	@ExcelProperty(value = "成绩")
	private Integer score ;
	/** 创建人 */
	@ExcelIgnore
	private String createdBy ;
	@ExcelIgnore
	/** 创建时间 */
	private Date createdTime ;
	@ExcelIgnore
	/** 更新人 */
	private String updatedBy ;
	@ExcelIgnore
	/** 更新时间 */
	private Date updatedTime ;
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
