package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.pojo.dto.ClazzDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.Clazz;
import com.wangjj.scoreinquirywxback.service.ClazzService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

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

	@Test
	void getClazzList() {
		ClazzDTO clazzDTO = new ClazzDTO();
		List<ClazzDTO> clazzList = clazzService.getClazzList(clazzDTO);
		System.out.println(clazzList);
	}

	@Test
	void saveClazz() {

		ClazzDTO clazzDTO = new ClazzDTO();
		clazzDTO.setId(1L);
		clazzDTO.setGradeId(2019L);
		clazzDTO.setClazzName("1班");
		clazzService.saveClazz(clazzDTO);
	}

	@Test
	void getClazzList1() {
	}

	@Test
	void getClazzListByPage() {
	}

	@Test
	void addClazz1() {
	}

	@Test
	void isExist() {
	}

	@Test
	void deleteClazz() {
		clazzService.deleteClazz("1");
	}

	@Test
	void findById() {
	}

	@Test
	void saveClazz1() {
	}

	@Test
	void saveClazzTeacher() {

//		clazzService.saveClazzTeacher(1L,2019002L);
	}
}