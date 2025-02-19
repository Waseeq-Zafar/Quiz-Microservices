package com.waseeq.eureka_service_reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceRegApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceRegApplication.class, args);
	}

}
