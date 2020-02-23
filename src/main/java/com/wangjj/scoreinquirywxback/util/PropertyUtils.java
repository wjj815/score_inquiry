package com.wangjj.scoreinquirywxback.util;

import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

/**
 * @ClassName : PropertyUtils
 * @Author : wangJJ
 * @Date : 2020/1/29 22:26
 * @Description : 属性工具类
 */
@Slf4j
public class PropertyUtils {

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) emptyNames.add(pd.getName());
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static void copyNoNullProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}





	/**
	 * 转化工具
	 *
	 * @param list
	 * @param transformable
	 * @param <T1>
	 * @param <T2>
	 * @return
	 */
	public static <T1, T2> List<T2> convert(Collection<T1> list, Function<T1, T2> transformable) {
//		log.info("origin list data : {}", list);
		List<T2> result = new ArrayList<>();
		list.forEach(e -> {
			T2 transform = transformable.apply(e);
			result.add(transform);
		});

//		log.info("converted list data : {}", result);
		return result;
	}


	public static <T1, T2> PageResult<T2> convert(Page<T1> page, Function<T1, T2> transformable) {
//		log.info("origin list data : {}", list);
		PageResult<T2> pageResult = new PageResult<>();
		copyNoNullProperties(page, pageResult);
		List<T2> convert = convert(page.getContent(), transformable);
		pageResult.setContent(convert);
//		log.info("converted list data : {}", result);
		return pageResult;
	}


}
