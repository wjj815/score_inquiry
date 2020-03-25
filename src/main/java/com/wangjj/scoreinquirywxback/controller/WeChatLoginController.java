package com.wangjj.scoreinquirywxback.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangjj.scoreinquirywxback.util.AesCbcUtil;
import com.wangjj.scoreinquirywxback.util.HttpClientUtil;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import com.wangjj.scoreinquirywxback.util.HttpUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : WeiXinController
 * @Author : wangJJ
 * @Date : 2020/2/2 17:44
 * @Description : TODO
 */
@Slf4j
@Api(value = "微信类")
@RestController
@RequestMapping("/wechat")
public class WeChatLoginController {

	@Value("${wechat.appid}")
	private String appid;
	@Value("${wechat.appsecret}")
	private String appsecret;

	public String GETTOKEN = "" +
			"https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

	public String USERINFO = "" +
			"https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	public String GETACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
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
				GETTOKEN.replaceAll("JSCODE", code)
						.replaceAll("SECRET",appsecret)
						.replaceAll("APPID",appid)
		);
		Map map = JSON.parseObject(token);
		//获取到了关键的令牌和openid后，
		//就可以正式开始查询微信用户的信息，完成我们要做的微信绑定
		String s = HttpClientUtil.doGet(GETACCESS_TOKEN.replaceAll("SECRET", appsecret)
				.replaceAll("APPID", appid));

		String access_token = (String) JSONObject.parseObject(s).get("access_token");
		String openid = (String) map.get("openid");
		String userInfo = HttpClientUtil.doGet(USERINFO.replaceAll("ACCESS_TOKEN", access_token).replaceAll("OPENID", openid));
		log.info("userInfo:{}",userInfo);
		Map info = JSON.parseObject(userInfo);
		return APIResultBean.ok(info).build();
//		return new ModelAndView("personal","userInfo",info);
	}


	@GetMapping(value = "/decodeUserInfo")
	public APIResultBean decodeUserInfo(String encryptedData, String iv, String code) {

		Map<String,Object> map = new HashMap<>();
		//登录凭证不能为空
		if (code == null || code.length() == 0) {
			return APIResultBean.error("code不能为空").build();
		}

		//小程序唯一标识   (在微信小程序管理后台获取)
		String wxspAppid = appid;
		//小程序的 app secret (在微信小程序管理后台获取)
		String wxspSecret = appsecret;
		//授权（必填）
		String grant_type = "authorization_code";

		//////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
		//请求参数
		String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
		//发送请求
		String sr = HttpClientUtil.doGet("https://api.weixin.qq.com/sns/jscode2session?"+params);
		//解析相应内容（转换成json对象）
		JSONObject json = JSON.parseObject(sr);
		//获取会话密钥（session_key）
		String session_key = json.get("session_key").toString();
		//用户的唯一标识（openid）
		String openid = (String) json.get("openid");

		//////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
		try {
			JSONObject userInfoJSON = AesCbcUtil.getUserInfo(encryptedData, session_key, iv);
			log.info("解密后的信息为：{}",userInfoJSON);
			if (null != userInfoJSON) {
				Map<String,Object> userInfo = new HashMap<>();
				userInfo.put("openId", userInfoJSON.get("openId"));
				userInfo.put("nickName", userInfoJSON.get("nickName"));
				userInfo.put("gender", userInfoJSON.get("gender"));
				userInfo.put("city", userInfoJSON.get("city"));
				userInfo.put("province", userInfoJSON.get("province"));
				userInfo.put("country", userInfoJSON.get("country"));
				userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
				userInfo.put("unionId", userInfoJSON.get("unionId"));
				map.put("userInfo", userInfo);
				log.info("decodeUserInfo:{}",map);
				return APIResultBean.ok("解密成功",userInfo).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return APIResultBean.error("解密失败").build();
	}

}