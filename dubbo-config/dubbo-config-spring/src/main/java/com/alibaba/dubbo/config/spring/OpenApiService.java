package com.alibaba.dubbo.config.spring;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.open.bean.OpenSystem;
import com.alibaba.dubbo.open.proxy.ProxyService;
import com.alibaba.dubbo.open.proxy.ProxyServiceFactory;
import com.alibaba.dubbo.open.util.JsonUtil;

public class OpenApiService {
	
	public void referenceProxyService(String data){
		OpenSystem openSystem=JsonUtil.toObject(data, OpenSystem.class);
		ApplicationConfig applicationConfig=new ApplicationConfig(openSystem.getApplicationName());
		ProtocolConfig protocolConfig=new ProtocolConfig(openSystem.getProtocolName());
		RegistryConfig proxyRegistryConfig=new RegistryConfig(openSystem.getRegistryAddress());
		proxyRegistryConfig.setProtocol(openSystem.getRegistryProtocol());
		
		ReferenceBean<ProxyService> reference=new ReferenceBean<ProxyService>();
		reference.setApplication(applicationConfig);
		reference.setRegistry(proxyRegistryConfig);
		reference.setProtocol(protocolConfig.getName());
		reference.setInterface(ProxyService.class.getName());
		reference.setGroup(openSystem.getGroup());
		reference.setVersion(openSystem.getVersion());
		ProxyService proxyService=reference.get();
		
		ProxyServiceFactory.putProviderService(applicationConfig.getName(),openSystem.getGroup(),openSystem.getVersion(),proxyService);
	}

}
