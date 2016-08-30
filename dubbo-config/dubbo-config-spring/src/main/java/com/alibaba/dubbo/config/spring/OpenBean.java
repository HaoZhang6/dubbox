package com.alibaba.dubbo.config.spring;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.OpenConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.open.bean.OpenSystem;
import com.alibaba.dubbo.open.provider.ProviderServiceFactory;
import com.alibaba.dubbo.open.proxy.ProxyService;
import com.alibaba.dubbo.open.proxy.ProxyServiceImpl;
import com.alibaba.dubbo.open.util.JsonUtil;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.dubbo.registry.zookeeper.ZookeeperRegistry;

public class OpenBean extends OpenConfig implements InitializingBean,ApplicationContextAware{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4114603410828257623L;
    
	private transient ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@SuppressWarnings("rawtypes")
	@Override  
	public void afterPropertiesSet() throws Exception {
		
		Map<String, ServiceConfig> serviceConfigMap = applicationContext == null ? null  : BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, ServiceConfig.class, false, false);
		
		Collection<ServiceConfig> serviceConfigs=serviceConfigMap.values();
		
		
		ProviderConfig providerConfig=applicationContext.getBean(ProviderConfig.class);
		ProtocolConfig protocolConfig=null;
		ApplicationConfig applicationConfig=null;
		RegistryConfig registryConfig=null;
		
		int exportMode=0;//0:正常 1:引用模式 2.忽略模式
		
		if(!StringUtils.isEmpty(getImportServices())){
			exportMode=1;	
			if(!StringUtils.isEmpty(getIgnoreServices())){
				setIgnoreServices("");
			}
		}
		
		if(!StringUtils.isEmpty(getIgnoreServices())){				
			 exportMode=2;	
		}
		
		 for(ServiceConfig serviceConfig:serviceConfigs){
			 String path=serviceConfig.getPath();
			 String key=path.substring(path.lastIndexOf(".")+1);
			 boolean export=true;
			 if(exportMode==1){
				 if(!getImportServices().contains(key)&&!getImportServices().contains(path)){
					 export=false;
				 }
			 }
			 if(exportMode==2){
				 if(getIgnoreServices().contains(key)&&getIgnoreServices().contains(path)){
					 export=false;
				 } 
			 }
			 if(export){
				 ProviderServiceFactory.putProviderService(key, serviceConfig.getRef());
				 protocolConfig=serviceConfig.getProtocol();
				 applicationConfig=serviceConfig.getApplication();
				 registryConfig=serviceConfig.getRegistry();
			 }
		 } 
		
		ServiceConfig<ProxyService> service=new ServiceConfig<ProxyService>();
		service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.setProtocol(protocolConfig);
        service.setInterface(ProxyService.class.getName());
        service.setRef(new ProxyServiceImpl());
        service.setGroup(providerConfig.getGroup());
        service.setVersion(providerConfig.getVersion());
        service.export();
		
        String openPath="/dubbo/open";
		String openSystemsPath=openPath+"/systems/";
		OpenSystem openSystem=new OpenSystem();
		openSystem.setApplicationName(applicationConfig.getName());
		openSystem.setProtocolName(protocolConfig.getName());
		openSystem.setGroup(providerConfig.getGroup());
		openSystem.setRegistryProtocol(registryConfig.getProtocol());
		openSystem.setRegistryAddress(registryConfig.getAddress());
		openSystem.setVersion(providerConfig.getVersion());

		String fullPath=openSystemsPath+JsonUtil.toJson(openSystem);
		
		RegistryFactory registryFactory= ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
		URL url=new URL(registryConfig.getProtocol(), registryConfig.getAddress(), 0);
		ZookeeperRegistry	 zookeeperRegistry=(ZookeeperRegistry)registryFactory.getRegistry(url);
		zookeeperRegistry.setData(fullPath);
		
	}

}
