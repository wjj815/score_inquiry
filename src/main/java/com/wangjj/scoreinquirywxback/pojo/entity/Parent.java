package com.wangjj.scoreinquirywxback.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName : Parent
 * @Author : wangJJ
 * @Date : 2019/12/24 21:01
 * @Description : 家长
 */
/*@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})*/
@Setter
@Getter
@ToString(exclude = {"students"})
@Entity
@Table(name = "t_parent")
@org.hibernate.annotations.Table(appliesTo = "t_parent",comment = "学生家长表")
@EqualsAndHashCode
public class Parent {


	@ApiModelProperty(hidden = true)
	@Id
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
	/*@ApiModelProperty( name = "studentRelation",value = "与学生关系",required = true)
	private String studentRelation;*/
	/** 身份证号 */
	@ApiModelProperty( name = "idCardNo",value = "家长身份证号",required = true)
	private String idCardNo ;
	/** 籍贯 */
	@ExcelProperty(value = "籍贯")
	private String domicilePlace ;
	/** 居住地址 */
	@ExcelProperty(value = "居住地址")
	private String presentAddress ;



	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Student> students = new HashSet<>(0);

	/**角色*/
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;

}
