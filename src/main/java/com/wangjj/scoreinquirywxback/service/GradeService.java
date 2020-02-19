package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.dao.GradeRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.GradeDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : GradeService
 * @Author : wangJJ
 * @Date : 2020/1/26 19:55
 * @Description : 年级业务实现类
 */
@Slf4j
@Service
public class GradeService  {

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private CourseRepository courseRepository;


	@Transactional
	public void saveGrade(GradeDTO gradeDTO) {

		boolean exist = gradeRepository.existsById(gradeDTO.getId());
		Grade grade = exist
				? gradeRepository.getOne(gradeDTO.getId())
				: new Grade();

		PropertyUtils.copyNoNullProperties(gradeDTO,grade);
		/*deleteGradeCourse(grade);
		saveGradeCourse(grade,gradeDTO.getCourseIds());*/
		List<Long> analyse = ParameterUtils.analyse(gradeDTO.getCourseIds());
		if(CollectionUtils.isEmpty(analyse)) {
			throw new GlobalException("年级课程不能为空！");
		}

		List<Course> courses = courseRepository.findAllById(analyse);
		boolean isUpdate = true;
		/*如果课程没有发生更改则不更改课程*/
		if(exist) {
			List<Course> originCourses = grade.getCourses();
			if(Arrays.equals(courses.toArray(),originCourses.toArray())) {
				isUpdate = false;
			}
		}
			/*重新设置课程*/
		if(isUpdate) {
			grade.setCourses(courses);
		}
		log.info("saveGrade---->课程isUpdate:{}",isUpdate);
		gradeRepository.save(grade);
		/*每次更改年级信息的时候都会移除然后再插入*/

	}

	public boolean isExist(String gradeName) {
		return Objects.nonNull(gradeRepository.findByGradeName(gradeName));
	}

	public Grade findByGradeName(String gradeName) {
		Grade grade = gradeRepository.findByGradeName(gradeName);
		if(Objects.isNull(grade)) {
			throw new GlobalException("该年级不存在");
		}
		return grade;
	}

	@Transactional
	public void deleteGradeById(Long gradeId) {
		if(!gradeRepository.existsById(gradeId)){
			throw new GlobalException("年级不存在");
		}
		Grade grade = gradeRepository.getOne(gradeId);
		/*删除年级与课程的关联*/
		grade.setCourses(new ArrayList<>());
		gradeRepository.save(grade);
		/*删除年级*/
		gradeRepository.delete(grade);
	}

	@Transactional
	public void deleteGradeCourse(Long gradeId) {
		Grade grade = gradeRepository.getOne(gradeId);
		/*删除年级与课程的关联*/
		grade.setCourses(new ArrayList<>());
		gradeRepository.save(grade);
	}


	public List<GradeDTO> findGradeList(GradeDTO gradeDTO) {
		List<Grade> grades = gradeRepository.findAll(getGradeSpecification(gradeDTO));
		return PropertyUtils.convert(grades,this::getGradeDTO);
	}

	public PageResult<GradeDTO> findGradePage(GradeDTO gradeDTO, Pageable pageable) {
		Page<Grade> grades = gradeRepository.findAll(getGradeSpecification(gradeDTO), pageable);
		return PropertyUtils.convert(grades, this::getGradeDTO);
	}

	private GradeDTO getGradeDTO(Grade u) {
		GradeDTO gradeDTO1 = new GradeDTO();
		PropertyUtils.copyNoNullProperties(u,gradeDTO1);
		String collect = u.getCourses().stream()
				.map(e->String.valueOf(e.getId()))
				.collect(Collectors.joining(","));
		gradeDTO1.setCourseIds(collect);
		return gradeDTO1;
	}

	private Specification<Grade> getGradeSpecification(GradeDTO gradeDTO) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if(Objects.nonNull(gradeDTO.getId())) {
				predicates.add(criteriaBuilder.equal(root.get("id"),gradeDTO.getId()));
			}

			if(CollectionUtils.isEmpty(predicates)) {
				query.where(predicates.toArray(new Predicate[predicates.size()]));
			}
			return null;
		};
	}


	private Grade getGrade(GradeDTO gradeDTO) {
		if(!gradeRepository.existsById(gradeDTO.getId())){
			throw new GlobalException("年级不存在");
		}
		return gradeRepository.getOne(gradeDTO.getId());
	}
}
