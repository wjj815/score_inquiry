package com.wangjj.scoreinquirywxback.config;

import com.wangjj.scoreinquirywxback.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : WebConfig
 * @Author : wangJJ
 * @Date : 2020/1/5 19:21
 * @Description : Web的相关配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private String apiPrefix = "/api";
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/login",apiPrefix+"/user/login",
						apiPrefix+"/wechat/decodeUserInfo",
						apiPrefix+"/user/wxLogin",
						"/**/register",
						"/static/**",
						"/v2/**",
//						"/swagger-ui.html",
						"/doc.html",
						"/swagger-resources/**",
						"/webjars/**");

	}

	/**
	 * 修改springboot中默认的静态文件路径
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//addResourceHandler请求路径
		//addResourceLocations 在项目中的资源路径
		//setCacheControl 设置静态资源缓存时间
	/*	registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");*/
		registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("/api",aClass -> aClass.isAnnotationPresent(RestController.class));
	}
}
