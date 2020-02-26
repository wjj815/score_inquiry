package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.constant.UserType;
import com.wangjj.scoreinquirywxback.pojo.dto.ParentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.UserDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.User;
import com.wangjj.scoreinquirywxback.service.ManagerService;
import com.wangjj.scoreinquirywxback.service.ParentService;
import com.wangjj.scoreinquirywxback.service.TeacherService;
import com.wangjj.scoreinquirywxback.service.UserService;
import com.wangjj.scoreinquirywxback.util.SessionUtils;
import com.wangjj.scoreinquirywxback.pojo.dto.request.LoginParameter;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private ParentService parentService;

	@Autowired
	private ManagerService managerService;

	@PostMapping("/login")
	@ApiOperation(value = "用户登录", notes = "用户登录")
	@ApiImplicitParam(name = "loginParameter", value = "用户登录参数", dataType = "LoginParameter")
	public APIResultBean login(@RequestBody LoginParameter loginParameter) {
		UserDTO user = userService.findByLoginParameter(loginParameter);
		user.setPassword(null);
		SessionUtils.setUser(user);
//		log.info("set session that is : {}", SessionUtils.getUser());
		return APIResultBean.ok("登录成功", user).build();
}

	@GetMapping("/logout")
	@ApiOperation(value = "用户退出登录", notes = "用户退出登录")
	public APIResultBean logout() {
		SessionUtils.removeUser();
		return APIResultBean.ok("已成功退出登录！").build();
	}

	@PostMapping("/register")
	public APIResultBean registerParent(@RequestBody UserDTO userDTO) {

		if(userDTO.getRoleId().equals(UserType.PARENT.getId())) {

		}

		return APIResultBean.ok().build();
	}

	@GetMapping("/info")
	@ApiOperation(value = "获取用户信息",notes = "获取用户信息")
	public APIResultBean getUserInfo(UserDTO userDTO) {
		if(userDTO.getRoleId().equals(UserType.TEACHER.getId())) {
			return APIResultBean.ok(teacherService.getTeacherById(userDTO.getInfoId())).build();
		} else if(userDTO.getRoleId().equals(UserType.PARENT.getId())) {
			return APIResultBean.ok(parentService.findParentById(userDTO.getInfoId())).build();
		} else if(userDTO.getRoleId().equals(UserType.MANAGER.getId())) {
			return APIResultBean.ok(managerService.findById(userDTO.getInfoId())).build();
		} else {
			return APIResultBean.ok().build();
		}
	}
}
