package com.wangjj.scoreinquirywxback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName : Swagger2Config
 * @Author : wangJJ
 * @Date : 2019/12/26 20:21
 * @Description : TODO
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	//api接口包扫描路径
	private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.wangjj.scoreinquirywxback";

	private static final String VERSION = "1.0.0";

	//读取yml文件配置
	@Value("${swagger2.enable}")
	private boolean swagger2Enable;

	/**
	 * .enable()  控制是否进行初始化
	 * .select()  初始化并返回一个API选择构造器
	 * .paths(PathSelectors.any())   设置路径筛选器
	 * .apis(RequestHandlerSelectors.basePackage("com.xxx.xxx.xxx"))  添加路径选择条件
	 * .build();    构建
	 *
	 * PathSelectors 类的方法：
	 *  - Predicate<String> any():满足条件的路径，该断言总为true
	 *  - Predicate<String> none():不满足条件的路径,该断言总为false
	 *  - Predicate<String> regex(final String pathRegex):符合正则的路径
	 *
	 * RequestHandlerSelectors 类的方法：
	 *  - Predicate<RequestHandler> any()：返回包含所有满足条件的请求处理器的断言，该断言总为true
	 *  - Predicate<RequestHandler> none()：返回不满足条件的请求处理器的断言,该断言总为false
	 *  - Predicate<RequestHandler> basePackage(final String basePackage)：返回一个断言(Predicate)，该断言包含所有匹配basePackage下所有类的请求路径的请求处理器
	 */

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(swagger2Enable)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
				.paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
				.build();
	}
/*Swagger 通过注解定制接口对外展示的信息，这些信息包括接口名、请求方法、参数、返回信息等。更多注解类型：

@Api：修饰整个类，描述Controller的作用
@ApiOperation：描述一个类的一个方法，或者说一个接口
@ApiParam：单个参数描述
@ApiModel：用对象来接收参数
@ApiProperty：用对象接收参数时，描述对象的一个字段
@ApiResponse：HTTP响应其中1个描述
@ApiResponses：HTTP响应整体描述
@ApiIgnore：使用该注解忽略这个API
@ApiError ：发生错误返回的信息
@ApiImplicitParam：描述一个请求参数，可以配置参数的中文含义，还可以给参数设置默认值
@ApiImplicitParams：描述由多个 @ApiImplicitParam 注解的参数组成的请求参数列表
*/
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("学生成绩管理系统") //设置文档的标题
				.description("学生成绩管理系统 API 接口文档") // 设置文档的描述
				.version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
				.termsOfServiceUrl("http://www.baidu.com") // 设置文档的License信息->1.3 License information
				.build();
	}

}
