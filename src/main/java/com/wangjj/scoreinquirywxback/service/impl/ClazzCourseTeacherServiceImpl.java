package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.ClazzCourseTeacherRepository;
import com.wangjj.scoreinquirywxback.dao.ClazzRepository;
import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.entity.Clazz;
import com.wangjj.scoreinquirywxback.entity.ClazzCourseTeacher;
import com.wangjj.scoreinquirywxback.entity.Course;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.ClazzCourseTeacherService;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import com.wangjj.scoreinquirywxback.vo.response.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : ClazzCourseTeacherServiceImpl
 * @Author : wangJJ
 * @Date : 2020/2/11 22:02
 * @Description : 班级课程教师的业务类实现
 */
@Service
public class ClazzCourseTeacherServiceImpl implements ClazzCourseTeacherService {
	@Autowired
	private ClazzCourseTeacherRepository clazzCourseTeacherRepository;
	@Autowired
	private ClazzRepository clazzRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public void saveClazzCourse(ClazzCourseTeacher clazzCourseTeacher) {
		if(clazzCourseTeacherRepository.exists(Example.of(clazzCourseTeacher))) {
			throw new GlobalException("该班级课程老师已存在！");
		}
		/*判断该班级的课程是否已经有老师了*/
		ClazzCourseTeacher exists = ClazzCourseTeacher.builder()
				.courseId(clazzCourseTeacher.getCourseId())
				.clazzId(clazzCourseTeacher.getClazzId())
				.build();
		if(clazzCourseTeacherRepository.exists(Example.of(exists))) {
			throw new GlobalException("该班级课程已经有老师教授了！");
		}
		clazzCourseTeacherRepository.save(clazzCourseTeacher);
	}


	@Transactional
	@Override
	public PageResult<ClazzCourseTeacher> findClazzCoursePageByTeacherId(Long teacherId, Pageable pageable) {
		ClazzCourseTeacher clazzCourseTeacher = ClazzCourseTeacher.builder().teacherId(teacherId).build();
		Page<ClazzCourseTeacher> list = clazzCourseTeacherRepository.findAll(Example.of(clazzCourseTeacher),pageable);
		PageResult<ClazzCourseTeacher> page = new PageResult<>();
		BeanUtils.copyProperties(list,page);
		List<ClazzCourseTeacher> l = new ArrayList<>();
		list.getContent().forEach(e->{
			Clazz clazz = clazzRepository.getOne(e.getClazzId());
			Course course= courseRepository.getOne(e.getCourseId());
			ClazzCourseTeacher courseTeacher = ClazzCourseTeacher.builder().build();
			BeanUtils.copyProperties(e,courseTeacher);
			courseTeacher.setClazz(clazz);
			courseTeacher.setCourse(course);
			l.add(courseTeacher);
		});
		page.setContent(l);
		return page;
	}

	@Transactional
	@Override
	public void deleteClazzCourseTeacher(String ids) {
		List<Long> list = ParameterUtils.analyse(ids);
		clazzCourseTeacherRepository.deleteByIdIn(list);
	}
}
