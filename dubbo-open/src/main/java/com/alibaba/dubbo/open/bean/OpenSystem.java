package com.alibaba.dubbo.open.bean;

import java.io.Serializable;

public class OpenSystem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3130248224788042713L;

	private String applicationName;
	
	private String protocolName;
	
	private String group;
	
	private String registryAddress;
	
	private String registryProtocol;

	public String getApplicationName() {
		return applicationName;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public String getGroup() {
		return group;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public String getRegistryProtocol() {
		return registryProtocol;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public void setRegistryProtocol(String registryProtocol) {
		this.registryProtocol = registryProtocol;
	}

}
