package com.wangjj.scoreinquirywxback.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 教师类
 *
 */
@Entity
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_teacher")
public class Teacher {
	@Id
	@GeneratedValue
	private String id; //Id
	/** 姓名 */
	private String teacherName ;
	/** 性别 */
	private String gender ;
	/** 出生日期 */
	private Date birthday ;
	/** 身份证号 */
	private String idCardNo ;
	/** 电话 */
	private String mobilePhone ;
	/** 介绍 */
	private String introduction ;
	/** 创建人 */
	private String createdBy ;
	/** 创建时间 */
	private Date createdTime ;
	/** 更新人 */
	private String updatedBy ;
	/** 更新时间 */
	private Date updatedTime ;

	/** 微信 */
	private String weiXin;

}
