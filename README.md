### Introduction

`Pet` microservices project.

* Docker
* Spring Boot
* Cassandra
* ZooKeeper
* Kafka
* REST API

### Developer Environment

Build docker images
```
chmod +x ./docker-build.sh && ./docker-build.sh
```
Run
```
docker-compose up
```

[User API](https://github.com/tsarenkotxt/microservices/blob/master/api_service/src/main/java/com/griddynamics/api/controller/UserController.java#L12)  
[Order API](https://github.com/tsarenkotxt/microservices/blob/master/api_service/src/main/java/com/griddynamics/api/controller/OrderController.java#L12)    
[Product API](https://github.com/tsarenkotxt/microservices/blob/master/api_service/src/main/java/com/griddynamics/api/controller/ProductController.java#L12)

### Architecture

![](readme/diagram.png)


