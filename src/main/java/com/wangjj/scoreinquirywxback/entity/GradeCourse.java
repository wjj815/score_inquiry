package com.wangjj.scoreinquirywxback.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName : GradeCourse
 * @Author : wangJJ
 * @Date : 2020/1/23 14:25
 * @Description : 年级课程表
 */
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_grade_course")
public class GradeCourse {
	@Id
	@GeneratedValue
	private Long id;
	/**年级id*/
	private Long gradeId;
	/**课程id*/
	private Long courseId;
}
