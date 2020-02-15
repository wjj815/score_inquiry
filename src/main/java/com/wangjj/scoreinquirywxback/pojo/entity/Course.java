package com.wangjj.scoreinquirywxback.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课程类
 *
 */
/*@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})*/
@ApiModel(description = "课程实体")
@ToString(exclude = {"grades","clazzCourses","courseScore"})
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_course")
@EqualsAndHashCode
public class Course {
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue
	private Long id; //ID
	/** 课程名 */
	@ApiModelProperty(name = "courseName",value = "课程名称",example = "语文")
	private String courseName ;
	/** 课程说明 */
	@ApiModelProperty(name = "introduction",value = "课程介绍",example = "关于汉语的学习")
	private String introduction ;
	/** 创建人 */
	@ApiModelProperty(hidden = true)
	private String createdBy ;
	/** 创建时间 */
	@ApiModelProperty(hidden = true)
	private Date createdTime ;
	/** 更新人 */
	@ApiModelProperty(hidden = true)
	private String updatedBy ;
	/** 更新时间 */
	@ApiModelProperty(hidden = true)
	private Date updatedTime ;


	/*@ManyToMany(fetch = FetchType.LAZY)
	private List<Exam> exams = new ArrayList<> ();*/

	/*一个课程对应一个考试成绩*/
	@OneToOne(fetch = FetchType.LAZY)
	private CourseScore courseScore;


	@ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
	private List<Grade> grades = new ArrayList<>(0);


	@OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
	private List<ClazzCourse> clazzCourses = new ArrayList<>(0);
}
