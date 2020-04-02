package com.wangjj.scoreinquirywxback.pojo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 课程类
 */
/*@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})*/
@ToString(exclude = {"grades", "courseScore"})
@Setter
@Getter
@Entity
@Table(name = "t_course",indexes = {@Index(
	unique = true,name = "course_name",columnList = "courseName"
)})
@org.hibernate.annotations.Table(appliesTo = "t_course",comment = "课程类")
@EqualsAndHashCode
public class Course {
	@Id
	@GeneratedValue
	private Long id; //ID
	/**
	 * 课程名
	 */
	private String courseName;
	/**
	 * 课程说明
	 */
	private String introduction;


	/*一个课程对应一个考试成绩*/
	@OneToOne(fetch = FetchType.LAZY)
	private CourseScore courseScore;


	@ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private final Set<Grade> grades = new HashSet<>(0);


	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private final Set<Teacher> teachers = new HashSet<>(0);
}
