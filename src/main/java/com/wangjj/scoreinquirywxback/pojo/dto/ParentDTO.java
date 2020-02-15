package com.wangjj.scoreinquirywxback.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName : ParentDTO
 * @Author : wangJJ
 * @Date : 2020/2/15 20:32
 * @Description : TODO
 */
@Data
public class ParentDTO {


	@ApiModelProperty(hidden = true)
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
}
