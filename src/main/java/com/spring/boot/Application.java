package com.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@SuppressWarnings("unused")
@SpringBootApplication
//@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ComponentScan
//@PropertySource("${oracleDatabase}")
@ImportResource("classpath:springConfig.xml")
public class Application {
	
	public static void main(String args[]){
		
		SpringApplication.run(Application.class, args);
		
		/*ApplicationContext beanInfo = SpringApplication.run(Application.class, args);
		
		for(String bean : beanInfo.getBeanDefinitionNames()){
			System.out.println("Bean name "+bean);
		}*/
	}
	
	//@Bean
	
}
