package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.dao.GradeRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.CourseDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.GradeDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.request.GradeCourseParameter;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.GradeService;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
	private CourseRepository courseRepository;


	@Transactional
	@Override
	public void saveGrade(GradeDTO gradeDTO) {

		Grade grade = gradeRepository.existsById(gradeDTO.getId())
				? gradeRepository.getOne(gradeDTO.getId())
				: new Grade();

		PropertyUtils.copyNoNullProperties(gradeDTO,grade);
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

	@Transactional
	@Override
	public void saveGradeCourse(GradeDTO gradeDTO) {

		Grade grade = getGrade(gradeDTO);
		List<Long> analyse = ParameterUtils.analyse(gradeDTO.getCourseIds());
		List<Course> courses = courseRepository.findAllById(analyse);
		courses.forEach(e-> e.getGrades().add(grade));
	}

	private Grade getGrade(GradeDTO gradeDTO) {
		if(!gradeRepository.existsById(gradeDTO.getId())){
			throw new GlobalException("年级不存在");
		}
		return gradeRepository.getOne(gradeDTO.getId());
	}
}
