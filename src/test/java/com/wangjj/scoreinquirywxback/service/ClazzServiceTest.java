package com.wangjj.scoreinquirywxback.service;


import com.wangjj.scoreinquirywxback.dao.ClazzRepository;
import com.wangjj.scoreinquirywxback.entity.Clazz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ClazzServiceTest {

	@Autowired
	private ClazzRepository clazzRepository;
	@Test
	public void test() {
		List<Clazz> all = clazzRepository.findAll();
		System.out.println(all);
	}
}