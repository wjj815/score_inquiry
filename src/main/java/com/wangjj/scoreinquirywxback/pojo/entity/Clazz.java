package com.wangjj.scoreinquirywxback.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * 班级类
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel(description = "班级实体")
@ToString(exclude = {"grade","clazzCourses"})
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_clazz")
public class Clazz {
	@ApiModelProperty(name = "id",value = "班级编号",example = "201901")
	@Id
//	@GeneratedValue
	private Long id; //Id
	/** 班级名称 */
	@ApiModelProperty(name = "clazzName", value = "班级名称", example = "2019级1班")
	private String clazzName ;
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
	/**
	 * 一个年级对应多个班级
	 */
	/*@ApiModelProperty(name = "gradeId", value = "年级Id", example = "2019")
	private Long gradeId; //班级所属年级*/
	/*年级*/
	@ManyToOne(fetch = FetchType.LAZY)
	private Grade grade;

	@OneToMany(mappedBy = "clazz",fetch = FetchType.LAZY)
	private List<Student> students = new ArrayList<>(0);

	/*@OneToMany(mappedBy = "clazz")
	private List<ClazzCourse> clazzCourses = new ArrayList<>(0);*/

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Teacher> teachers = new HashSet<>(0);
}
