/*package com.spring.boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
//import javax.sql.DataSource;

@Repository()
public class DataSource {
	
	@Autowired
	javax.sql.DataSource dataSource;
	
	
	@Bean
	public static JdbcTemplate getJdbcTemplate(javax.sql.DataSource dataSource){
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 
		
		return jdbcTemplate;
	}

}
*/