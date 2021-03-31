/*package com.spring.boot.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.omg.CORBA.TRANSACTION_MODE;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dao.AdhayanDAO;
import com.spring.boot.model.Transaction;
import com.spring.boot.pojo.Mobile;
//import com.spring.boot.utils.Utilities;

@Component
public class AdhayanDAOImpl implements AdhayanDAO {
	
	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AdhayanDAOImpl.class);
	
	@Autowired
	Utilities jdbctemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	@Override
	public String persistIncomingData(Mobile incomingData) {
		// TODO Auto-generated method stub
		//String query = "SELECT * FROM NOKIA WHERE";
		String inserQuery = "INSERT INTO NOKIA VALUES(phoneid_seq.nextval,NVL(?"
				+ ",'NOKIA'),NVL(?,10000),NVL(?,2),systimestamp at time zone 'utc')";
		
		
		//Merge Query, and various sql functions like xmlType to be included
		//namedParameterJdbcTemplate.update(inserQuery, paramMap)
		
		int val = 0;
		val = jdbcTemplate.update(inserQuery,incomingData.getPhoneid()
				,incomingData.getPhonename(),incomingData.getPrice(),
				incomingData.getWarranty());
		if(val!=0){
			return "Successfully inserted incomingData";
		}
		else
			return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public String checkAnswers(String optionSelected) {
		// TODO Auto-generated method stub
		
		String query = "Select solution from quiz where solution='"+optionSelected+"'";
		
		try{
		String value = jdbcTemplate.queryForObject(query, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				return rs.getString(1);
			}
			
		});
		
		//if(value != null && !value.isEmpty())
			return value;
		}
		
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}


	@Override
	@Transactional
	public String performTransactions(Transaction transaction) throws SQLException{
		// TODO Auto-generated method stub
		final Transaction temp = transaction;
		//Long transId = (long) 0;
		Long transId = null;
		int rowsInstered = 0;
		try{
		final Character chr = 'Y';
		String is_pc = null;
		//Connection connection = dataSource.getConnection();
		if(transaction.isPromoCodeApplicable()){
			//chr = 'Y';
			is_pc = "Y";
		}
		else{
			//chr = 'N';
			is_pc = "N";
		}
		String performConcession = null;
		String updateQuery = null;
		final String performTransaction = "Insert Into transaction_info VALUES(transid_seq.nextval,?,?,?,?,?,SYSTIMESTAMP AT TIME ZONE 'UTC',SYSTIMESTAMP  AT TIME ZONE 'UTC')";
		
		//connection.setAutoCommit(true);
		rowsInstered = jdbcTemplate.update(performTransaction,
				transaction.getTranction_amount(),transaction.getRemark(),
				is_pc,transaction.getPromoCodeAmount(),"USER");
		KeyHolder holder = new GeneratedKeyHolder();
		//final String performTransaction1 = "Insert Into transaction_info VALUES(transid_seq.currval,?,?,?,?,?,SYSTIMESTAMP AT TIME ZONE 'UTC',SYSTIMESTAMP  AT TIME ZONE 'UTC')";
		
		rowsInstered = jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement preparedStatement = arg0.prepareStatement(performTransaction.toString(),
                        new String[]{"transactionid"}Statement.RETURN_GENERATED_KEYS); 
				preparedStatement.setDouble(1, temp.getTranction_amount());
				preparedStatement.setString(2, temp.getRemark());
				preparedStatement.setString(3, (temp.getPromoCodeAmount() != null ? "Y" : "N"));
				preparedStatement.setDouble(4, temp.getPromoCodeAmount());
				preparedStatement.setString(5, "USER");
				return preparedStatement;
			}
		},holder);
		
		//transId = holder.getKey().longValue();
		//transId =(Integer) holder.getKeys().get("ROWID");
		//Integer id = transId.intValue();
		
		//connection.commit();
		if(is_pc.equalsIgnoreCase("Y") && rowsInstered > 0){
			 performConcession = "Insert Into discounts_and_cashback VALUES(conscess_seq.nextval,(transid_seq.nextval - 1),NVL(?,0),NVL(?,0),?)";
			 //connection.setAutoCommit(false);
			int rowsInstered2 = jdbcTemplate.update(performConcession,
					transaction.getPromoCodeAmount(),(transaction.getPromoCodeAmount() != null ? 0 : 0), 
					"PromoCode Applied."+transaction.getPromoCodeAmount()+ " and Cashback worth 0");
			//connection.commit();
			if(rowsInstered2 > 0){
				KeyHolder holder = new GeneratedKeyHolder();
				final String performTransaction1 = "Insert Into transaction_info VALUES(transid_seq.currval,?,?,?,?,?,SYSTIMESTAMP AT TIME ZONE 'UTC',SYSTIMESTAMP  AT TIME ZONE 'UTC')";
				
				jdbcTemplate.update(new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
						// TODO Auto-generated method stub
						PreparedStatement preparedStatement = arg0.prepareStatement(performTransaction1.toString(),
		                        Statement.RETURN_GENERATED_KEYS); 
						preparedStatement.setDouble(1, temp.getTranction_amount());
						preparedStatement.setString(2, temp.getRemark());
						preparedStatement.setString(3, (temp.getPromoCodeAmount() != null ? "Y" : "N"));
						preparedStatement.setDouble(4, temp.getPromoCodeAmount());
						preparedStatement.setString(5, "USER");
						return preparedStatement;
					}
				},holder);
				
				transId = holder.getKey().longValue();
				//transId =(Integer) holder.getKeys().get("ROWID");
				//Integer id = transId.intValue();
				Iterator iterator = holder.getKeys().keySet().iterator();
				
				while(iterator.hasNext()){
					String key = (String) iterator.next();
					System.out.println("iterator.next() -----"+holder.getKeys().get(key));
				}
				
				System.out.println("transId is -----"+transId);
				
				updateQuery = "UPDATE transaction_info SET transaction_amount = ?, udatedAfter = ? , lastUpdatedTS = SYSTIMESTAMP AT TIME ZONE 'UTC' WHERE transactionid = ?";
				int updateRows = jdbcTemplate.update(updateQuery,new Object[]{(transaction.getTranction_amount() - transaction.getPromoCodeAmount()),"CASHBACK/PROMOCODE", transId});
				//connection.commit();
				System.out.println("jdbcTemplate.getQueryTimeout() info ------"+jdbcTemplate.getQueryTimeout());
				rowsInstered = updateRows;
			}
			//Integer id = transId.intValue();
			
		}
		}
		catch(SQLException e){//DataRetrievalFailureException -- keyholfer
			e.printStackTrace();
			LOGGER.info("Caught Exception "+e);
		}
		catch(DataRetrievalFailureException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		if(rowsInstered > 0 )
			return "Transaction Completed Successfully";
		else
			return "Oops! Please Try Again \n Tranaction Failed";
	}

}
*/