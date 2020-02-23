package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.constant.UserType;
import com.wangjj.scoreinquirywxback.dao.UserRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.UserDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.Parent;
import com.wangjj.scoreinquirywxback.pojo.entity.Teacher;
import com.wangjj.scoreinquirywxback.pojo.entity.User;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.dto.request.LoginParameter;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
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
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDTO findByLoginParameter(LoginParameter loginParameter) {

		User user = userRepository.findByAccountAndPassword(loginParameter.getAccount(), loginParameter.getPassword());
		if(Objects.isNull(user)) {
			throw new GlobalException("用户名或密码错误！");
		}
		return getUserDTO(user);
	}

	private UserDTO getUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		PropertyUtils.copyNoNullProperties(user,userDTO);
		userDTO.setRoleIds(user.getRole().getId()+"");
		return userDTO;
	}

	@Transactional
	public void addTeacherUser(Teacher teacher) {
		User user = new User();
		user.setAccount(teacher.getId()+"");
		user.setPassword("123456");
		user.setInfoId(teacher.getId());
		user.setRole(teacher.getRole());
		userRepository.save(user);
	}

	@Transactional
	public void addParentUser(User user,Parent parent) {
		user.setRole(parent.getRole());
	}

	public User findById(Long id) {
		return userRepository.getOne(id);
	}
}
