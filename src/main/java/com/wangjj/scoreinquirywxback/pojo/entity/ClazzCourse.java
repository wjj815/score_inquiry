package com.wangjj.scoreinquirywxback.pojo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName : ClazzCourse
 * @Author : wangJJ
 * @Date : 2020/2/15 15:21
 * @Description : TODO
 */
@Entity
@Table(name="t_clazz_course")
@Data
public class ClazzCourse {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Clazz clazz;

	@ManyToOne
	private Course course;

	@ManyToOne
	private Teacher teacher;
}
