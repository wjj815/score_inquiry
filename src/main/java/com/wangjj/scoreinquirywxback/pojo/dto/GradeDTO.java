package com.wangjj.scoreinquirywxback.pojo.dto;

import com.wangjj.scoreinquirywxback.valid.AddGroup;
import com.wangjj.scoreinquirywxback.valid.DeleteGroup;
import com.wangjj.scoreinquirywxback.valid.FindGroup;
import com.wangjj.scoreinquirywxback.valid.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName : GradeDTO
 * @Author : wangJJ
 * @Date : 2020/2/15 16:28
 * @Description : TODO
 */
@Data
public class GradeDTO {

	@NotNull(groups = {AddGroup.class, DeleteGroup.class, UpdateGroup.class, FindGroup.class},
			message = "年级编号不能为空")
	@ApiModelProperty(name = "id",value = "年级编号",example = "2019")
	private Long id; //ID
	/** 年级名称 */
	@NotBlank(groups = {AddGroup.class})
	@ApiModelProperty(name = "gradeName",value = "年级名称",required = true,example = "2019级")
	private String gradeName ;
}
