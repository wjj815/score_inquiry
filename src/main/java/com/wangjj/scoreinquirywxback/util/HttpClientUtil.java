package com.wangjj.scoreinquirywxback.util;

import com.alibaba.fastjson.JSON;
import jdk.net.SocketFlow;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.autoconfigure.http.HttpProperties;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName : HttpClientUtil
 * @Author : wangJJ
 * @Date : 2020/1/3 15:03
 * @Description : get/post请求工具类
 */
@Slf4j
public class HttpClientUtil {




	public static String doGet(String url) {
		return doGet(url,null);
	}
	/**
	 * 发送get请求
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doGet(String url, Map<String, String> param) {
		log.info("GET请求地址:{},请求参数内容:{}",url, JSON.toJSON(param));
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (Objects.nonNull(param)) {
				param.forEach(builder::addParameter);
			}
			URI uri = builder.build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 执行请求
			response = httpclient.execute(httpGet);

			// 判断返回状态是否为200
			if (Objects.equals(response.getStatusLine().getStatusCode(),HttpStatus.SC_OK)) {
				resultString = EntityUtils.toString(response.getEntity(), HttpProperties.Encoding.DEFAULT_CHARSET);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("网络出现异常:{}",e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.error("IO出现异常:{}",e.getMessage());
			}
		}
		log.info("GET响应参数:{}",resultString);
		return resultString;
	}

	/**
	 * 发post请求 传入xml/json字符串
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doPostJson(String url, String json) {
		log.info("POST请求地址:{},请求参数内容:{}",url,json);
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), HttpProperties.Encoding.DEFAULT_CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("网络出现异常:{}",e.getMessage());
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.error("IO出现异常:{}",e.getMessage());
			}
		}
		log.info("POST响应参数:{}",resultString);
		return resultString;
	}

}