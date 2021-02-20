package com.nagp.ucp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan({ "com.nagp.ucp" })
@EnableFeignClients
public class UcpServiceBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(UcpServiceBookingApplication.class, args);
	}

}
