package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.Menu;

import java.util.List;

/**
 * @ClassName : MenuService
 * @Author : 1090086767
 * @Date : 2020/1/7 22:18
 * @Description : 菜单业务处理
 */
public interface MenuService {


	List<Menu> findMenuList();
}
