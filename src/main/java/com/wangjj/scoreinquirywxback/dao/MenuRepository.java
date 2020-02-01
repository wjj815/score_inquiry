package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName : MenuRepository
 * @Author : wangJJ
 * @Date : 2020/1/7 14:01
 * @Description : 菜单的数据库操作
 */
public interface MenuRepository extends JpaRepository<Menu,Long> {
}
