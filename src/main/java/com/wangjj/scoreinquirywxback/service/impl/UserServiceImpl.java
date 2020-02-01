package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.UserRepository;
import com.wangjj.scoreinquirywxback.entity.User;
import com.wangjj.scoreinquirywxback.service.UserService;
import com.wangjj.scoreinquirywxback.vo.request.LoginParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @ClassName : UserServiceImpl
 * @Author : wangJJ
 * @Date : 2020/1/2 16:59
 * @Description : TODO
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByLoginParameter(LoginParameter loginParameter) {
		return userRepository.findByAccountAndPassword(loginParameter.getAccount(),loginParameter.getPassword());
	}


	@Override
	public User findById(Long id) {
		return userRepository.getOne(id);
	}
}
