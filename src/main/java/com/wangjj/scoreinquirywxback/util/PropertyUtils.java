package com.wangjj.scoreinquirywxback.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * @ClassName : PropertyUtils
 * @Author : wangJJ
 * @Date : 2020/1/29 22:26
 * @Description : 属性工具类
 */
@Slf4j
public class PropertyUtils {

	public static String[] getNullPropertyNames (Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for(java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) emptyNames.add(pd.getName());
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static void copyNoNullProperties(Object source, Object target) {
		BeanUtils.copyProperties(source,target,getNullPropertyNames(source));
	}


	public static <T> List<T> convert(Collection<?> list, Class<T> aClass) {
//		log.info("origin list data : {}", list);
		List<T> result = new ArrayList<>();
		list.forEach(e -> {
			try {
				T t = aClass.newInstance();
				BeanUtils.copyProperties(e,t,getNullPropertyNames(e));
//				log.info("---{}",t.toString());
				result.add(t);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		});

//		log.info("converted list data : {}", result);
		return result;
	}
}
