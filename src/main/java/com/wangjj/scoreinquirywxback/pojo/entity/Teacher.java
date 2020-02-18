package com.wangjj.scoreinquirywxback.pojo.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.*;

/**
 * 教师类
 *
 */
@Entity
@ToString(exclude = {"course","courseScores","clazzSet"})
@Setter
@Getter
@Table(name = "t_teacher")
public class Teacher {

	@ExcelProperty(value="教师编号")
	@ColumnWidth(value = 20)
	@ApiModelProperty(name = "id",value = "教师编号",required = true,example = "2019001")
	@Id
//	@GeneratedValue
	private Long id; //Id
	/** 姓名 */
	@ExcelProperty(value = "教师姓名")
	@ColumnWidth(value = 20)
	@ApiModelProperty(name="teacherName",value = "教师姓名",required = true,example = "王老师")
	private String teacherName ;
	/** 性别 */
	@ExcelProperty("性别")
	@ApiModelProperty(name="sex",value = "性别",required = true,example = "男")
	private String sex ;
	/** 年龄*/
	@ExcelProperty("年龄")
	@ColumnWidth(value = 10)
	private Integer age;
	/** 出生日期 */
	@Temporal(TemporalType.DATE)
	@ExcelProperty("出生日期")
	@ColumnWidth(value = 20)
	@DateTimeFormat(value = "yyyy/MM/dd")
	@ApiModelProperty(name="birthday",value = "出生日期",example = "1976-2-13",dataType = "Date")
	private Date birthday ;
	/** 身份证号 */
	@ExcelProperty("身份证号")
	@ColumnWidth(value = 30)
	@ApiModelProperty(name="idCardNo",value = "身份证号",example = "18位数字")
	private String idCardNo ;
	/** 电话 */
	@ExcelProperty("联系电话")
	@ColumnWidth(value = 20)
	@ApiModelProperty(name = "phone",value = "联系电话", example = "11位手机号")
	private String phone ;
	/** 介绍 */
	@ExcelProperty("详细介绍")
	@ColumnWidth(value = 30)

	@ApiModelProperty(name = "introduction",value = "介绍")
	private String introduction ;
	/** 创建人 */
	@ExcelIgnore
	@ApiModelProperty(hidden = true)
	private String createdBy ;
	/** 创建时间 */
	@Temporal(TemporalType.DATE)
	@CreatedDate
	@ExcelProperty("创建时间")
	@ColumnWidth(value = 30)
	@ApiModelProperty(hidden = true)
	private Date createdTime ;
	/** 更新人 */
	@ExcelIgnore
	@ApiModelProperty(hidden = true)
	private String updatedBy ;
	/** 更新时间 */
	@ExcelIgnore
	@LastModifiedDate
	@ApiModelProperty(hidden = true)
	private Date updatedTime ;

	@ExcelIgnore
	/** 微信 */
	@ApiModelProperty(hidden = true)
	private String weiXin;

	@ManyToOne(fetch = FetchType.LAZY)
	private Course course;

	@ManyToMany(mappedBy = "teachers",fetch = FetchType.LAZY)
	private Set<Clazz> clazzSet = new HashSet<>(0);
	/*@OneToMany(mappedBy = "teacher")
	private List<ClazzCourse> clazzCourses = new ArrayList<>(0);*/

	@OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
	private Set<CourseScore> courseScores = new HashSet<>(0);
}
