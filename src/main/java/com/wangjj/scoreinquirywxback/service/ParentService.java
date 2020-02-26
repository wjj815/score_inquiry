package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.constant.UserType;
import com.wangjj.scoreinquirywxback.dao.ParentRepository;
import com.wangjj.scoreinquirywxback.dao.RoleRepository;
import com.wangjj.scoreinquirywxback.dao.StudentRepository;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.dto.ParentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Parent;
import com.wangjj.scoreinquirywxback.pojo.entity.Role;
import com.wangjj.scoreinquirywxback.pojo.entity.Student;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @ClassName : ParentService
 * @Author : wangJJ
 * @Date : 2020/2/20 14:00
 * @Description : 家长的服务类
 */
@Service
public class ParentService {

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Transactional
	public void saveParent(ParentDTO parentDTO) {

		Parent parent;
		if(Objects.nonNull(parentDTO.getId()) && parentRepository.existsById(parentDTO.getId())) {
			parent = parentRepository.getOne(parentDTO.getId());
		} else {
			parent = new Parent();
		}
		PropertyUtils.copyNoNullProperties(parentDTO,parent);
		Role role = roleRepository.getOne(UserType.PARENT.getId());
		parent.setRole(role);
		parentRepository.save(parent);

		/*Parent savedParent = parentRepository.save(parent);
		if(Objects.isNull(id)) {
			log.info("添加学生和家长的关联信息");
			StudentParent sp = StudentParent.builder()
					.studentId(studentId)
					.parentId(savedParent.getId()).build();
			studentParentRepository.save(sp);
		}*/
	}

	@Transactional
	public void removeRelevanceOfStudentAndParent(Long studentId, Long parentId) {

		check(studentId, parentId);
		Student student = studentRepository.getOne(studentId);
		Parent parent = parentRepository.getOne(parentId);
//		student.getParents().remove(parent);
		parent.getStudents().remove(student);
	}

	private void check(Long studentId, Long parentId) {
		if (!studentRepository.existsById(studentId)) {
			throw new GlobalException(String.format("不存在学号为%s的学生", studentId));
		}
		if (!parentRepository.existsById(parentId)) {
			throw new GlobalException(String.format("不存在该家长信息！"));
		}
	}


	@Transactional
	public void saveRelevanceOfStudentAndParent(Long studentId, Long parentId) {

		check(studentId, parentId);
		Student student = studentRepository.getOne(studentId);
		Parent parent = parentRepository.getOne(parentId);
		parent.getStudents().add(student);
//		parentRepository.save(parent);
		/*Parent savedParent = parentRepository.save(parent);
		if(Objects.isNull(id)) {
			log.info("添加学生和家长的关联信息");
			StudentParent sp = StudentParent.builder()
					.studentId(studentId)
					.parentId(savedParent.getId()).build();
			studentParentRepository.save(sp);
		}*/
	}

	@Transactional
	public void deleteParent(Long parentId) {
		if(!parentRepository.existsById(parentId)) {
			throw new GlobalException("该家长不存在");
		}
		Parent parent = parentRepository.getOne(parentId);
		parent.getStudents().forEach(e->e.getParents().remove(parent));
		parentRepository.deleteById(parentId);
	}


	public List<ParentDTO> findParentList(ParentDTO parentDTO) {
		List<Parent> parents = parentRepository.findAll(getParentSpecification(parentDTO));
		return PropertyUtils.convert(parents,this::getParentDTO);
	}

	public ParentDTO findParentById(Long parentId) {
		if(!parentRepository.existsById(parentId)) {
			throw new GlobalException("家长不存在");
		}
		Parent parent = parentRepository.getOne(parentId);
		return getParentDTO(parent);
	}

	private ParentDTO getParentDTO(Parent parent) {
		ParentDTO dto = new ParentDTO();
		PropertyUtils.copyNoNullProperties(parent,dto);
		return dto;
	}

	private Specification<Parent> getParentSpecification(ParentDTO parentDTO) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if(Objects.nonNull(parentDTO.getId())) {
				predicates.add(criteriaBuilder.equal(root.get("id"),parentDTO.getId()));
			}

			if(Objects.nonNull(parentDTO.getName())) {
				predicates.add(criteriaBuilder.like(root.get("name"),parentDTO.getName()+"%"));
			}

			if(Objects.nonNull(parentDTO.getStudentId())) {
				predicates.add(criteriaBuilder.equal(root.joinSet("students").get("id"),
						parentDTO.getStudentId()));
			}
			return null;
		};
	}

	public PageResult<ParentDTO> findParentPage(ParentDTO parentDTO, Pageable pageable) {
		Page<Parent> parents = parentRepository.findAll(getParentSpecification(parentDTO), pageable);
		return PropertyUtils.convert(parents,this::getParentDTO);
	}


}
