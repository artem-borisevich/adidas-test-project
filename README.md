# adidas-test-project


# adidas-api-project
Public service opened for end user.

Technology stack:
 
 - spring-cloud-starter-gateway - Spring Cloud Gateway, this project provides a library for building an API Gateway. Will be used for routing to private Subscription service.
 - spring-boot-starter-webflux - required dependency for spring-cloud-starter-gateway.
 - spring-cloud-starter-netflix-hystrix - implementation of Circuit breaker pattern, provide way to reroute request to fallback controller in case of any service is down.  
 
_Docker commands:_
- docker build -t api . - _build docker image_
- docker run -d -p 8080:8080 api - _run docker image_

# subscription-service-project
Private subscription service.

Technology stack:
 
 - spring-boot-starter-data-jpa - Spring module that simplify communication with DB.
 - spring-boot-starter-validation - Used for Model validation.
 - spring-kafka - Spring lib for integration with Apache Kafka
 - mysql:mysql-connector-java - MySql driver
 - org.liquibase:liquibase-core - tool for managing db scripts
 
_Docker commands:_
- docker-compose up

# email-service-project
Public service opened for end user.

Technology stack:
 
 - spring-kafka - Spring lib for integration with Apache Kafka
 - com.sun.mail:javax.mail - provide classes for working with emails.
 
_Docker commands:_
- docker-compose up