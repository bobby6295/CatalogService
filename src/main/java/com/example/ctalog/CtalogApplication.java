package com.example.ctalog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
public class CtalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtalogApplication.class, args);
	}


}
