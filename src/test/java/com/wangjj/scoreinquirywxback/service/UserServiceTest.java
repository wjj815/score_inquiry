package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.ParentDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;
	@Transactional
	@Test
	public void test() {
		User byId = userService.findById(1L);
		System.out.println(byId);

	}

	@Test
	void addParentUser() {
		ParentDTO parentDTO = new ParentDTO();
		parentDTO.setName("123");
		parentDTO.setPhone("123");
		parentDTO.setWxOpenId("123");
		userService.addParentUser(parentDTO);
	}
}