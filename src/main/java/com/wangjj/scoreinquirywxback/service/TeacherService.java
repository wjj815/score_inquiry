package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.ClazzCourseTeacher;
import com.wangjj.scoreinquirywxback.entity.Teacher;
import com.wangjj.scoreinquirywxback.vo.response.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : TeacherService
 * @Author : wangJJ
 * @Date : 2020/2/2 19:58
 * @Description : 教师业务
 */
public interface TeacherService {

	void saveTeacher(Teacher teacher);

	Page<Teacher> getTeacherPage(Teacher teacher,Pageable pageable);

	void deleteTeacher(String ids);

	String importTeacherList(InputStream inputStream);

	List<Teacher> getTeacherList();

	Teacher getTeacherById(Long teacherId);

	void saveClazzCourse(ClazzCourseTeacher clazzCourseTeacher);

	PageResult<ClazzCourseTeacher> findClazzCoursePageByTeacherId(Long teacherId, Pageable pageable);
}
