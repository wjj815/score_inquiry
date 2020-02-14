package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.ClazzCourseTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ClassName : ClazzCourseTeacherRepository
 * @Author : wangJJ
 * @Date : 2020/2/8 16:52
 * @Description : 教师班级课程数据库操作
 */
public interface ClazzCourseTeacherRepository extends JpaRepository<ClazzCourseTeacher,Long>, JpaSpecificationExecutor<ClazzCourseTeacher> {

	List<ClazzCourseTeacher> findByTeacherId(Long id);

	int deleteByIdIn(List<Long> ids);

	int deleteByCourseIdIn(List<Long> ids);
}
