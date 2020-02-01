package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ClassName : GradeService
 * @Author : wangJJ
 * @Date : 2020/1/26 19:54
 * @Description : 年级业务类
 */
public interface GradeService {

	void addGrade(Grade grade);

	boolean isExist(String gradeName);

	Grade findByGradeName(String gradeName);

	void deleteGradeById(Long gradeId);

	List<Grade> findGradeList();

	Page<Grade> findGradePage(Grade grade, Pageable pageable);
}
