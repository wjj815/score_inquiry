package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
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

}