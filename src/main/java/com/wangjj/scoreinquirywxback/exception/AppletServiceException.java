package com.wangjj.scoreinquirywxback.exception;

/**
 * @ClassName : AppletServiceException
 * @Author : wangJJ
 * @Date : 2020/1/3 15:01
 * @Description : 小程序服务端异常类
 */
public class AppletServiceException extends RuntimeException {
	/**
	 * 错误码
	 */
	private Integer errorCode;
	/**
	 * 错误描述
	 */
	private String errorMsg;

	/**
	 * @param errorCode
	 * @param errorMsg
	 */
	public AppletServiceException(Integer errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
