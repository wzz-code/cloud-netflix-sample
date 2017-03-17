# Zuul

Zuul 是Netflix的基于JVM的路由器和服务器端负载均衡器。

##Zuul_v1.0 创建Zuul DEMO

构建一个基于Spring Boot项目，并在pom.xml中加入如下内容

```xml
 <dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zuul</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka</artifactId>
    </dependency>
</dependencies>
```

在主类中代码如下

```java
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
```

+ 通过 `@EnableZullProxy` 注解来添加反向代理能力

application.yml配置如下

```yaml
server:
  port: 7070
spring:
  application:
    name: zuul-proxy
zuul:
  routes:
    netflix-client: /*
```

如上所示，`zuul.routes.netflix-client: /*` 表示所有的请求将会转发到 "netflix-client" 这个服务中，服务是通过eureka进行发现。 


###运行示例

先在 `/etc/hosts` 或 `C:\Windows\System32\drivers\etc` 添加配置，如下

```text
127.0.0.1	eureka
127.0.0.1	zuul
```

1. 运行NetflixServerApplication，访问 [eureka](http://eureka:8761)，查看服务监控界面
1. 运行NetflixClientApplication
1. 运行ZuulApplication，访问 [zuul](http://zuul:7070/)，访问后可以看到页面显示 Hello World

操作上面的步骤后，可以正确显示结果，这说明Zuul示例已经实现了代理，将请求转到netflix-client服务。
