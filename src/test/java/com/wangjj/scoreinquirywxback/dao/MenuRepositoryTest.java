package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.Menu;
import com.wangjj.scoreinquirywxback.pojo.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MenuRepositoryTest {

	@Autowired
	private MenuRepository menuRepository;

	@Test
	public void testFindMenusByRole() {


	}
	@Test
	public void addMenu() {

		List<Menu> list = new ArrayList<>();
		list.add(
				Menu.builder().menuCode("welcome")
						.id(1L)
						.menuIcon("iconfont layui-extendicon_huabanfuben")
						.menuOrder(0)
						.menuName("首页")
						.menuUrl("/welcome")
						.build()
		);
		/*添加菜单*/
		Menu m1 = Menu.builder().menuCode("scoreStatistic")
				.menuIcon("iconfont layui-extendicon_huabanfuben2")
				.menuName("成绩统计分析")
				.id(2L)
				.menuOrder(1)
				.menuUrl("")
				.build();
		list.add(m1);

		Menu m2 = Menu.builder().menuCode("studentManage")
				.menuIcon("iconfont layui-extendappstore-fill")
				.menuName("学生信息管理")
				.id(3L)
				.menuOrder(2)
				.menuUrl("")
				.build();
		list.add(m2);
		list.add(
				Menu.builder().menuCode("studentList")
				.menuIcon("iconfont layui-extendnantongxue")
				.menuOrder(0)
				.id(4L)
				.menuName("学生列表")
				.menuUrl("/student")
				.parentMenu(m2)
				.build()
		);

		Menu m3 = Menu.builder().menuCode("baseInformationManage")
				.menuIcon("iconfont layui-extendlist")
				.menuName("基础信息管理")
				.menuOrder(3)
				.menuUrl("")
				.id(5L)
				.build();
		list.add(m3);
		list.add(Menu.builder().menuCode("clazzList")
				.menuName("年级/班级列表")
				.id(6L)
				.menuIcon("iconfont layui-extendicon_huabanfuben2")
				.menuOrder(0)
				.menuUrl("/clazz")
				.parentMenu(m3)
				.build());
		list.add(Menu.builder().menuCode("courseList")
				.menuName("课程列表")
				.id(7L)
				.menuIcon("iconfont layui-extendtable")
				.menuOrder(0)
				.menuUrl("/course")
				.parentMenu(m3)
				.build());
		Menu m4 = Menu.builder().menuCode("teacherManage")
				.menuIcon("iconfont layui-extendTemp-1")
				.menuName("教师信息管理")
				.id(8L)
				.menuOrder(2)
				.menuUrl("")
				.build();
		list.add(m4);
		list.add(
				Menu.builder().menuCode("teacherList")
						.menuIcon("iconfont layui-extendicon_wode-")
						.menuOrder(0)
						.id(9L)
						.menuName("教师列表")
						.menuUrl("/teacher")
						.parentMenu(m4)
						.build()
		);
		list.add(
				Menu.builder().menuCode("examList")
						.menuIcon("iconfont layui-extendmulu")
						.menuOrder(0)
						.id(10L)
						.menuName("考试列表")
						.menuUrl("/exam")
						.parentMenu(m1)
						.build()
		);
		list.add(
				Menu.builder().menuCode("setting")
						.menuIcon("iconfont layui-extendshezhi-")
						.menuOrder(0)
						.id(11L)
						.menuName("系统设置")
						.menuUrl("/system")
						.build()
		);

	list.forEach(menu -> menu.getRoleList().add(Role.builder().id(1L).build()));
	menuRepository.saveAll(list);
	}
}