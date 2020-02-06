package com.wangjj.scoreinquirywxback.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 教师类
 *
 */
@ApiModel(description = "教师实体")
@Entity
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_teacher")
public class Teacher {

	@ApiModelProperty(name = "id",value = "教师编号",required = true,example = "2019001")
	@Id
	@GeneratedValue
	private Long id; //Id
	/** 姓名 */
	@ApiModelProperty(name="teacherName",value = "教师姓名",required = true,example = "王老师")
	private String teacherName ;
	/** 性别 */
	@ApiModelProperty(name="sex",value = "性别",required = true,example = "男")
	private String sex ;
	/** 出生日期 */
	@ApiModelProperty(name="birthday",value = "出生日期",example = "1976-2-13",dataType = "Date")
	private Date birthday ;
	/** 身份证号 */
	@ApiModelProperty(name="idCardNo",value = "身份证号",example = "18位数字")
	private String idCardNo ;
	/** 电话 */
	@ApiModelProperty(name = "phone",value = "联系电话", example = "11位手机号")
	private String phone ;
	/** 介绍 */
	@ApiModelProperty(name = "introduction",value = "介绍")
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

	/** 微信 */
	@ApiModelProperty(hidden = true)
	private String weiXin;

}
