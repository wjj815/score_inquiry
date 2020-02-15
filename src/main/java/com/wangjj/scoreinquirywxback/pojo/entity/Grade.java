package com.wangjj.scoreinquirywxback.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * 年级类
 *
 */
@ApiModel(description = "年级实体")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_grade",
		uniqueConstraints = {@UniqueConstraint(columnNames = {"gradeName"})})
public class Grade {

	@Id
//	@GeneratedValue
	@ApiModelProperty(name = "id",value = "年级编号",example = "2019")
	private Long id; //ID
	/** 年级名称 */
	@ApiModelProperty(name = "gradeName",value = "年级名称",required = true,example = "2019级")
	private String gradeName ;
	/** 学校id */
	@ApiModelProperty(name = "schoolId",hidden = true)
	private String schoolId ;
	/** 创建人 */
	@ApiModelProperty(hidden = true)/*隐藏Api属性*/
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

	@OneToMany(mappedBy = "grade",fetch = FetchType.LAZY)
	private List<Exam> exams = new ArrayList<>(0);

	@OneToMany(mappedBy = "grade",fetch = FetchType.LAZY)
	private List<Clazz> clazzes = new ArrayList<>(0);

	@OneToMany(mappedBy = "grade",fetch = FetchType.LAZY)
	private List<Student> students = new ArrayList<>(0);

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Course> courses = new ArrayList<>(0);
}
