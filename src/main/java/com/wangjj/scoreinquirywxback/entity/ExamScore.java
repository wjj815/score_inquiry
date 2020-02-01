package com.wangjj.scoreinquirywxback.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 考试成绩类
 *
 */
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_exam_score")
public class ExamScore {
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
	private Long courseId;
	/**考试学生*/
	private Long studentId;
	/**对应哪次考试*/
	private Long examId;



}
