package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.CourseDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.request.IdsParameter;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
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
import java.util.Collections;
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
public class CourseService  {

	@Autowired
	private CourseRepository courseRepository;

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

			if(Objects.nonNull(courseDTO.getStudentId())) {
				predicates.add(criteriaBuilder.equal(
						root.joinSet("teachers")
								.joinSet("clazzSet")
								.joinSet("students").get("id")
						,courseDTO.getStudentId()));
			}

			if(Objects.nonNull(courseDTO.getClazzId())) {
				predicates.add(criteriaBuilder.equal(
						root.joinSet("grades").joinSet("clazzes").get("id"),
						courseDTO.getClazzId()
				));
			}


			if(Objects.nonNull(courseDTO.getGradeId())) {
				predicates.add(criteriaBuilder.equal(root.joinSet("grades").get("id"),courseDTO.getGradeId()));
			}



			query.where(predicates.toArray(new Predicate[predicates.size()]));
			return null;
		};
	}

	public List<CourseDTO> findAllByIds(IdsParameter idsParameter) {
		List<Long> analyse = ParameterUtils.analyse(idsParameter.getIds());
		if(analyse.isEmpty())
			return Collections.emptyList();
		List<Course> allById = courseRepository.findAllById(analyse);
		return PropertyUtils.convert(allById,this::getCourseDTO);
	}


	public PageResult<CourseDTO> getCoursePage(CourseDTO courseDTO, Pageable pageable) {
		Page<Course> coursePage = courseRepository.findAll(this.getCourseSpecification(courseDTO), pageable);
		return PropertyUtils.convert(coursePage,this::getCourseDTO);
	}


	@Transactional
	public void saveCourse(CourseDTO courseDTO) {

		Course course;
		if(Objects.nonNull(courseDTO.getId())&&courseRepository.existsById(courseDTO.getId())) {
			course = courseRepository.getOne(courseDTO.getId());
		} else {
			course = new Course();
		}
		PropertyUtils.copyNoNullProperties(courseDTO,course);
		courseRepository.save(course);
	}

	@Transactional
	public void deleteCourse(String courseIds) {
		List<Long> collect = ParameterUtils.analyse(courseIds);
		courseRepository.deleteByIdIn(collect);
	}
}
