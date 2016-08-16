/**
 * 
 */
package com.alibaba.dubbo.open.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leon
 *
 */
public class ProviderServiceFactory{
	
	private static Map<String,ProviderService> providerServices=new HashMap<String, ProviderService>();
	
	public static ProviderService  getProviderService(String providerName){
		return providerServices.get(providerName);
	}
	
	public static void putProviderService(String key,Object object){
		ProviderService providerService=new ProviderService( key, object);
		providerServices.put(key, providerService);
	}

}
