package com.spring.boot.controllers;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.spring.boot.dao.AdhayanDAO;
import com.spring.boot.daoTwo.AdhayanDAOTwo;
import com.spring.boot.exception.CustomizedException;
import com.spring.boot.model.Transaction;
import com.spring.boot.pojo.Mobile;
import com.spring.boot.pojo.ResponseProvider;
import com.spring.boot.pojo.Trial2;

@RestController
public class Controller {
	
	private final Logger logger = LoggerFactory.getLogger(Controller.class);
	/*
	@Autowired
	AdhayanDAO adhayanDAO;*/
	
	@Autowired
	AdhayanDAOTwo adhayanDAOTwo; 
	
	@GET
	@RequestMapping("/sound/{id}")
	public Integer getId(@PathVariable("id") Integer id){
		
		return id;
	}
	
	/*@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/post/")
	public Integer getTrial(@RequestBody Trial2 trail){
		
		return Integer.parseInt(trail.getDescription());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/")
	public Response persistIncomingData(@RequestBody Mobile incomingData){
		
		ResponseProvider response = new ResponseProvider();
		
		String output = adhayanDAO.persistIncomingData(incomingData);
		
		Response response1 = null;
		
		try{
			response1 = Response.ok(output).build();
		if(!output.isEmpty() && output != null){
			response.setEntity(output);
			response.setStatus(200);
		}
		else{
			response.setStatus(500);
			response.setEntity("Sorry! data not inserted :o");
		}
		
		logger.info("Entity recieved conveys {"+response1.getEntity()+"}");
		
		//logger.
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("Exception is : ",e);
			//throw new CustomizedException("Oops! Something went wrong..");
		}
		
		return response1;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/check")
	public Response checkAnswers(@RequestBody String optionSelected){
		Response response = null;
		String serviceResponse = adhayanDAO.checkAnswers(optionSelected);
		if(serviceResponse != null)
			//response = Response.ok(serviceResponse).build();
			response = Response.ok("Yay, Correct Selection!").build();
		else
			response = Response.ok("Oops, Wrong Option Selected!").build();
		
		return response;
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping("/transactions")
	public Response doTransaction(@RequestBody Transaction transaction) throws SQLException{
		Response response = null;
		
		String transactionResponse = adhayanDAO.performTransactions(transaction);
		
		if(!transactionResponse.isEmpty() && transactionResponse != null)
			response = Response.ok(transactionResponse).build();
		else
			response = Response.ok(transactionResponse).build();
		
		return response;
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping("/mergeQuery/{val}")
	public Response tryMergeQuery(@PathVariable int val) throws SQLException{
		Response response = null;
		
		String transactionResponse = adhayanDAOTwo.insertOrUpdateDate(val);
		
		if(!transactionResponse.isEmpty() && transactionResponse != null)
			response = Response.ok(transactionResponse).build();
		else
			response = Response.ok(transactionResponse).build();
		
		return response;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping("dummyCheck")
	public ResponseEntity dummyCheck(/*@PathVariable int val*/) throws SQLException{
		
		Mobile mobile = new Mobile(10,"ten","Ten",1);
		
		ResponseEntity responseEntity = new ResponseEntity(mobile,HttpStatus.OK);
		
		if(responseEntity.getBody() instanceof Mobile){
			System.out.println("True is responseEntity.getBody() instanceof Mobile");
		}
		
		return responseEntity;
	}
	
}
