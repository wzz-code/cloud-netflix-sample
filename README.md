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