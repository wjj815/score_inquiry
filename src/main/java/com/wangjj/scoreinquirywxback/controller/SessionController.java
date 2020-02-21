package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.constant.ResultCode;
import com.wangjj.scoreinquirywxback.pojo.dto.UserDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.User;
import com.wangjj.scoreinquirywxback.util.SessionUtils;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @ClassName : SessionController
 * @Author : wangJJ
 * @Date : 2020/1/5 22:39
 * @Description : TODO
 */
@Api(description = "会话类")
@RestController
@RequestMapping("/session")
public class SessionController {

	@ApiOperation(value = "获得当前会话的用户")
	@GetMapping("/user")
	public APIResultBean geUserFromSession() {
		UserDTO user = SessionUtils.getUser();
		if(Objects.isNull(user)) {
			return APIResultBean.error(ResultCode.FAILED,"该用户未登录").build();
		}else {
			return APIResultBean.ok(user).build();
		}
	}
}
