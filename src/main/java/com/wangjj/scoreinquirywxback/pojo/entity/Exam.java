package com.wangjj.scoreinquirywxback.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.*;

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
public class Exam {

	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue
	private Long id; //ID
	/**
	 * 考试名称
	 */
	@ApiModelProperty(name = "examName", value = "考试名称", example = "2019级第一次期中考试")
	private String examName;
	/**
	 * 考试时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(name = "examTime", value = "考试时间", example = "2019-12-1", dataType = "Date")
	private Date examTime;
	/**
	 * 考试备注
	 */
	@ApiModelProperty(name = "remark", value = "考试备注")
	private String remark;
	/**
	 * 考试类型
	 */
	@ApiModelProperty(name = "examType", value = "考试类型:班级平时考试（0）,年级考试(1):月考、期中考、期末考", example = "0")
	private Integer examType;
	/**
	 * 创建人
	 */
	@ApiModelProperty(hidden = true)
	private String createdBy;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	private Date createdTime;
	/**
	 * 更新人
	 */
	@ApiModelProperty(hidden = true)
	private String updatedBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(hidden = true)
	private Date updatedTime;


	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Grade grade;

	/*@ManyToMany(mappedBy = "exams",cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<> ();
*/
	/*@ManyToMany
	private List<Student> students = new ArrayList<> ();*/

	@OneToMany(cascade = CascadeType.ALL)
	private Set<CourseScore> courseScores = new HashSet<>(0);
}
