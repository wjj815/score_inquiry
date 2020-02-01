package com.wangjj.scoreinquirywxback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 考试类
 * 
 * 考试分为年级统考和平时考试两种
 * 年级统考由管理员添加一次考试
 * 平时考试由科任老师添加考试
 *
 */
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_exam")
public class Exam {
	@Id
	@GeneratedValue
	private Long id; //ID
	/** 考试名称 */
	private String examName ;
	/** 考试时间 */
	private String examTime ;
	/** 考试备注 */
	private String remark ;
	/** 考试类型 */
	@Enumerated(EnumType.STRING)
	private ExamType examType ;
	/** 创建人 */
	private String createdBy ;
	/** 创建时间 */
	private Date createdTime ;
	/** 更新人 */
	private String updatedBy ;
	/** 更新时间 */
	private Date updatedTime ;

	/**
	 * 考试类型
	 */
	public enum ExamType{
		/**
		 * 考试类型：月考
		 */
		MONTH_TYPE,
		/**
		 * 考试类型：期中考试
		 */
		MIDTERM_TYPE,
		/**
		 * 考试类型：期末考试
		 */
		FINALTERM_TYPE
	}

	/*public static final int EXAM_MONTH_TYPE = 1;


	public static final int EXAM_MID_TERM_TYPE = 2;


	public static final int EXAM_FINAL_TERM_TYPE = 3;*/

	/**
	 * 年级id
	 */
	private Long gradeId;
	/**
	 * 班级id
	 */
	private Long clazzId ; //考试的班级: 平时考试涉及到某个班级，统考则为所有班级
}
