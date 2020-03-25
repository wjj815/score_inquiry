package com.wangjj.scoreinquirywxback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
@SpringBootApplication
public class ScoreInquiryWxBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoreInquiryWxBackApplication.class, args);
	}

}
