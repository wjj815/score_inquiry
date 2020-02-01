package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName : TeacherRepository
 * @Author : wangJJ
 * @Date : 2020/1/21 20:04
 * @Description : 教师表类
 */
public interface TeacherRepository  extends JpaRepository<Teacher,Long> {
}
