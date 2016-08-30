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
	
	public static ProxyService  getProxyService(String systemName,String group,String version){
		String key=systemName+group+version;
		return proxyServices.get(key);
	}
	
	public static void putProviderService(String systemName,String group,String version,ProxyService object){
		String key=systemName+group+version;
		proxyServices.put(key, object);
	}

}
