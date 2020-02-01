package com.wangjj.scoreinquirywxback.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.InputStream;

/**
 * @ClassName : EasyExcelUtils
 * @Author : wangJJ
 * @Date : 2020/1/26 9:13
 * @Description : easyExcel的工具类
 */
public class EasyExcelUtils {


	public static <T> String  readExcel(InputStream stream, Class<T> aClass,AnalysisEventListener<T> eventListener) {
		/**/EasyExcel.read(stream, aClass, eventListener).sheet().doReadSync();
		return null;
	}


}
