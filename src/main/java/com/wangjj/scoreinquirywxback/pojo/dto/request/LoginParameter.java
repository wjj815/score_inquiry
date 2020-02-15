package com.wangjj.scoreinquirywxback.pojo.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName : LoginParameter
 * @Author : wangJJ
 * @Date : 2019/12/26 20:40
 * @Description : 登录的参数
 */

@ApiModel(description = "登录参数")
@Data
public class LoginParameter implements Serializable {

	/**
	 * 用户账号
	 */
	@ApiModelProperty(value = "登录账号(学生,教师为工号)", name = "account", required = true, example = "admin")
	@NotBlank(message = "账号不允许为空,请输入")
	private String account;
	/**
	 * 用户密码
	 */
	@ApiModelProperty(value = "登录密码", name = "password", required = true, example = "123456")
	@NotBlank(message = "密码不允许为空,请输入")
	private String password;

	/**
	 * 用户类型
	 */
	@ApiModelProperty(value = "用户类型", name = "type", required = true,example = "0",notes ="0:管理员,1:学生,2:老师,3:家长")
	@NotBlank(message = "请选择用户类型")
	private Integer type;

}
