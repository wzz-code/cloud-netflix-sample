# Turbine

Turbine，监控聚合，使用Hystrix监控，我们需要打开每一个服务实例的监控信息来查看。而Turbine可以帮助我们把所有的服务实例的监控信息聚合到一个地方统一查看。这样就不需要挨个打开一个个的页面一个个查看。

## Turbine_v1.0

### 运行示例

先在 `/etc/hosts` 或 `C:\Windows\System32\drivers\etc` 添加配置，如下

```text
127.0.0.1	eureka
127.0.0.1	netflix-client
127.0.0.1	turbine
127.0.0.1	hystrix
```

1. 运行NetflixServerApplication，访问 [eureka](http://eureka:8761)，查看服务监控界面
1. 运行HystrixApplication，访问 [hystrix](http://hystrix:8100/hystrix)，进入到配置页面
1. 运行TurbineApplication，在steam文本框中输入 `http://turbine:8989/turbine.stream?cluster=MAIN` ， 并点击 `Monitor Stream` 进入 监控页面
1. 运行NetflixClientApplication，在新的浏览器中访问 [hystrix-hello](http://netflix-client:8080/hystrix-hello) , 并在监控页面查看效果
1. 在新的浏览器中访问 [test](http://hystrix:8100/test)  , 并在监控页面查看效果

### 配置说明

#### cloud-trubine
在`pom.xml` 添加配置，并配置eureka

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-netflix-turbine</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```
在 `application.xml` 添加配置

```yaml
turbine:
  aggregator:
    clusterConfig: MAIN
  app-config: hystrix, netflix-client
  cluster-name-expression: metadata['cluster']
  instanceUrlSuffix:
    MAIN: admin/hystrix.stream
```
+ `clusterConfig` 配置集群的名称，是一个列表集合，clusterConfig: [clusterName1],[clusterName2],...， http://turbine-hostname:port/turbine.stream?cluster=[clusterName] 中的clusterName
+ `app-config` 每个服务对应的 `spring.application.name`
+ `cluster-name-expression` 当值为metadata['cluster']时，表示从其它服务的metadata.cluster取值，应该与clusterConfig中的值匹配
+ `instanceUrlSuffix.MAIN` 表示集群MAIN都使用此配置，以访问hystrix.stream。一般用于当其它服务修改 `management.context-path` 时，turbine无法自动发现hystrix.stream，所以修改此配置替换默认值

在StringBootApplication的配置

```java
@SpringBootApplication
@EnableTurbine
@EnableEurekaClient
public class TurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
```

添加 `@EnableTurbine` 注解

#### 使用turbine的客户端配置

如下配置

```yaml
spring:
  application:
    name: netflix-client
    
management:
  port: 8081
  context-path: /admin

eureka:
  instance:
    metadata-map:
      cluster: MAIN
      management.port: ${management.port:8081}
```

+ `spring.application.name` 与 `turbineapp-config` 集合中的其中一个值一致
+ `metadata-map: cluster` 描述集群名称为 MAIN
+ `metadata-map: management.prot` 当 `management.port` 有修改时，则需要在metadata-map中进行描述，turbine才可以找到 hystrix.stream



