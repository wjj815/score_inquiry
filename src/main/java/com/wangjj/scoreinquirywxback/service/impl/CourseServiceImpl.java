package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.entity.Course;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName : CourseServiceImpl
 * @Author : wangJJ
 * @Date : 2020/2/4 19:29
 * @Description : TODO
 */
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getCourseList() {
		return courseRepository.findAll((root, query, criteriaBuilder) -> null);
	}

	@Override
	public Page<Course> getCoursePage(Pageable pageable) {
		return courseRepository.findAll(pageable);
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
		log.info("original Args:{}",courseIds);
		String[] split = courseIds.split(",");
		System.out.println(Arrays.toString(split));
		boolean b = Arrays.stream(split)
				.filter(s->!StringUtils.isEmpty(s))
				.anyMatch(Pattern.compile("^[^\\d]+$").asPredicate());
		if(b) {
			throw new GlobalException("参数不合法！");
		}
		List<Long> collect = Arrays.stream(split)
				.filter(s->!StringUtils.isEmpty(s))
				.map(Long::parseLong)
				.collect(Collectors.toList());

		courseRepository.deleteByIdIn(collect);
	}
}
