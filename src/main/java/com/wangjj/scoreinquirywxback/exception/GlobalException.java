package com.wangjj.scoreinquirywxback.exception;

import com.wangjj.scoreinquirywxback.constant.ResultCode;
import com.wangjj.scoreinquirywxback.vo.response.APIResultBean;

/**
 * @ClassName : GlobalException
 * @Author : wangJJ
 * @Date : 2020/1/23 17:27
 * @Description : 全局异常
 */
public class GlobalException extends RuntimeException {


	public GlobalException(String msg) {
		super(msg);
	}

	public APIResultBean getResult() {
		return APIResultBean.error(ResultCode.ERROR,this.getMessage()).build();
	}
}
