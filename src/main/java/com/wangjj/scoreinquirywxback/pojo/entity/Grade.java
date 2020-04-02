package com.wangjj.scoreinquirywxback.pojo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 年级类
 *
 */
@ApiModel(description = "年级实体")
@Getter
@Setter
@Entity
@Table(name = "t_grade")
@org.hibernate.annotations.Table(appliesTo = "t_grade",comment = "年级表")
public class Grade {

	@Id
//	@GeneratedValue
	private Long id; //ID
	/** 年级名称 */
	private String gradeName ;
	/** 学校id */
	private String schoolId ;

	@OneToMany(mappedBy = "grade",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private final Set<Exam> exams = new HashSet<>(0);

	@OneToMany(mappedBy = "grade",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private final Set<Clazz> clazzes = new HashSet<>(0);

	@OneToMany(mappedBy = "grade",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private final Set<Student> students = new HashSet<>(0);

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Course> courses = new ArrayList<>(0);
}
