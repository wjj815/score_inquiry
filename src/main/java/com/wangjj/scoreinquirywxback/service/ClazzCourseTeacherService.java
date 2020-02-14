package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.ClazzCourseTeacher;
import com.wangjj.scoreinquirywxback.vo.response.PageResult;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

/**
 * @ClassName : ClazzCourseTeacherService
 * @Author : wangJJ
 * @Date : 2020/2/11 22:02
 * @Description : 班级课程教师的业务类
 */
public interface ClazzCourseTeacherService {


	void saveClazzCourse(ClazzCourseTeacher clazzCourseTeacher);


	PageResult<ClazzCourseTeacher> findClazzCoursePageByTeacherId(Long teacherId, Pageable pageable);

	void deleteClazzCourseTeacher(String ids);
}
