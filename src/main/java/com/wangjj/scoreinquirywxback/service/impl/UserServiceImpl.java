package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.UserRepository;
import com.wangjj.scoreinquirywxback.entity.User;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.UserService;
import com.wangjj.scoreinquirywxback.vo.request.LoginParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

/**
 * @ClassName : UserServiceImpl
 * @Author : wangJJ
 * @Date : 2020/1/2 16:59
 * @Description : 用户业务处理
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByLoginParameter(LoginParameter loginParameter) {

		User user = userRepository.findByAccountAndPassword(loginParameter.getAccount(), loginParameter.getPassword());
		if(Objects.isNull(user)) {
			throw new GlobalException("用户名或密码错误！");
		}
		return user;
	}


	@Override
	public User findById(Long id) {
		return userRepository.getOne(id);
	}
}
