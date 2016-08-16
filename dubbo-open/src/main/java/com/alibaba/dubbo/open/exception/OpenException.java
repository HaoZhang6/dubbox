package com.alibaba.dubbo.open.exception;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OpenException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3952153088876094670L;

	@JsonProperty("resp_code")
	private String respCode;
	
	@JsonProperty("resp_info")
	private String respInfo;
	
	public OpenException(){}
	
	public OpenException(String message) {
	    super(message);
	}
	
	public OpenException(String respCode, String respInfo) {
		super(respCode + ": " + respInfo);
		this.respCode = respCode;
		this.respInfo = respInfo;
    }

	public String getRespCode() {
		return respCode;
	}
	
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
	public String getRespInfo() {
		return respInfo;
	}
	
	public void setRespInfo(String respInfo) {
		this.respInfo = respInfo;
	}
  
}