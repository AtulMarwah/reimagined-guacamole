package com.spring.boot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomizedException extends Exception{
	
	private final Logger logger = LoggerFactory.getLogger(CustomizedException.class);
	
	/*public CustomizedException(){
		
	}*/
	
	public CustomizedException(String string) {
		// TODO Auto-generated constructor stub
		logger.error(string ,getMessage());
		
	}

	/*public String CustomizedException(String exception){
		
		return exception;
	}*/

}
