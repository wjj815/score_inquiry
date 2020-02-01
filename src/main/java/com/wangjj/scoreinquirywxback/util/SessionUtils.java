package com.wangjj.scoreinquirywxback.util;

import com.wangjj.scoreinquirywxback.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @ClassName : SessionUtils
 * @Author : wangJJ
 * @Date : 2020/1/5 19:30
 * @Description : 操作Session的工具类
 */
@Slf4j
public class SessionUtils {



	private static final String SESSION_USER = "session_user";

	/**
	 * 获取request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return requestAttributes==null? null : requestAttributes.getRequest();
	}

	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession(){
		return getRequest().getSession(true);
	}


	/**
	 * 设置User
	 * @param user user
	 */
	public static void setUser(User user){
		getSession().setAttribute(SESSION_USER, user);
		log.info("add it in session that is {}",user);
	}


	/**
	 * 从session中获取用户信息
	 * @return User
	 */
	public static User getUser(){
		Object obj = getSession().getAttribute(SESSION_USER);
		return Objects.isNull(obj) ? null : (User)obj ;
	}


	/**
	 * 从session中删除用户信息
	 * @return User
	 */
	public static void removeUser(){
		getSession().removeAttribute(SESSION_USER);
	}

}
