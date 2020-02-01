package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName : ClazzRepository
 * @Author : 1090086767
 * @Date : 2020/1/1 13:27
 * @Description : TODO
 */
public interface ClazzRepository extends JpaRepository<Clazz,Long>, JpaSpecificationExecutor<Clazz> {

	Clazz findByGradeIdAndClazzName(Long gradeId,String ClazzName);
}
