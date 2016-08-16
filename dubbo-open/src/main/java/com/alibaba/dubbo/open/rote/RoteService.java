package com.alibaba.dubbo.open.rote;

import com.alibaba.dubbo.open.constant.Constant;
import com.alibaba.dubbo.open.exception.OpenException;
import com.alibaba.dubbo.open.proxy.ProxyService;
import com.alibaba.dubbo.open.proxy.ProxyServiceFactory;

public class RoteService {
	
	public String invoke(String systemName,String providerName, String providerMethod,
			String params)throws OpenException{
		ProxyService proxyService=ProxyServiceFactory.getProxyService(systemName);
		if(proxyService==null){
			throw new OpenException(Constant.systemNotFondCode, Constant.systemNotFondInfo);
		}
		return proxyService.invoke(providerName, providerMethod, params);
	}

}
