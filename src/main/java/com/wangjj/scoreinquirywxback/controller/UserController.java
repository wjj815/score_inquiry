package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.constant.UserType;
import com.wangjj.scoreinquirywxback.pojo.dto.ParentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.UserDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.request.PageParameter;
import com.wangjj.scoreinquirywxback.pojo.dto.request.WXLoginParameter;
import com.wangjj.scoreinquirywxback.pojo.entity.User;
import com.wangjj.scoreinquirywxback.service.ManagerService;
import com.wangjj.scoreinquirywxback.service.ParentService;
import com.wangjj.scoreinquirywxback.service.TeacherService;
import com.wangjj.scoreinquirywxback.service.UserService;
import com.wangjj.scoreinquirywxback.util.SessionUtils;
import com.wangjj.scoreinquirywxback.pojo.dto.request.LoginParameter;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import com.wangjj.scoreinquirywxback.valid.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

	@PostMapping("/wxLogin")
	@ApiOperation(value = "使用openId来微信登录",notes = "微信登录")
	public APIResultBean wxLogin(@RequestBody WXLoginParameter wxLoginParameter) {
		UserDTO userDTO = userService.findByWxLoginParameter(wxLoginParameter);
		SessionUtils.setUser(userDTO);
		return APIResultBean.ok("登录成功",userDTO).build();
	}

	@GetMapping("/logout")
	@ApiOperation(value = "用户退出登录", notes = "用户退出登录")
	public APIResultBean logout() {
		SessionUtils.removeUser();
		return APIResultBean.ok("已成功退出登录！").build();
	}

	@PostMapping("/parent/register")
	@ApiOperation(value = "家长注册", notes = "家长注册")
	public APIResultBean registerParent(@RequestBody @Validated(AddGroup.class) ParentDTO parentDTO) {
		userService.addParentUser(parentDTO);
		return APIResultBean.ok().build();
	}

	@GetMapping("/info")
	@ApiOperation(value = "获取用户信息",notes = "获取用户信息")
	public APIResultBean getUserInfo(UserDTO userDTO) {
		if(Objects.equals(userDTO.getRoleId(),UserType.TEACHER.getId())) {
			return APIResultBean.ok(teacherService.getTeacherById(userDTO.getId())).build();
		} else if(Objects.equals(userDTO.getRoleId(),UserType.PARENT.getId())) {
			return APIResultBean.ok(parentService.findParentById(userDTO.getId())).build();
		} else if(Objects.equals(userDTO.getRoleId(),UserType.MANAGER.getId())) {
			return APIResultBean.ok(managerService.findById(userDTO.getId())).build();
		} else {
			return APIResultBean.ok().build();
		}
	}

	@GetMapping("/list")
	@ApiOperation(value = "获得用户列表")
	public APIResultBean getUserList(UserDTO userDTO) {
		List<UserDTO> userList = userService.getUserList(userDTO);
		return APIResultBean.ok(userList).build();
	}

	@GetMapping("/page")
	@ApiOperation(value = "分页获得用户信息")
	public APIResultBean getUserList(UserDTO userDTO, PageParameter pageParameter) {
		return APIResultBean.ok(userService.getUserPage(userDTO, PageRequest.of(pageParameter.getPage() - 1,pageParameter.getLimit()))).build();
	}
}
