package com.spring.boot.pojo;

import org.springframework.stereotype.Component;

@Component
public class ResponseProvider {
	
	private Integer status;
	
	private String entity;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	

}
