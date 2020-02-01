package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.Clazz;
import com.wangjj.scoreinquirywxback.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ClassName : ClazzService
 * @Author : 1090086767
 * @Date : 2019/12/25 17:28
 * @Description : 班级的业务
 */
public interface ClazzService {

	/**
	 * 通过年级id获取年级下的班级信息
	 * @param clazz
	 * @return
	 */
	List<Clazz> getClazzList(Clazz clazz);

	Page<Clazz> getClazzListByPage(Clazz clazz, Pageable pageable);

	/**
	 * 添加班级
	 * @param clazz
	 */
	void addClazz(Clazz clazz);

	boolean isExist(Clazz clazz);

	/**
	 * 删除班级
	 * @param id
	 */
	void deleteClazz(Long id);

	void updateClazz(Clazz clazz);

	Clazz findById(Long id);
}
