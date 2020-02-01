package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.MenuRepository;
import com.wangjj.scoreinquirywxback.entity.Menu;
import com.wangjj.scoreinquirywxback.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName : MenuServiceImpl
 * @Author : wangJJ
 * @Date : 2020/1/7 22:22
 * @Description : 菜单业务的实现类
 */
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;


	@Override
	public List<Menu> findMenuList() {
		return null;
	}
}
