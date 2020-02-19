package com.wangjj.scoreinquirywxback.pojo.dto;

import com.wangjj.scoreinquirywxback.valid.AddGroup;
import com.wangjj.scoreinquirywxback.valid.DeleteGroup;
import com.wangjj.scoreinquirywxback.valid.FindGroup;
import com.wangjj.scoreinquirywxback.valid.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName : GradeDTO
 * @Author : wangJJ
 * @Date : 2020/2/15 16:28
 * @Description : TODO
 */
@ApiModel(description = "年级类")
@Data
public class GradeDTO {

	@NotNull(groups = {AddGroup.class, DeleteGroup.class, UpdateGroup.class},
			message = "年级编号不能为空")
	@ApiModelProperty(name = "id",value = "年级编号,([添加，删除，更改]（必须）)",example = "2019")
	private Long id; //ID
	/** 年级名称 */
	@NotBlank(groups = {AddGroup.class},message = "年级名称不能为空")
	@ApiModelProperty(name = "gradeName",value = "年级名称:([添加]（必须）)",example = "2019级")
	private String gradeName ;

	@NotBlank(groups = {AddGroup.class,UpdateGroup.class},message = "年级课程不能为空")
	@ApiModelProperty(name = "courseIds",value = "课程ids,为年级的课程([添加，更改]（必须）)}")
	private String courseIds;
}
