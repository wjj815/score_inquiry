package com.wangjj.scoreinquirywxback.excel;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName : AbstractExcelStrategy
 * @Author : wangJJ
 * @Date : 2020/1/23 17:46
 * @Description : 抽象Excel策略类
 */
public abstract class AbstractExcelStrategy<T> {

	private InputStream inputStream;

	private

	AbstractExcelStrategy(InputStream inputStream) {
		this.inputStream = inputStream;
	}



	abstract boolean check();

	abstract List<T> analysis();
}
