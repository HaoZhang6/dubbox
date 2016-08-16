/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.config;


/**
 * OpenConfig
 * 
 * @author leon.zhang
 * @export
 */
public class OpenConfig extends AbstractConfig{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -284769756724217123L;

	/**
     *  对外发布的服务，不能与 ignoreServices 同时使用
     */
    private String            importServices;
    
    /**
     * 不发布的服务，不能与 importServices 同时使用
     */
    private String            ignoreServices;

	public String getImportServices() {
		return importServices;
	}

	public String getIgnoreServices() {
		return ignoreServices;
	}

	public void setImportServices(String importServices) {
		this.importServices = importServices;
	}

	public void setIgnoreServices(String ignoreServices) {
		this.ignoreServices = ignoreServices;
	}

}