package com.alibaba.dubbo.config.spring;

import java.util.List;

import com.alibaba.dubbo.remoting.zookeeper.ChildListener;

public class OpenChildListener implements ChildListener {
	
	private OpenApiService openApiService=new OpenApiService();

	@Override
	public void childChanged(String path, List<String> children) {
		
		for(String child:children){
			openApiService.referenceProxyService(child);
	}

	}

}
