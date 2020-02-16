package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.ParentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.StudentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Parent;
import com.wangjj.scoreinquirywxback.pojo.entity.Student;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : StudentService
 * @Author : wangJJ
 * @Date : 2019/12/24 23:27
 * @Description : 学生的业务
 */
public interface StudentService {

	void removeRelevanceOfStudentAndParent(Long studentId, Long parentId);

	/**
	 * 绑定学生和家长信息
	 * @param parentId
	 */
	void saveRelevanceOfStudentAndParent(Long studentId, Long parentId);

	@Transactional
	void saveParent(ParentDTO parentDTO);

	/**
	 * 增加学生信息
	 * @param studentDTO
	 */
	void saveStudent(StudentDTO studentDTO);

	/**
	 * 删除学生信息
	 * @param studentId
	 */
	void deleteStudent(Long studentId);


	void deleteParent(Long parentId);

	List<StudentDTO> findStudent(StudentDTO studentDTO);

	PageResult<StudentDTO> findStudent(StudentDTO student, Pageable pageable);

	Student findStudentById(Long id);

	String importStudentList(InputStream inputStream);

	List<ParentDTO> findParentOfStudent(Long studentId);

	List<StudentDTO> findStudentOfParent(Long parentId);

	Parent findParentByStudentIdAndParentId(Long studentId, Long parentId);
}
