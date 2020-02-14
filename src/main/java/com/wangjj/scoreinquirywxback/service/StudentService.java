package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.Parent;
import com.wangjj.scoreinquirywxback.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : StudentService
 * @Author : wangJJ
 * @Date : 2019/12/24 23:27
 * @Description : 学生的业务
 */
public interface StudentService {

	/**
	 * 增加学生的家长信息
	 * @param parent
	 */
	void saveStudentParent(Long studentId, Parent parent);

	/**
	 * 增加学生信息
	 * @param student
	 */
	void saveStudent(Student student);


	/**
	 * 删除学生信息
	 * @param ids
	 */
	void deleteStudent(String ids);


	List<Student> findStudent(Student student);

	Page<Student> findStudent(Student student, Pageable pageable);

	Student findStudentById(Long id);

	String importStudentList(InputStream inputStream);

	List<Parent> findStudentParent(Long studentId);

	Parent findParentByStudentIdAndParentId(Long studentId,Long parentId);
}
