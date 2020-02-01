package com.wangjj.scoreinquirywxback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课程类
 *
 */
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_course")
public class Course {
	@Id
	@GeneratedValue
	private Long id; //ID
	/** 课程名 */
	private String courseName ;
	/** 课程说明 */
	private String introduction ;
	/** 创建人 */
	private String createdBy ;
	/** 创建时间 */
	private Date createdTime ;
	/** 更新人 */
	private String updatedBy ;
	/** 更新时间 */
	private Date updatedTime ;
}
