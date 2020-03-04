package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName : UserRepository
 * @Author : wangJJ
 * @Date : 2020/1/2 16:56
 * @Description : TODO
 */
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

	/**
	 * 通过账户名和密码查询用户
	 * @param account 账户
	 * @param password 密码
	 * @return 用户
	 */
	User findByAccountAndPassword(String account, String password);
}
