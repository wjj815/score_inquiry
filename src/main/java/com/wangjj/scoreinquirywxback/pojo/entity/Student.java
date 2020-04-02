package com.wangjj.scoreinquirywxback.pojo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 学生类
 *
 */
@Getter
@Setter
@ToString(exclude = {"grade","clazz","courseScores","parents"})
@Entity
@Table(name = "t_student")
@org.hibernate.annotations.Table(appliesTo = "t_student",comment = "学生信息表")
@EqualsAndHashCode
public class Student {
	/** 学生学号 */
	@Id
	@ExcelProperty(value = "学号")
	private Long id;
	/** 学生姓名 */
	@ExcelProperty(value = "姓名")
	private String studentName ;
	/** 性别 */
	@ExcelProperty(value = "性别")
	private String sex ;
	/** 年龄 */
	@ExcelProperty("年龄")
	private Integer age;
	/** 身份证号 */
	@ExcelProperty(value = "身份证号")
	private String idCardNo ;
	/** 生日 */
	@Temporal(TemporalType.DATE)
	@ExcelProperty("出生日期")
	@DateTimeFormat(value = "yyyy/MM/dd")
	private Date birthday ;
	/** 微信 */
	@ExcelProperty(value = "微信")
	private String weiXin;
	/** 手机号 */
	@ExcelProperty(value = "手机号")
	private String mobilePhone ;
	/** 籍贯 */
	@ExcelProperty(value = "籍贯")
	private String domicilePlace ;
	/** 居住地址 */
	@ExcelProperty(value = "居住地址")
	private String presentAddress ;
	/** 状态 */
	@ExcelIgnore
	@ApiParam(hidden = true)
	private String status ;
	/** 创建人 */

	@ExcelIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Clazz clazz;


	@ExcelIgnore
	@ManyToMany(mappedBy = "students",fetch = FetchType.LAZY)
	private List<Parent> parents = new ArrayList<>(0); //家长信息
/*	@JsonIgnore
	@ManyToMany(mappedBy = "students")
	List<Exam> exams = new ArrayList<> ();*/

	@ExcelIgnore
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<CourseScore> courseScores = new ArrayList<>(0);

	@ExcelIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Grade grade;




}
