package com.wangjj.scoreinquirywxback.util;

import com.wangjj.scoreinquirywxback.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @ClassName : HttpUtils
 * @Author : wangJJ
 * @Date : 2020/1/25 18:20
 * @Description : 上传文件的工具类
 */
@Slf4j
public class HttpUtils {


	public static HttpServletResponse getExcelResponse(String filename) {

		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = Objects.requireNonNull(requestAttributes).getResponse();
		Objects.requireNonNull(response).setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		try {
			// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
			String fileName = URLEncoder.encode(filename, "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new GlobalException("导出Excel异常");
		}
		return response;
	}
}

