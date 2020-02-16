package com.wangjj.scoreinquirywxback.service.impl;

import com.alibaba.excel.EasyExcel;
import com.wangjj.scoreinquirywxback.dao.ClazzCourseTeacherRepository;
import com.wangjj.scoreinquirywxback.dao.ClazzRepository;
import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.dao.TeacherRepository;
import com.wangjj.scoreinquirywxback.excel.TeacherDataListener;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.dto.TeacherDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Clazz;
import com.wangjj.scoreinquirywxback.pojo.entity.ClazzCourseTeacher;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.pojo.entity.Teacher;
import com.wangjj.scoreinquirywxback.service.TeacherService;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import com.wangjj.scoreinquirywxback.util.RepositoryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
	public void saveTeacher(TeacherDTO teacherDTO) {

		if(!courseRepository.existsById(teacherDTO.getCourseId())) {
			throw new GlobalException("课程不存在");
		}

		Course course = courseRepository.getOne(teacherDTO.getCourseId());
		Teacher teacher = teacherRepository.existsById(teacherDTO.getId()) ?
				teacherRepository.getOne(teacherDTO.getId()) : new Teacher();
		PropertyUtils.copyNoNullProperties(teacherDTO,teacher);
		teacher.setCourse(course);
		teacherRepository.save(teacher);
	}

	@Override
	public PageResult<TeacherDTO> getTeacherPage(TeacherDTO teacherDTO,Pageable pageable) {

		Page<Teacher> teacherPage = teacherRepository.findAll(getTeacherSpecification(teacherDTO), pageable);
		/*将查询出来的数据实体转化为数据传输对象*/
		PageResult<TeacherDTO> convert = PropertyUtils.convert(teacherPage, u -> {
			TeacherDTO teacherDTO1 = getTeacherDTO(u);
			return teacherDTO1;
		});
		return convert;
	}

	private Specification<Teacher> getTeacherSpecification(TeacherDTO teacherDTO) {
		List<Long> clazzIds = ParameterUtils.analyse(teacherDTO.getClazzId());
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicateList = new ArrayList<>();

			if(Objects.nonNull(teacherDTO.getId())) {
				predicateList.add(criteriaBuilder.equal(root.get("id"),teacherDTO.getId()));
			}

			if(Objects.nonNull(teacherDTO.getTeacherName())) {
				predicateList.add(criteriaBuilder.like(root.get("teacherName"),teacherDTO.getTeacherName()+"%"));
			}

			if(Objects.nonNull(teacherDTO.getCourseId())) {
				predicateList.add(criteriaBuilder.equal(root.get("course").get("id"),teacherDTO.getCourseId()));
			}

			if(!CollectionUtils.isEmpty(clazzIds)) {

				Path<Object> objectPath = root.joinSet("clazzSet").get("id");
				CriteriaBuilder.In<Object> in = criteriaBuilder.in(objectPath);
				for (Long clazzId : clazzIds) {
					in.value(clazzId);
				}
				predicateList.add(criteriaBuilder.and(in));
			}

			query.where(predicateList.toArray(new Predicate[predicateList.size()]));
			return null;
		};
	}

	@Transactional
	@Override
	public void deleteTeacher(String ids) {
		List<Long> list = ParameterUtils.analyse(ids);
		List<Teacher> teachers = RepositoryUtils.findListByIdIn(teacherRepository, list);
		teachers.forEach(e->{
			e.getClazzSet().forEach(c->{
				c.getTeachers().remove(e);
			});
		});
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
	public List<TeacherDTO> getTeacherList(TeacherDTO teacherDTO) {
		List<Teacher> teachers = teacherRepository.findAll(getTeacherSpecification(teacherDTO));
		List<TeacherDTO> convert = PropertyUtils.convert(teachers, u -> {
			TeacherDTO teacherDTO1 = getTeacherDTO(u);
			return teacherDTO1;
		});
		return convert;
	}

	private TeacherDTO getTeacherDTO(Teacher u) {
		TeacherDTO teacherDTO1 = new TeacherDTO();
		PropertyUtils.copyNoNullProperties(u, teacherDTO1);
		teacherDTO1.setCourseId(u.getCourse().getId());
		return teacherDTO1;
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
