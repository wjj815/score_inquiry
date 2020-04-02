package com.wangjj.scoreinquirywxback.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 考试类
 * 
 * 考试分为年级统考和平时考试两种
 * 年级统考由管理员添加一次考试
 * 平时考试由科任老师添加考试
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(description = "考试类")
@Getter
@Setter
@Entity
@Table(name = "t_exam")
@org.hibernate.annotations.Table(appliesTo = "t_exam",comment = "考试表")
public class Exam {

	@Id
	@GeneratedValue
	private Long id; //ID
	/**
	 * 考试名称
	 */
	private String examName;
	/**
	 * 考试时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date examTime;
	/**
	 * 考试备注
	 */
	private String remark;
	/**
	 * 考试类型
	 */
	private Integer examType;


	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Grade grade;

	/*@ManyToMany(mappedBy = "exams",cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<> ();
*/
	/*@ManyToMany
	private List<Student> students = new ArrayList<> ();*/

	@OneToMany(mappedBy = "exam",cascade = CascadeType.ALL)
	private Set<CourseScore> courseScores = new HashSet<>(0);
}
