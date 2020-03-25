package com.wangjj.scoreinquirywxback.pojo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName : ParentDTO
 * @Author : wangJJ
 * @Date : 2020/2/15 20:32
 * @Description : TODO
 */
@ApiModel(description = "家长类")
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
	/** 身份证号 */
	@ApiModelProperty( name = "idCardNo",value = "家长身份证号")
	private String idCardNo ;
	/** 籍贯 */
	@ApiModelProperty( name = "domicilePlace",value = "籍贯",required = true)
	private String domicilePlace ;
	/** 居住地址 */
	@ApiModelProperty( name = "presentAddress",value = "居住地址",required = true)
	private String presentAddress ;
	private Long studentId;

	@ApiModelProperty( name = "wxOpenId",value = "微信openId")
	private String wxOpenId;

	private String avatar;
}
