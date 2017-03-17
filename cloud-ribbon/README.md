# Ribbon

Ribbon是一个基于HTTP和TCP客户端的负载均衡器

## Ribbon_v1.0 创建Ribbon DEMO

构建一个基于Spring Boot项目，并在pom.xml中加入如下内容

```xml
 <dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-ribbon</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka</artifactId>
    </dependency>
</dependencies>
```

在主类中代码如下

```java
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonApplication {

    @Bean
    @LoadBalanced
    RestTemplate resetTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }
}
```

+ 通过 `@EnableDiscoveryClient` 注解来添加发现服务能力
+ 通过 `@loadBalanced` 注解开启均衡负载能力

在控制类代码如下

```java
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return restTemplate.getForEntity("http://NETFLIX-CLIENT/hystrix-hello", String.class).getBody();
    }

}
```
如上所示创建 `ConsumerController` 来消费 `NETFLIX-CLIENT` 的hystrix-hello服务。通过RestTemplate来调用服务。

### 运行示例

先在 `/etc/hosts` 或 `C:\Windows\System32\drivers\etc` 添加配置，如下

```text
127.0.0.1	eureka
127.0.0.1	ribbon
```

1. 运行NetflixServerApplication，访问 [eureka](http://eureka:8761)，查看服务监控界面
1. `cd cloud-netflix-client/`，执行 `mvn clean package`
1. 执行 `java -jar target/cloud-netflix-client-0.0.1-SNAPSHOT.jar --server.port="8180" --management.port="8181"`
1. 执行 `java -jar target/cloud-netflix-client-0.0.1-SNAPSHOT.jar --server.port="8280" --management.port="8281"`
1. 运行RibbonApplication，在新的浏览器中访问2次 [ribbon-hello](http://ribbon:3333/hello) 
1. 查看两个netflix-client控制台

可以看到，之前启动的两个netflix-client服务端分别被调用一次，说明这个Ribbon示例已经实现了对服务调用的均衡负载

