package com.wangjj.scoreinquirywxback.excel;

import com.wangjj.scoreinquirywxback.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * @ClassName : TeacherDataListener
 * @Author : wangJJ
 * @Date : 2020/2/6 17:49
 * @Description : easyExcel的数据导入监听
 */
public class TeacherDataListener extends BaseDataListener<Teacher> {


	public TeacherDataListener(JpaRepository<Teacher, Long> jpaRepository) {
		super(jpaRepository);
	}

	@Override
	protected void wrapperData(Teacher data) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(data.getBirthday());
//		controllerLog.info("{}当前年份, {} 生日年份， {}相差年份",LocalDate.now().getYear(), );
		data.setAge(LocalDate.now().getYear() - instance.get(Calendar.YEAR));
	}
}
