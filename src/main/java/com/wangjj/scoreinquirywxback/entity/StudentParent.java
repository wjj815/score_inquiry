package com.wangjj.scoreinquirywxback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName : StudentParent
 * @Author : wangJJ
 * @Date : 2020/1/29 22:44
 * @Description : 学生家长类
 */
@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_student_parent")
public class StudentParent {

	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 学生id
	 */
	private Long studentId;

	/**
	 * 家长id
	 */
	private Long parentId;
}
