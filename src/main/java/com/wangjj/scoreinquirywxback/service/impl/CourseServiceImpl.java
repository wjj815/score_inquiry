package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.CourseDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.CourseService;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName : CourseServiceImpl
 * @Author : wangJJ
 * @Date : 2020/2/4 19:29
 * @Description : 课程业务的实现类
 */
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<CourseDTO> getCourseList(CourseDTO courseDTO) {
		List<Course> courses = courseRepository.findAll(getCourseSpecification(courseDTO));
		return PropertyUtils.convert(courses, this::getCourseDTO);
	}

	private CourseDTO getCourseDTO(Course u) {
		CourseDTO dto = new CourseDTO();
		PropertyUtils.copyNoNullProperties(u,dto);
		return dto;
	}

	private Specification<Course> getCourseSpecification(CourseDTO courseDTO) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if(Objects.nonNull(courseDTO.getId())) {
				predicates.add(criteriaBuilder.equal(root.get("id"),courseDTO.getId()));
			}

			if(Objects.nonNull(courseDTO.getGradeId())) {
				predicates.add(criteriaBuilder.equal(root.joinSet("grades").get("id"),courseDTO.getGradeId()));
			}

			query.where(predicates.toArray(new Predicate[predicates.size()]));
			return null;
		};
	}

	@Override
	public PageResult<CourseDTO> getCoursePage(CourseDTO courseDTO, Pageable pageable) {
		Page<Course> coursePage = courseRepository.findAll(this.getCourseSpecification(courseDTO), pageable);
		return PropertyUtils.convert(coursePage,this::getCourseDTO);
	}

	@Override
	public void saveCourse(Course course) {
		if(courseRepository.exists(Example.of(course))) {
			throw new GlobalException("课程已存在！");
		}

		courseRepository.save(course);
	}

	@Transactional
	@Override
	public void deleteCourse(String courseIds) {
		List<Long> collect = ParameterUtils.analyse(courseIds);
		courseRepository.deleteByIdIn(collect);
	}
}
