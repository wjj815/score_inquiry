package com.wangjj.scoreinquirywxback.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * @ClassName : Parent
 * @Author : wangJJ
 * @Date : 2019/12/24 21:01
 * @Description : 家长
 */
/*@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})*/
@ApiModel(description = "家长实体类")
@Setter
@Getter
@ToString(exclude = {"students"})
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_parent")
@EqualsAndHashCode
public class Parent {


	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue
	private Long id;

	/** 家长姓名 */
	@ApiModelProperty( name = "name",value = "家长姓名",required = true)
	private String name ;
	/** 电话 */
	@ApiModelProperty( name = "phone",value = "家长电话",required = true)
	private String phone ;
	/** 性别 */
	@ApiModelProperty( name = "sex",value = "家长性别",required = true)
	private String sex ;
	/** 年龄*/
	@ApiModelProperty( name = "age",value = "家长年龄",required = true)
	private Integer age ;
	/** 与学生关系 */
	@ApiModelProperty( name = "studentRelation",value = "与学生关系",required = true)
	private String studentRelation;
	/** 身份证号 */
	@ApiModelProperty( name = "idCardNo",value = "家长身份证号",required = true)
	private String idCardNo ;

	/** 微信 */
	@ApiModelProperty(hidden = true)
	private String weiXin ;
	/** 创建时间 */
	@ApiModelProperty(hidden = true)
	private Date createdTime ;
	/** 创建人 */
	@ApiModelProperty(hidden = true)
	private String createdBy ;
	/** 更新人 */
	@ApiModelProperty(hidden = true)
	private String updatedBy ;
	/** 更新时间 */
	@ApiModelProperty(hidden = true)
	private Date updatedTime ;


	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Student> students = new HashSet<>(0);


}
