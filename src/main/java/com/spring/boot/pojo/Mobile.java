package com.spring.boot.pojo;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//@Service
//@JsonIgnoreProperties(ignoreUnknown = false)
@Component
public class Mobile {
	
	@JsonProperty("_id")
	private Integer phoneid;
	
	@JsonProperty("name")
	private String phonename;
	
	private String price;
	
	private Integer warranty;
	
	public Mobile(Integer phoneid, String phonename, String price, Integer warranty) {
		this.phoneid = phoneid;
		this.phonename = phonename;
		this.price = price;
		this.warranty = warranty;
	}

	public Integer getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(Integer phoneid) {
		this.phoneid = phoneid;
	}

	public String getPhonename() {
		return phonename;
	}

	public void setPhonename(String phonename) {
		this.phonename = phonename;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}
	public Mobile(){
		
	}
	
	public Mobile(String json) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objMapper = new ObjectMapper();
		this.phonename = objMapper.readValue(json, Mobile.class).getPhonename();
		this.price = objMapper.readValue(json, Mobile.class).getPrice();
		this.warranty = objMapper.readValue(json, Mobile.class).getWarranty();
	}
}
