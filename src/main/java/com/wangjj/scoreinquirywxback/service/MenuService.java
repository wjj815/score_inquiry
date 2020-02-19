package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.dao.MenuRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.MenuDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName : MenuService
 * @Author : wangJJ
 * @Date : 2020/1/7 22:22
 * @Description : 菜单业务的实现类
 */
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;


	public List<Menu> findMenuList(MenuDTO menuDTO) {
		return null;
	}
}
