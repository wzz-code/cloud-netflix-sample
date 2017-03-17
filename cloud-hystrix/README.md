# Spring Cloud Hystrix

## v1.2 Circuit Breaker: Hystrix Clients

新增cloud-hystrix项目模块，并在pom.xml中添加配置
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
</dependency>
```

运行HystrixApplication，访问 http://localhost:8100/health

##v1.2.1 How to Include Hystrix And Hystrix Dashboard
在方法上添加@HystrixCommand注解

```java
@Component
public class StoreIntegration {

    @HystrixCommand(fallbackMethod = "defaultStores")
    public Object getStores(Map<String, Object> parameters) {
        System.out.println("getStores");
        return null;
    }

    public Object defaultStores(Map<String, Object> parameters) {
        System.out.println("defaultStores");
        return null; /* something useful */
    }
}
```

添加maven依赖
在pom.xml文件中加入下面的依赖

```xml
<dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
</dependency>
```

1. 运行HystrixApplication，访问 [hystrix](http://localhost:8100/hystrix)
1. 在steam文本框中输入 `http://localhost:8100/hystrix.stream` ， 并点击 `Monitor Stream` 进入 监控页面
1. 在新的浏览器中访问 [test](http://localhost:8100/test) , 并在监控页面查看效果

