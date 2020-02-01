package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.entity.StudentParent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName : StudentParentRepository
 * @Author : wangJJ
 * @Date : 2020/1/29 22:47
 * @Description : 学生家长实体类操作
 */
public interface StudentParentRepository extends JpaRepository<StudentParent,Long> {


	List<StudentParent> findByStudentId(Long studentId);

	boolean existsByStudentIdAndAndParentId(Long studentId,Long parentId);
}
