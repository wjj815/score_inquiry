package com.wangjj.scoreinquirywxback.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName : GradeDTO
 * @Author : wangJJ
 * @Date : 2020/2/15 16:28
 * @Description : TODO
 */
@Data
public class GradeDTO {

	@ApiModelProperty(name = "id",value = "年级编号",example = "2019")
	private Long id; //ID
	/** 年级名称 */
	@ApiModelProperty(name = "gradeName",value = "年级名称",required = true,example = "2019级")
	private String gradeName ;
}
