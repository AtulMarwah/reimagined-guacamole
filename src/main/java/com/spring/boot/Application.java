package com.spring.boot;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SuppressWarnings("unused")
@SpringBootApplication
//@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ComponentScan
//@PropertySource("${oracleDatabase}")
@ImportResource("classpath:springConfig.xml")
public class Application {
	
	public static void main(String args[]){
		
		InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
            System.out.println("LoopBack Address "+ InetAddress.getLoopbackAddress().getHostAddress());
            //InetAddress.getLoopbackAddress().getHostName();
            SpringApplication.run(Application.class, args);
 
        } catch (UnknownHostException e) {
 
            e.printStackTrace();
        }
		
		/*ApplicationContext beanInfo = SpringApplication.run(Application.class, args);
		
		for(String bean : beanInfo.getBeanDefinitionNames()){
			System.out.println("Bean name "+bean);
		}*/
	}
	
	//@Bean
	
}
