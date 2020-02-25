package com.wangjj.scoreinquirywxback.excel;

import com.wangjj.scoreinquirywxback.constant.UserType;
import com.wangjj.scoreinquirywxback.dao.RoleRepository;
import com.wangjj.scoreinquirywxback.dao.UserRepository;
import com.wangjj.scoreinquirywxback.pojo.entity.Role;
import com.wangjj.scoreinquirywxback.pojo.entity.Teacher;
import com.wangjj.scoreinquirywxback.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName : TeacherDataListener
 * @Author : wangJJ
 * @Date : 2020/2/6 17:49
 * @Description : easyExcel的数据导入监听
 */
public class TeacherDataListener extends BaseDataListener<Teacher> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public TeacherDataListener(JpaRepository<Teacher, Long> jpaRepository) {
		super(jpaRepository);
	}

	@Override
	protected void wrapperData(Teacher data) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(data.getBirthday());
//		controllerLog.info("{}当前年份, {} 生日年份， {}相差年份",LocalDate.now().getYear(), );
		data.setAge(LocalDate.now().getYear() - instance.get(Calendar.YEAR));
		Role r = roleRepository.getOne(UserType.TEACHER.getId());
		data.setRole(r);
	}

	@Override
	public void otherSave(List<Teacher> list) {
		List<User> users = new ArrayList<>();
		list.forEach(e-> {
			User user = new User();
			user.setId(e.getId());
			user.setName(e.getTeacherName());
			user.setRole(e.getRole());
			user.setAccount(e.getId()+"");
			user.setInfoId(e.getId());
			users.add(user);
		});
		userRepository.saveAll(users);
	}
}
