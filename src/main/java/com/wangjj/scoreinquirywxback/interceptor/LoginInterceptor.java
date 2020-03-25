package com.wangjj.scoreinquirywxback.interceptor;


import com.alibaba.fastjson.JSON;
import com.wangjj.scoreinquirywxback.constant.ResultCode;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import com.wangjj.scoreinquirywxback.util.SessionUtils;
import org.apache.http.entity.ContentType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @ClassName : LoginInterceptor
 * @Author : wangJJ
 * @Date : 2020/1/5 19:21
 * @Description : 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(Objects.isNull(SessionUtils.getUser())) {
				response.setContentType(ContentType.APPLICATION_JSON.toString());
				response.getWriter().write(JSON.toJSONString(APIResultBean.error(ResultCode.FAILED, "该用户未登录").build()));
				response.sendRedirect("/login");

			return false;
		}
		return true;
	}
}
