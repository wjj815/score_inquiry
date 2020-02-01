package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName : GradeRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:00
 * @Description : 年级表的操作
 */
public interface GradeRepository extends JpaRepository<Grade,Long>, JpaSpecificationExecutor<Grade> {

	Grade findByGradeName(String gradeName);
}
