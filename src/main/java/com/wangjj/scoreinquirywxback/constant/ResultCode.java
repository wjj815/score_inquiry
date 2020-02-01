package com.wangjj.scoreinquirywxback.constant;

/**
 * @ClassName : ResultCode
 * @Author : wangJJ
 * @Date : 2019/12/26 20:42
 * @Description : TODO
 */
public enum ResultCode {

	LOGIN_ERROR(ResultCode.FAILED, "登陆失败，用户名或密码错误");

	private String code;
	private String msg;

	public static final String SUCCESS = "0";
	public static final String FAILED = "1";
	public static final String ERROR = "-1";

	ResultCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
