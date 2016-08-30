package com.alibaba.dubbo.open.provider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ProviderService {
	
	private Object service;
	
	private Map<String,Method> serviceMethods;
	
	private Class<?> fullType;
	
	public Object getService() {
		return service;
	}

	public void setService(Object service) {
		this.service = service;
	}

	public ProviderService(String name,Object object){
	    service=object;
	    fullType=object.getClass();
		Method[] methods=object.getClass().getDeclaredMethods();
		serviceMethods=new HashMap<String, Method>();
	    for(Method method:methods){
	    	serviceMethods.put(method.getName(),method);
	    }
	}
	
	public Method getMethod(String methodName){
		return serviceMethods.get(methodName);
	}

	public Map<String, Method> getServiceMethods() {
		return serviceMethods;
	}

	public Class<?> getFullType() {
		return fullType;
	}

	public void setServiceMethods(Map<String, Method> serviceMethods) {
		this.serviceMethods = serviceMethods;
	}

	public void setFullType(Class<?> fullType) {
		this.fullType = fullType;
	}

}
