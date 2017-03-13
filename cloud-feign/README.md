# Feign

Ribbon是一个基于HTTP和TCP客户端的负载均衡器

##Feign_v1.0 创建Feign DEMO

构建一个基于Spring Boot项目，并在pom.xml中加入如下内容

```xml
 <dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-feign</artifactId>
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
@EnableDiscoveryClient
@EnableFeignClients
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}
```

+ 通过 `@EnableDiscoveryClient` 注解来添加发现服务能力
+ 通过 `@EnableFeignClients` 注解开启均衡负载能力

定义 `netflix-client` 服务接口，代码如下

```java
@FeignClient("netflix-client")
public interface ComputeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hystrix-hello")
    String hello();
}
```

+ 使用 `@FeignClient("netflix-client")` 注释来绑定netflix-client服务
+ `@RequestMapping(method = RequestMethod.GET, value = "/hystrix-hello")` 将调用netflix-client服务的/hystrix-hello接口
+ `String hello()` 为无参数时的接口定义，当有参数时，可定义为 `String hello(@RequestParam(value = "param") String param);

在web层中调用上面定义的 `ComputeClient`，代码如下 

```java
@RestController
public class ConsumerController {

    @Autowired
    ComputeClient computeClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return computeClient.hello();
    }

}
```

###运行示例

先在 `/etc/hosts` 或 `C:\Windows\System32\drivers\etc` 添加配置，如下

```text
127.0.0.1	eureka
127.0.0.1	feign
```

1. 运行NetflixServerApplication，访问 [eureka](http://eureka:8761)，查看服务监控界面
1. `cd cloud-netflix-client/`，执行 `mvn clean package`
1. 执行 `java -jar target/cloud-netflix-client-0.0.1-SNAPSHOT.jar --server.port="8180" --management.port="8181"`
1. 执行 `java -jar target/cloud-netflix-client-0.0.1-SNAPSHOT.jar --server.port="8280" --management.port="8281"`
1. 运行FeignApplication，在新的浏览器中访问2次 [feign-hello](http://feign:3355/hello) 
1. 查看两个netflix-client控制台

可以看到，之前启动的两个netflix-client服务端分别被调用一次，与使用Ribbon时一样，说明这个Feign示例已经实现了对服务调用的均衡负载
