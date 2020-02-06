package com.wangjj.scoreinquirywxback.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName : IdParameter
 * @Author : wangJJ
 * @Date : 2020/1/1 16:35
 * @Description : ids参数
 */
@ApiModel(description = "ids的请求参数实体")
@Data
public class IdsParameter {
	@ApiModelProperty(name = "ids",value = "id的集合(,隔开),后面跟个,也没关系",example = "1,2,3,4")
	private String ids;
}
