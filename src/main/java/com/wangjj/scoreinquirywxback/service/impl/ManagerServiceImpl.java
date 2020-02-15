package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.ManagerRepository;
import com.wangjj.scoreinquirywxback.pojo.entity.Manager;
import com.wangjj.scoreinquirywxback.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName : ManagerServiceImpl
 * @Author : wangJJ
 * @Date : 2020/1/3 20:30
 * @Description : TODO
 */
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerRepository managerRepository;



	@Override
	public Manager findById(Long id) {
		return managerRepository.getOne(id);
	}
}
