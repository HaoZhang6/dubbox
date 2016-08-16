package com.alibaba.dubbo.open.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.alibaba.dubbo.open.constant.Constant;
import com.alibaba.dubbo.open.exception.OpenException;
import com.alibaba.dubbo.open.provider.ProviderService;
import com.alibaba.dubbo.open.provider.ProviderServiceFactory;
import com.alibaba.dubbo.open.util.JsonUtil;
  
public class ProxyServiceImpl implements ProxyService {
	
	/* (non-Javadoc)
	 * @see com.alibaba.dubbo.open.ProxyService#invoke(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public String  invoke(String providerName,String providerMethod,String params)throws OpenException{
		ProviderService provider= ProviderServiceFactory.getProviderService(providerName);
		if(provider==null){
			throw new OpenException(Constant.serviceNotFondCode,Constant.serviceNotFondInfo);
		}
		try {
			Method method=provider.getMethod(providerMethod);
			HashMap<String,Object> paramsMap=new HashMap<String, Object>();
			paramsMap=JsonUtil.toObject(params, paramsMap.getClass());
			Object  result=method.invoke(provider.getService(),paramsMap.values().toArray());
			return JsonUtil.toJson(result);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
  