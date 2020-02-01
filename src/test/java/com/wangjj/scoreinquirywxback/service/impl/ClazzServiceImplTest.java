package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.entity.Clazz;
import com.wangjj.scoreinquirywxback.service.ClazzService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ClazzServiceImplTest {

	@Autowired
	private ClazzService clazzService;

	@Transactional
	@Test
	void addClazz() {
		for (int i = 0; i < 10; i++) {
			Clazz c = new Clazz();

		}

	}
}