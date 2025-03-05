package com.blubin.identityservice;

import com.blubin.commonservice.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.blubin.identityservice",
											"com.blubin.commonservice",
											"com.blubin.userservice"})
@EnableConfigurationProperties(CorsConfig.class)
public class IdentityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityServiceApplication.class, args);
	}

}
