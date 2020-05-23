# spring-cloud-eureka-demo
Spring Cloud Eureka 的demo，主要来源于《重新定义Spring Cloud 实战》

该例子需要添加以下参数到hosts文件中
```
127.0.0.1 eureka.server.com
127.0.0.1 eureka.client.com
```

项目中存在多个profile，而且eureka-client、eureka-server、zuul的profile是相对的，运行时需要指定profile运行,如以下指定以cli运行，对应的配置文件为application-cli.properties:
```
-Dspring.profiles.active=cli
```
