package com.wangjj.scoreinquirywxback.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 班级类
 *
 */

@ToString(exclude = {"grade", "students"})
@Setter
@Getter
@Entity
@Table(name = "t_clazz")
@org.hibernate.annotations.Table(appliesTo = "t_clazz",comment = "班级表")
public class Clazz {
	@Id
//	@GeneratedValue
	private Long id; //Id
	/** 班级名称 */

	private String clazzName ;
	/**
	 * 一个年级对应多个班级
	 */
	/*@ApiModelProperty(name = "gradeId", value = "年级Id", example = "2019")
	private Long gradeId; //班级所属年级*/
	/*年级*/
	@ManyToOne(fetch = FetchType.LAZY)
	private Grade grade;

	/*班级删除时，班级下的所有学生也会被删除*/
	@OneToMany(mappedBy = "clazz",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private final Set<Student> students = new HashSet<>(0);

	/*@OneToMany(mappedBy = "clazz")
	private List<ClazzCourse> clazzCourses = new ArrayList<>(0);*/

	@ManyToMany(fetch = FetchType.LAZY)
	private final Set<Teacher> teachers = new HashSet<>(0);
}
