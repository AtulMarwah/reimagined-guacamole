package com.spring.boot.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
	
	@JsonProperty("amount")
	private Double tranction_amount;
	
	private String remark;
	
	@JsonProperty("promoCode")
	private boolean promoCodeApplicable;
	
	private Double promoCodeAmount;
	
	private String updateAfter;

	public Double getTranction_amount() {
		return tranction_amount;
	}

	public void setTranction_amount(Double tranction_amount) {
		this.tranction_amount = tranction_amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public boolean isPromoCodeApplicable() {
		return promoCodeApplicable;
	}

	public void setPromoCodeApplicable(boolean promoCodeApplicable) {
		this.promoCodeApplicable = promoCodeApplicable;
	}

	public Double getPromoCodeAmount() {
		return promoCodeAmount;
	}

	public void setPromoCodeAmount(Double promoCodeAmount) {
		this.promoCodeAmount = promoCodeAmount;
	}
	
	public String getUpdateAfter() {
		return updateAfter;
	}

	public void setUpdateAfter(String updateAfter) {
		this.updateAfter = updateAfter;
	}


}
