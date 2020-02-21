package com.wangjj.scoreinquirywxback.pojo.dto.request;

import com.wangjj.scoreinquirywxback.valid.DeleteGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName : IdParameter
 * @Author : wangJJ
 * @Date : 2020/1/1 16:35
 * @Description : ids参数
 */
@ApiModel(description = "ids的请求参数实体")
@Data
public class IdsParameter {

	@NotBlank(message = "请求的ids不能为空！",groups = {DeleteGroup.class})
	@ApiModelProperty(name = "ids",value = "id的集合(,隔开),后面跟个,也没关系",example = "1,2,3,4")
	private String ids;
}
