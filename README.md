# Spring Cloud Netflix Sample

本示例是通过阅读Spring官网上的 [ Spring Cloud Netflix ](http://cloud.spring.io/spring-cloud-netflix/) 版本为 [1.3.0 M1](http://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/1.3.0.M1/) 的指南而创建的示例

将通过创建里程碑的方式记录每一个阶段的学习及应用

###运行示例

__服务端__

1. 运行cloud-netflix-server下的NetflixServerApplication
1. 打开Eureka服务管理界面 http://localhost:8761/

__客户端__

1. 运行cloud-netflix-client下的NetflixClientApplication
1. 运行后将注册到Eureka服务，可通过 http://localhost:8761/ 查看是否已注册成功

#版本

##v1.0 创建示例项目

创建一个基于Eureka的服务端以及客户端，并运行。

##v1.1 健康监控

如果需要查看程序的健康状态，则需要在 `pom.xml` 添加配置

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
并在application.yml中添加配置

```yaml
info:
  team:
    developer: Wang Zhizhao
    tester: Wang Zhizhao
  docs:
    url: https://github.com/wzz-code/cloud-netflix-sample

endpoints:
  sensitive: false
  beans:
    sensitive: true
```
如上所示，`info` 则定义此应用程序的一些相关信息，如开发人员、测试人员、文档地址等。
当需要让所有的健康监控接口都有关闭验证，则可以设置 `endpoints.sensitive = false`，
如果需要再指定特定的接口，如指定beans需要验证后才可访问，则可设置 `endpoints.beans.sensitive = true`

##v1.1.1 修改默认监控访问地址

我们也可以修改监控的默认访问地址，如下示例

```yaml
management:
  port: 8081
  context-path: /admin

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    status-page-url-path: ${management.context-path}/info
    health-check-url-path: ${management.context-path}/health
```
如上所示，将管理端口修改为`8081`，访问地址为 http://localhost:8081/admin ，并且通过 `eureka.instance.xxxx-url-path` 告诉Eureka Server此应用程序修改后的监控接口地址