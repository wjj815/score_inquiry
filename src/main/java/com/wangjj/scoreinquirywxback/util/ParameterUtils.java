package com.wangjj.scoreinquirywxback.util;

import com.wangjj.scoreinquirywxback.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName : ParameterUtils
 * @Author : wangJJ
 * @Date : 2020/2/6 10:32
 * @Description : 参数的工具类
 */
@Slf4j
public class ParameterUtils {


	public static List<Long> analyse(String ids){

		log.info("original Args:{}",ids);
		String[] split = ids.split(",");
		System.out.println(Arrays.toString(split));
		boolean b = Arrays.stream(split)
				.filter(s->!StringUtils.isEmpty(s))
				.anyMatch(Pattern.compile("^[\\D]+$").asPredicate());// \D == [^0-9]
		if(b) {
			log.error("参数不合法；{}",ids);
			throw new GlobalException("参数不合法！");
		}
		List<Long> collect = Arrays.stream(split)
				.filter(s->!StringUtils.isEmpty(s))
				.map(Long::parseLong)
				.collect(Collectors.toList());
		/*空校验*/
		if(CollectionUtils.isEmpty(collect)) {
			throw new GlobalException("参数不能为空！");
		}
		return collect;
	}
}
