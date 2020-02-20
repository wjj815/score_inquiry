package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName : ParentRepository
 * @Author : 1090086767
 * @Date : 2019/12/25 16:56
 * @Description : 数据库家长表的操作
 */
public interface ParentRepository extends JpaRepository<Parent,Long>, JpaSpecificationExecutor<Parent> {

}
