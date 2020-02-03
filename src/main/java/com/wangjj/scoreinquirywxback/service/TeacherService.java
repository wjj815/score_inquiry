package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @ClassName : TeacherService
 * @Author : wangJJ
 * @Date : 2020/2/2 19:58
 * @Description : 教师业务
 */
public interface TeacherService {

	void saveTeacher(Teacher teacher);

	Page<Teacher> getTeacherPage(Pageable pageable);


	void deleteTeacher(Long teacherId);

}
