#### open-api 插件

##### maven依赖

```

业务体系和open-api系统必须使用最新版本的 dubbox ，如目前为 2.8.8-SNAPSHOT

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.8.8-SNAPSHOT</version>
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