package com.spring.boot.daoTwo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class AdhayanDAOTwoImpl implements AdhayanDAOTwo{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String insertOrUpdateDate(final int val) {
		
		final String query = "MERGE INTO NOKIA nkia USING(SELECT COUNT(*) id_availb FROM NOKIA "
				+ "WHERE PHONEID = ?) temp ON (temp.id_availb > 0) WHEN NOT MATCHED THEN "
				+ "INSERT VALUES(phoneid_seq.nextval,NVL(?"
				+ ",'NOKIA'),NVL(?,10000),NVL(?,2),systimestamp at time zone 'utc') "
				+ "WHEN MATCHED THEN UPDATE SET PHONENAME = LogiTECHNokia WHERE PHONEID = ?";
		//:phoneID
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int key = 0;
		
		key = jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				// TODO Auto-generated method stub
				
				PreparedStatement preparedStatement = arg0.prepareStatement(query.toString(),
                        new String[]{"PHONEID"}/*Statement.RETURN_GENERATED_KEYS*/); 
				preparedStatement.setInt(1, val);
				preparedStatement.setString(2, null);
				preparedStatement.setInt(3, 1000);
				preparedStatement.setInt(4, 2);
				return preparedStatement;
			}
		},keyHolder);
		
		System.out.println("Row Inserted at : "+key);
		
		Object[] params = {key};
		
		if(key > 0){
			jdbcTemplate.update(query, params);
			System.out.println("Row Updated at : "+key);
		}
		
		return "Success";
	}
	
}
