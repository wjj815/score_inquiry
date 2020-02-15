package com.wangjj.scoreinquirywxback.pojo.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName : PageParameter
 * @Author : wangJJ
 * @Date : 2020/1/1 17:00
 * @Description : TODO
 */
@Data
@ApiModel(description = "分页参数")
public class PageParameter {
	@ApiModelProperty(value = "分页参数:页码(从1开始)", name = "page",dataType = "int",required = true, example = "0")
	private int page;
	@ApiModelProperty(value = "分页参数:页大小", name = "size",dataType = "int", required = true, example = "10")
	private int size;
}
