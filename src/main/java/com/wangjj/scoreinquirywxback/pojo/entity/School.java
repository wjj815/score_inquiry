package com.wangjj.scoreinquirywxback.pojo.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@org.hibernate.annotations.Table(appliesTo = "t_school",comment = "学校信息表")
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 

	/** 学校名称 */
	private String schoolName ;


	private String noticeTeacher; //教师通知
	
	private String noticeStudent; //学生通知


}
