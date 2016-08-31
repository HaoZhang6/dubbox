#### open-api 插件

##### maven依赖

```

业务体系和open-api系统必须使用最新版本的 dubbox ，如目前为 2.8.8-SNAPSHOT

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.8.8-SNAPSHOT</version>
        </dependency>

同时，加入以下依赖

        <dependency>
            <groupId>com.101tec</groupId>
            <artifactId>zkclient</artifactId>
            <version>0.7</version>
        </dependency>
        <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-client</artifactId>
            <version>3.0.16.Final</version>
        </dependency>
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>1.0.0.GA</version>
        </dependency>
        <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-jackson2-provider</artifactId>
            <version>3.0.16.Final</version>
        </dependency>
        <dependency>
			<groupId>javax.ws.rs</groupId>
			<artifactId>javax.ws.rs-api</artifactId>
            <version>2.0.1</version>
		</dependency> 

        <dependency>
	      <groupId>com.fasterxml.jackson.core</groupId>
	      <artifactId>jackson-core</artifactId>
          <version>2.7.2</version>
        </dependency>
        <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-databind</artifactId>
          <version>2.7.2</version>
        </dependency>
        <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-annotations</artifactId>
          <version>2.7.2</version>
        </dependency>

```

##### 配置

- 业务系统使用 \<dubbo:open/\> ,需要配置group和version时，必须在 dubbo:provider 中配置 group 和 version
- open-api系统使用 \<dubbo:open-api exportServices="" ignoreServices=""/\>

```

exportServices 和 ignoreServices 均为可选配置

exportServices 发布到open-api的服务,多个服务以 , 分割
ignoreServices 不发布到open-api的服务,多个服务以 , 分割

同时配置 exportServices 和 ignoreServices，只有exportServices生效

```

##### openException

```

com.alibaba.dubbo.open.exception.OpenException

业务系统直接抛出 OpenException，open-api就可以返回相应的异常信息

```

##### BaseResponse

```

建议所有业务系统使用 com.alibaba.dubbo.open.response.BaseResponse

使用统一的返回格式

{"resp_code":x,"resp_info":"x",...}

```