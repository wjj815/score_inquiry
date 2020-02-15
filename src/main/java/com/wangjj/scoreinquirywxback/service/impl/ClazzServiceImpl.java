package com.wangjj.scoreinquirywxback.service.impl;

import com.wangjj.scoreinquirywxback.dao.ClazzRepository;
import com.wangjj.scoreinquirywxback.dao.GradeRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.ClazzDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.GradeDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.pojo.entity.Clazz;
import com.wangjj.scoreinquirywxback.exception.GlobalException;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
import com.wangjj.scoreinquirywxback.service.ClazzService;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName : ClazzServiceImpl
 * @Author : wangJJ
 * @Date : 2020/1/1 13:32
 * @Description : 班级业务的实现类
 */
@Service
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	private ClazzRepository clazzRepository;
	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public List<ClazzDTO> getClazzList(ClazzDTO clazzDTO) {
		List<Clazz> clazzes = clazzRepository.findAll(getClazzSpecification(clazzDTO));
		List<ClazzDTO> list = new ArrayList<>();
		clazzes.forEach(e -> {
			ClazzDTO dto = new ClazzDTO();
			PropertyUtils.copyNoNullProperties(e,dto);
			dto.setGradeId(e.getGrade().getId());
			list.add(dto);
		});

		return list;
	}

	private Specification<Clazz> getClazzSpecification(ClazzDTO clazz) {
		return (Specification<Clazz>) (root, query, criteriaBuilder) -> {
			List<Predicate> predicateList = new ArrayList<>();

			if (Objects.nonNull(clazz.getGradeId())) {
				predicateList.add(criteriaBuilder.equal(root.get("grade").get("id"), clazz.getGradeId()));
			}

			if (predicateList != null) {
				query.where(predicateList.toArray(new Predicate[predicateList.size()]));
			}
			return null;
		};
	}

	@Override
	public PageResult<ClazzDTO> getClazzListByPage(ClazzDTO clazzDTO, Pageable pageable) {

//		Page<Clazz> page = clazzRepository.findAll(getClazzSpecification(clazz),pageable);
		return null;
	}

	@Override
	public void addClazz(Clazz clazz) {

		/*if(!gradeRepository.existsById(clazz.getGradeId())) {
			throw new GlobalException(String.format("年级编号为%s的年级不存在！",clazz.getGradeId().toString()));
		}*/
		if(isExist(clazz)) {
			throw new GlobalException("班级已存在");
		}
		clazzRepository.save(clazz);
	}

	@Override
	public boolean isExist(Clazz clazz) {
	/*	if(!gradeRepository.existsById(clazz.getGradeId())) {
			throw new GlobalException("年级不存在");
		}
		Clazz c = clazzRepository.findByGradeIdAndClazzName(clazz.getGradeId(), clazz.getClazzName());*/
		return Objects.nonNull(null);
	}

	@Override
	public void deleteClazz(Long id) {
		clazzRepository.deleteById(id);
	}


	@Override
	public Clazz findById(Long id) {
		return clazzRepository.getOne(id);
	}



	@Override
	public void saveClazz(ClazzDTO clazzDTO) {
		if(gradeRepository.existsById(clazzDTO.getGradeId())) {
			throw new GlobalException(String.format("不存在gradeId为%s的年级",clazzDTO.getGradeId()));
		}

		Grade grade = gradeRepository.getOne(clazzDTO.getGradeId());
		Clazz clazz = clazzRepository.existsById(clazzDTO.getId())
				?clazzRepository.getOne(clazzDTO.getId())
				:new Clazz();

		PropertyUtils.copyNoNullProperties(clazzDTO,clazz);
		clazz.setGrade(grade);
		clazzRepository.save(clazz);
	}

}
