package com.wangjj.scoreinquirywxback.pojo.dto;

import com.wangjj.scoreinquirywxback.valid.AddGroup;
import com.wangjj.scoreinquirywxback.valid.DeleteGroup;
import com.wangjj.scoreinquirywxback.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName : ClazzDTO
 * @Author : wangJJ
 * @Date : 2020/2/15 17:08
 * @Description : TODO
 */
@Data

public class ClazzDTO {

	@NotNull(message = "班级编号不能为空",groups = {AddGroup.class, UpdateGroup.class, DeleteGroup.class})
	private Long id;

	@NotBlank(message = "班级名称不能为空",groups = {AddGroup.class})
	private String clazzName;
	@NotNull(message = "年级编号不能为空",groups = {AddGroup.class,UpdateGroup.class,DeleteGroup.class})
	private Long gradeId;

	private Long teacherId;
}
