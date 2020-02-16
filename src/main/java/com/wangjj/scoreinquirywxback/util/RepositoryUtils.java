package com.wangjj.scoreinquirywxback.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import java.util.List;

/**
 * @ClassName : RepositoryUtils
 * @Author : wangJJ
 * @Date : 2020/2/16 11:33
 * @Description : TODO
 */
@Slf4j
public class RepositoryUtils {


	/*public static <T,T1>PageResult<T> findByPage(Transformable<T1,T> transformable) {
		*//*PageResult<T> pageResult = new PageResult<>();
		Page<T1> t1s = jpaRepository.findAll(specification, pageable);
		PropertyUtils.copyNoNullProperties(t1s,pageResult);
		List<T> convert = PropertyUtils.convert(t1s.getContent(), transformable);
		pageResult.setContent(convert);
		return pageResult;*//*
	}*/

	public static<T> List<T> findListByIdIn(JpaSpecificationExecutor<T> executor, List<Long> ids) {
		log.info("findListByIdIn --| ids:{}",ids);
		return executor.findAll((root, query, criteriaBuilder) -> {
			Path<Object> path = root.get("id");
			CriteriaBuilder.In<Object> in = criteriaBuilder.in(path);
			for (Long aLong : ids) {
				in.value(aLong);
			}
			query.where(criteriaBuilder.and(in));
			return null;
		});

	}
}
