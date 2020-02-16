package com.wangjj.scoreinquirywxback.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName : CourseDTO
 * @Author : wangJJ
 * @Date : 2020/2/15 16:10
 * @Description : TODO
 */
@Data
public class CourseDTO {

	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue
	private Long id; //ID
	/** 课程名 */
	@ApiModelProperty(name = "courseName",value = "课程名称",example = "语文")
	private String courseName ;
	/** 课程说明 */
	@ApiModelProperty(name = "introduction",value = "课程介绍",example = "关于汉语的学习")
	private String introduction ;

	private Long gradeId;
}
