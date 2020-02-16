package com.wangjj.scoreinquirywxback.exception;

import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName : GlobalExceptionHandler
 * @Author : wangJJ
 * @Date : 2020/1/25 19:17
 * @Description : 统一异常处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public APIResultBean handlerException(Exception e) {
		return APIResultBean.error(e.getMessage()).build();
	}


	/**
	 * 参数校验异常处理
	 * @param ex 异常
	 * @return
	 */
	@ExceptionHandler(value = {BindValidationException.class, MethodArgumentNotValidException.class})
	public APIResultBean handlerArgumentValidException( MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		StringBuilder stringBuffer = new StringBuilder();
		if(bindingResult.hasErrors()){
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				//该格式仅仅作为response展示和log作用，前端应自己做校验
				stringBuffer.append(fieldError.getObjectName())
						.append("--")
						.append(fieldError.getDefaultMessage())
						.append(" ");
			}
		}
		log.error("参数校验错误：",stringBuffer.toString());
		return APIResultBean.error(stringBuffer.toString()).build();
	}
}
