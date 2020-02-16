package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.TeacherDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.Teacher;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
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

	/**
	 * 增加老师信息
	 * @param teacherDTO 老师信息
	 */
	void saveTeacher(TeacherDTO teacherDTO);

	/**
	 * 获得分页获得教师信息
	 * @param teacherDTO 可通过查询参数进行查询
	 * @param pageable 分页参数
	 * @return 教师信息
	 */
	PageResult<TeacherDTO> getTeacherPage(TeacherDTO teacherDTO,Pageable pageable);

	/**
	 * 通过id批量删除教师信息
	 * @param ids 教师id
	 */
	void deleteTeacher(String ids);

	String importTeacherList(InputStream inputStream);

	List<TeacherDTO> getTeacherList(TeacherDTO teacherDTO);

	Teacher getTeacherById(Long teacherId);

}
