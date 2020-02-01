package com.wangjj.scoreinquirywxback.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 系统初始化的一些信息
 *
 */
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_school")
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 

	/** 学校名称 */
	private String schoolName ;
	/** 创建人 */
	private String createdBy ;
	/** 创建时间 */
	private Date createdTime ;
	/** 更新人 */
	private String updatedBy ;
	/** 更新时间 */
	private Date updatedTime ;

	private String noticeTeacher; //教师通知
	
	private String noticeStudent; //学生通知


}
