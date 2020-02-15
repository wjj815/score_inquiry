package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.pojo.dto.CourseDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.GradeDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.request.GradeCourseParameter;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
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

	void saveGrade(GradeDTO gradeDTO);

	boolean isExist(String gradeName);

	Grade findByGradeName(String gradeName);

	void deleteGradeById(Long gradeId);

	List<Grade> findGradeList();

	Page<Grade> findGradePage(Grade grade, Pageable pageable);

	void saveGradeCourse(GradeCourseParameter gradeCourseParameter);

	List<CourseDTO> findGradeCourse(GradeCourseParameter gradeCourseParameter);
}
