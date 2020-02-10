package com.wangjj.scoreinquirywxback.service.impl;
import	java.util.ArrayList;

import com.alibaba.excel.EasyExcel;
import com.wangjj.scoreinquirywxback.dao.ClazzCourseTeacherRepository;
import com.wangjj.scoreinquirywxback.dao.ClazzRepository;
import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.dao.TeacherRepository;
import com.wangjj.scoreinquirywxback.entity.Clazz;
import com.wangjj.scoreinquirywxback.entity.ClazzCourseTeacher;
import com.wangjj.scoreinquirywxback.entity.Course;
import com.wangjj.scoreinquirywxback.entity.Teacher;
import com.wangjj.scoreinquirywxback.excel.TeacherDataListener;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.service.TeacherService;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import com.wangjj.scoreinquirywxback.vo.response.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : TeacherService
 * @Author : wangJJ
 * @Date : 2020/2/6 10:26
 * @Description : 老师业务的实现类
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ClazzCourseTeacherRepository clazzCourseTeacherRepository;
	@Autowired
	private ClazzRepository clazzRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Transactional
	@Override
	public void saveTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
	}

	@Override
	public Page<Teacher> getTeacherPage(Teacher teacher,Pageable pageable) {
		return teacherRepository.findAll(Example.of(teacher),pageable);
	}

	@Transactional
	@Override
	public void deleteTeacher(String ids) {
		List<Long> list = ParameterUtils.analyse(ids);
		teacherRepository.deleteByIdIn(list);
	}

	@Override
	public String importTeacherList(InputStream inputStream) {

		log.info("导入教师信息excel");
		TeacherDataListener teacherDataListener = new TeacherDataListener(teacherRepository);
		EasyExcel.read(inputStream, Teacher.class, teacherDataListener)
				.sheet().doRead();
		return teacherDataListener.getResult();

	}

	@Transactional
	@Override
	public List<Teacher> getTeacherList() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher getTeacherById(Long teacherId) {
		return teacherRepository.getOne(teacherId);
	}


	@Override
	public void saveClazzCourse(ClazzCourseTeacher clazzCourseTeacher) {
		if(clazzCourseTeacherRepository.exists(Example.of(clazzCourseTeacher))) {
			throw new GlobalException("该班级课程老师已存在！");
		}
		clazzCourseTeacherRepository.save(clazzCourseTeacher);
	}

	public void delete(Long id) {
		clazzCourseTeacherRepository.deleteById(id);
	}

	@Transactional
	@Override
	public PageResult<ClazzCourseTeacher> findClazzCoursePageByTeacherId(Long teacherId, Pageable pageable) {
		ClazzCourseTeacher clazzCourseTeacher = ClazzCourseTeacher.builder().teacherId(teacherId).build();
		Page<ClazzCourseTeacher> list = clazzCourseTeacherRepository.findAll(Example.of(clazzCourseTeacher),pageable);
		PageResult<ClazzCourseTeacher> page = new PageResult<>();
		BeanUtils.copyProperties(list,page);
		List<ClazzCourseTeacher> l = new ArrayList<> ();
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
}
