package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.ClazzCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName : ClazzCourseRepository
 * @Author : wangJJ
 * @Date : 2020/2/15 16:03
 * @Description : 班级课程的实体
 */
public interface ClazzCourseRepository extends JpaRepository<ClazzCourse,Long>,JpaSpecificationExecutor<ClazzCourse> {
}
