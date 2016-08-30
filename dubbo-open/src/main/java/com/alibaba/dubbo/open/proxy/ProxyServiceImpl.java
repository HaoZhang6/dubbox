package com.alibaba.dubbo.open.proxy;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import com.alibaba.dubbo.open.constant.Constant;
import com.alibaba.dubbo.open.exception.OpenException;
import com.alibaba.dubbo.open.provider.ProviderService;
import com.alibaba.dubbo.open.provider.ProviderServiceFactory;
import com.alibaba.dubbo.open.util.JsonUtil;
  
public class ProxyServiceImpl implements ProxyService {
	
	@Override
	@SuppressWarnings("unchecked")
	public String  invoke(String providerName,String providerMethod,String params)throws OpenException{
		ProviderService provider= ProviderServiceFactory.getProviderService(providerName);
		if(provider==null){
			throw new OpenException(Constant.serviceNotFondCode,Constant.serviceNotFondInfo);
		}
		try {
			Method method=provider.getMethod(providerMethod);
			LinkedHashMap<String,Object> paramsMap=new LinkedHashMap<String, Object>();
			paramsMap=JsonUtil.toObject(params, paramsMap.getClass());
			Object[] invokeParams=paramsMap.values().toArray();
			Object service=provider.getService();
			Object  result=method.invoke(service,invokeParams);
			return JsonUtil.toJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenException(Constant.serverErrorCode,Constant.serverErrorInfo);
		}
	}

}
  