package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName : TeacherRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:04
 * @Description : 教师表类
 */
public interface TeacherRepository  extends JpaRepository<Teacher,Long>, JpaSpecificationExecutor<Teacher> {

	int deleteByIdIn(List<Long> ids);
}
