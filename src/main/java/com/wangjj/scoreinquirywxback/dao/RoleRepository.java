package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName : RoleRepository
 * @Author : wangJJ
 * @Date : 2020/2/24 10:17
 * @Description : TODO
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
