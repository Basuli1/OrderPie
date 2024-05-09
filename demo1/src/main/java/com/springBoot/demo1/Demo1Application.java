package com.springBoot.demo1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.demo1.models.Pie;
import com.springBoot.demo1.services.PieService;

@SpringBootApplication
public class Demo1Application {
	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}
	
	
	@Bean
	public CommandLineRunner inspectorBean(ApplicationContext ctx) {
		return args->{
			System.out.printf("inspecting beans provided by spring boot in %s",appName).println();
			
			String[] beans = ctx.getBeanDefinitionNames();  //get all bean definition
			Arrays.sort(beans);
			for(String bean:beans) {
				System.out.println(bean);
			}
			System.out.println("ended");
		};
	}

}
