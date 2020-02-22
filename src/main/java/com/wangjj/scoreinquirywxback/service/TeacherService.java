package com.wangjj.scoreinquirywxback.service;

import com.alibaba.excel.EasyExcel;
import com.wangjj.scoreinquirywxback.dao.ClazzRepository;
import com.wangjj.scoreinquirywxback.dao.CourseRepository;
import com.wangjj.scoreinquirywxback.dao.TeacherRepository;
import com.wangjj.scoreinquirywxback.excel.TeacherDataListener;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.dto.TeacherDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Clazz;
import com.wangjj.scoreinquirywxback.pojo.entity.Course;
import com.wangjj.scoreinquirywxback.pojo.entity.Teacher;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ClazzRepository clazzRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Transactional
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

	public PageResult<TeacherDTO> getTeacherPage(TeacherDTO teacherDTO,Pageable pageable) {

		Page<Teacher> teacherPage = teacherRepository.findAll(getTeacherSpecification(teacherDTO), pageable);
		/*将查询出来的数据实体转化为数据传输对象*/
		return PropertyUtils.convert(teacherPage, this::getTeacherDTO);
	}

	private Specification<Teacher> getTeacherSpecification(TeacherDTO teacherDTO) {

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

			if(Objects.nonNull(teacherDTO.getClazzId())) {

				predicateList.add(criteriaBuilder.equal(root.joinSet("clazzSet").get("id"),teacherDTO.getClazzId()));
			}

			query.where(predicateList.toArray(new Predicate[predicateList.size()]));
			return null;
		};
	}

	@Transactional
	public void deleteTeacher(String ids) {
		List<Long> list = ParameterUtils.analyse(ids);
		List<Teacher> teachers = teacherRepository.findAllById(list);
		teachers.forEach(e-> e.getClazzSet().forEach(c->{
			c.getTeachers().remove(e);
		}));
		teacherRepository.deleteByIdIn(list);
	}

	public String importTeacherList(InputStream inputStream) {

		log.info("导入教师信息excel");
		TeacherDataListener teacherDataListener = new TeacherDataListener(teacherRepository);
		EasyExcel.read(inputStream, Teacher.class, teacherDataListener)
				.sheet().doRead();
		return teacherDataListener.getResult();

	}

	@Transactional
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
		if(Objects.nonNull(u.getCourse())) {
			teacherDTO1.setCourseId(u.getCourse().getId());
		}
		return teacherDTO1;
	}

	@Transactional
	public void saveClazzTeacher(TeacherDTO teacherDTO){
		if(!clazzRepository.existsById(teacherDTO.getClazzId())) {
			throw new GlobalException("班级不存在");
		}
		if(!teacherRepository.existsById(teacherDTO.getId())) {
			throw new GlobalException("老师不存在");
		}
		TeacherDTO dto = new TeacherDTO();
		dto.setClazzId(teacherDTO.getClazzId());
		dto.setCourseId(teacherDTO.getCourseId());
		List<Teacher> teacherList = teacherRepository.findAll(getTeacherSpecification(dto));
		/*if(!CollectionUtils.isEmpty(teacherList)) {
			throw new GlobalException("该班级老师已存在");
		}*/

		Clazz clazz = clazzRepository.getOne(teacherDTO.getClazzId());
		clazz.getTeachers().removeAll(teacherList);
		Teacher teacher = teacherRepository.getOne(teacherDTO.getId());
		clazz.getTeachers().add(teacher);
	}

	public TeacherDTO getTeacherById(Long teacherId) {
		return getTeacherDTO(teacherRepository.getOne(teacherId));
	}

}
