package com.wangjj.scoreinquirywxback.excel;


/**
 * @ClassName : ExcelContext
 * @Author : wangJJ
 * @Date : 2020/1/23 17:47
 * @Description : TODO
 */
public class ExcelContext{

	private AbstractExcelStrategy strategy;

	public ExcelContext(AbstractExcelStrategy strategy) {
		this.strategy = strategy;
	}


}
