package com.alibaba.dubbo.open.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8648139904908188729L;

	@JsonProperty("resp_code")
	private String respCode;
	
	@JsonProperty("resp_info")
	private String respInfo;

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
	
	public void success(){
		this.respCode="200";
		this.respInfo="success";
	}
	
	public void error(){
		this.respCode="500";
		this.respInfo="error";
	}
	
	public void build(String code,String info){
		this.respCode=code;
		this.respInfo=info;
	}

}
