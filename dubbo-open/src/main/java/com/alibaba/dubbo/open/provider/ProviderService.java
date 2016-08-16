package com.alibaba.dubbo.open.provider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ProviderService {
	
	private Object service;
	
	public Object getService() {
		return service;
	}

	public void setService(Object service) {
		this.service = service;
	}

	public ProviderService(String name,Object object){
	    service=object;
		Method[] methods=object.getClass().getDeclaredMethods();
		serviceMethods=new HashMap<String, Method>();
	    for(Method method:methods){
	    	serviceMethods.put(method.getName(),method);
	    }
	}
	
	private Map<String,Method> serviceMethods;
	
	public Method getMethod(String methodName){
		return serviceMethods.get(methodName);
	}

}
