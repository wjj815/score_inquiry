package com.wangjj.scoreinquirywxback.controller;


import com.alibaba.fastjson.JSON;
import com.wangjj.scoreinquirywxback.util.HttpClientUtil;
import com.wangjj.scoreinquirywxback.vo.response.APIResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @ClassName : WeiXinController
 * @Author : wangJJ
 * @Date : 2020/2/2 17:44
 * @Description : TODO
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class WeChatLoginController {

	@Value("${wechat.appid}")
	private String appid;
	@Value("${wechat.appsecret}")
	private String appsecret;

	public String GETTOKEN = "" +
			"https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	public String USERINFO = "" +
			"https://api.weixin.qq.com/sns/userinfo?access_token=TOKEN&openid=OPENID";
	/**
	 * 用户扫描确认登录后的回调执行，此处应对应redirect_uri
	 * 注意：要保证回调域名和当前网络环境通畅，如果想要本地测试，
	 * 可以修改hosts文件映射一下本地和回调域名，保证回调是调的本地这个方法↓↓↓↓
	 * @param code

	 * @return
	 */
	@GetMapping("/callBack")
	public APIResultBean callBack(@RequestParam String code){
		//回调获得code，通过用户授权的code去获取微信令牌
		String token = HttpClientUtil.doGet(
				GETTOKEN.replaceAll("CODE", code)
						.replaceAll("SECRET",appsecret)
						.replaceAll("APPID",appid)
		);
		Map map = JSON.parseObject(token);
		//获取到了关键的令牌和openid后，
		//就可以正式开始查询微信用户的信息，完成我们要做的微信绑定
		String access_token = (String) map.get("access_token");
		String openid = (String) map.get("openid");
		String userInfo = HttpClientUtil.doGet(USERINFO.replaceAll("TOKEN", access_token).replaceAll("OPENID", openid));
		Map info = JSON.parseObject(userInfo);
		return APIResultBean.ok(info).build();
//		return new ModelAndView("personal","userInfo",info);
	}

}