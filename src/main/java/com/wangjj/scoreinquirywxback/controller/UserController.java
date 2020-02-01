package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.constant.ResultCode;
import com.wangjj.scoreinquirywxback.entity.User;
import com.wangjj.scoreinquirywxback.service.UserService;
import com.wangjj.scoreinquirywxback.util.SessionUtils;
import com.wangjj.scoreinquirywxback.vo.request.LoginParameter;
import com.wangjj.scoreinquirywxback.vo.response.APIResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @ClassName : UserController
 * @Author : wangJJ
 * @Date : 2020/1/2 17:01
 * @Description : TODO
 */
@Api(description = "用户类")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping("/login")
	@ApiOperation(value = "用户登录", notes = "用户登录")
	@ApiImplicitParam(name = "loginParameter", value = "用户登录参数", dataType = "LoginParameter")
	public APIResultBean login(@RequestBody LoginParameter loginParameter) {
		User user = userService.findByLoginParameter(loginParameter);

		if(user != null) {
			user.toBuilder().password(null);
			SessionUtils.setUser(user);
			log.info("set session that is : {}" ,SessionUtils.getUser());
			return APIResultBean.ok("登录成功",user).build();
		} else {
			return APIResultBean.error(ResultCode.FAILED,"用户名或密码错误！").build();
		}
	}

	@GetMapping("/logout")
	@ApiOperation(value = "用户退出登录", notes = "用户退出登录")
	public APIResultBean logout() {
		SessionUtils.removeUser();
		return APIResultBean.ok("已成功退出登录！").build();
	}



}
