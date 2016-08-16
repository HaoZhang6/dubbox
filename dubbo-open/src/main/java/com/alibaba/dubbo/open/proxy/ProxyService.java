package com.alibaba.dubbo.open.proxy;

import com.alibaba.dubbo.open.exception.OpenException;

public interface ProxyService {

	public abstract String invoke(String providerName, String providerMethod,
			String params)throws OpenException;

}