package com.wangjj.scoreinquirywxback.pojo.entity;

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
@Table(name = "t_exam_score")
public class CourseScore {
	@Id
	@GeneratedValue
	private Long id; //ID
	/** 成绩 */
	private Integer score ;
	/** 创建人 */
	private String createdBy ;
	/** 创建时间 */
	private Date createdTime ;
	/** 更新人 */
	private String updatedBy ;
	/** 更新时间 */
	private Date updatedTime ;
	/**考试科目*/
	@OneToOne(fetch = FetchType.LAZY)
	private Course course;
	@ManyToOne(fetch = FetchType.LAZY)
	private Teacher teacher;
	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	private Exam exam;
}
