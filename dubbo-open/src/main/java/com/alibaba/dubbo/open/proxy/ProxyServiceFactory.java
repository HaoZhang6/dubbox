/**
 * 
 */
package com.alibaba.dubbo.open.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leon
 *
 */
public class ProxyServiceFactory{
	
	private static Map<String,ProxyService> proxyServices=new HashMap<String, ProxyService>();
	
	public static ProxyService  getProxyService(String systemName){
		return proxyServices.get(systemName);
	}
	
	public static void putProviderService(String key,ProxyService object){
		proxyServices.put(key, object);
	}

}
