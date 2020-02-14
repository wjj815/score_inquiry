package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.GradeCourseRepository;
import com.wangjj.scoreinquirywxback.dao.GradeRepository;
import com.wangjj.scoreinquirywxback.entity.Course;
import com.wangjj.scoreinquirywxback.entity.Grade;
import com.wangjj.scoreinquirywxback.entity.GradeCourse;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName : GradeService
 * @Author : wangJJ
 * @Date : 2020/1/26 19:55
 * @Description : 年级业务实现类
 */
@Slf4j
@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private GradeCourseRepository gradeCourseRepository;

	@Override
	public void addGrade(Grade grade) {
		if(gradeRepository.existsById(grade.getId()) || isExist(grade.getGradeName())) {
			throw new GlobalException("该年级已存在");
		}
		gradeRepository.save(grade);
	}

	@Override
	public boolean isExist(String gradeName) {
		return Objects.nonNull(gradeRepository.findByGradeName(gradeName));
	}

	@Override
	public Grade findByGradeName(String gradeName) {
		Grade grade = gradeRepository.findByGradeName(gradeName);
		if(Objects.isNull(grade)) {
			throw new GlobalException("该年级不存在");
		}
		return grade;
	}

	@Override
	public void deleteGradeById(Long gradeId) {
		gradeRepository.deleteById(gradeId);
	}

	@Override
	public List<Grade> findGradeList() {
		return gradeRepository.findAll();
	}

	@Override
	public Page<Grade> findGradePage(Grade grade,Pageable pageable) {
		return gradeRepository.findAll((root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if(Objects.nonNull(grade.getId())) {
				predicates.add(criteriaBuilder.equal(root.get("id"),grade.getId()));
			}

			if(CollectionUtils.isEmpty(predicates)) {
				query.where(predicates.toArray(new Predicate[predicates.size()]));
			}
			return null;
		},pageable);
	}



}
