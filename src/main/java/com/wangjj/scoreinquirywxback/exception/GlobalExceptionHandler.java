package com.wangjj.scoreinquirywxback.exception;

import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName : GlobalExceptionHandler
 * @Author : wangJJ
 * @Date : 2020/1/25 19:17
 * @Description : 统一异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public APIResultBean handlerException(Exception e) {
		return APIResultBean.error(e.getMessage()).build();
	}
}
