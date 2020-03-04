package com.wangjj.scoreinquirywxback.dao;

import com.wangjj.scoreinquirywxback.pojo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName : StudentRepository
 * @Author : wangJJ
 * @Date : 2019/12/24 23:25
 * @Description : TODO
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {

	List<Student> findAllByClazzId(Long clazzId);
	@Query(value = "select max(id) from t_student",nativeQuery = true)
	long findMaxId();
}
