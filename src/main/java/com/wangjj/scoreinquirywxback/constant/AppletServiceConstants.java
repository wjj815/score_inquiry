package com.wangjj.scoreinquirywxback.constant;

/**
 * @ClassName : AppletServiceConstants
 * @Author : wangJJ
 * @Date : 2020/1/3 14:59
 * @Description : 微信小程序服务端常量
 */
public class AppletServiceConstants {


	//TODO:服务端Api
	/**
	 * 小程序登录API
	 */
	public static final String AUTH_CODE_API = "https://api.weixin.qq.com/sns/jscode2session";
	/**
	 * accessToken授权API
	 */
	public static final String ACCESS_TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token";

	/**
	 * 模板消息URL
	 */
	public static String TEMPLATE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

	/**
	 * accessToken缓存key
	 */
	public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	/**
	 * accessToken缓存过期时间
	 */

	public static final Integer EXPIRES_IN  = 7000;


	//TODO：请求状态码
	public static final Integer PARAM_ERROR_CODE = 400;

	public static final Integer EXCEPTION_ERROR_CODE = 500;

	public static final Integer SUCCESS_CODE = 0;


	//TODO: 异常信息状态

	public static final String CODE_NULL_ERROR = "微信授权码code不为空";

	public static final String  AUTHORIZATION_ERROR = "未获取到openId,授权失败";

	public static final String ENCTYP_EXECPTION = "微信小程序手机号码解密异常";

	public static final String ACCESS_TOKRN_ERROR = "小程序授权获取accessToken失败";

}