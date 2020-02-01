package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.User;
import com.wangjj.scoreinquirywxback.vo.request.LoginParameter;

/**
 * @ClassName : UserService
 * @Author : wangJJ
 * @Date : 2020/1/2 16:58
 * @Description : TODO
 */
public interface UserService {

	/**
	 * 登录
	 * @param loginParameter 登录参数
	 * @return 返回用户
	 */
	User findByLoginParameter(LoginParameter loginParameter);


	User findById(Long id);
}
