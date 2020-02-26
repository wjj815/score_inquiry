package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.MenuDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceTest {

	@Autowired
	private MenuService menuService;
	@Test
	void findMenuList() {
		MenuDTO menuDTO = new MenuDTO();
		menuDTO.setRoleId(1L);
//		List<MenuDTO> menuList = menuService.findMenuList(menuDTO);
		List<MenuDTO> menuDTOS = menuService.bulidMenuTree(menuDTO);
		System.out.println(menuDTOS);
	}
}