package com.alibaba.dubbo.config.spring;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.OpenApiConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;

public class OpenApiBean extends OpenApiConfig implements InitializingBean, ApplicationContextAware {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -6026182502977779357L;
	private transient ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override  
	public void afterPropertiesSet() throws Exception {				
		
		RegistryConfig registryConfig=null;
		
		Map<String, RegistryConfig> registryConfigMap = applicationContext == null ? null  : BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, RegistryConfig.class, false, false);
		
		 for(RegistryConfig registryConfigValue:registryConfigMap.values()){
			 if(registryConfigValue.getId().startsWith("com.alibaba.dubbo.config.RegistryConfig")){
				 registryConfig=registryConfigValue;
			 }
		 } 
		
		RegistryFactory registryFactory= ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();		 

		URL url=new URL(registryConfig.getProtocol(), registryConfig.getAddress(), 0);
		ZookeeperRegistry	 zookeeperRegistry=(ZookeeperRegistry)registryFactory.getRegistry(url);
		
		String openPath="/dubbo/open/";
		
		String openSystemsPath=openPath+"systems";
		
		java.util.List<String> urls=zookeeperRegistry.getData(openSystemsPath);
		
		OpenApiService openApiService=new OpenApiService();
		
		for(String path:urls){
			openApiService.referenceProxyService(path);
		}
		
		zookeeperRegistry.addPathChildlistener(openSystemsPath, new OpenChildListener());
		
	}

}
