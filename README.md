# Car Dealership Demo
## Description
Demo web application for a car dealership to handle internal processing of vehicle catalog, purchases, statistics, users, etc...
It provides a content management interface for manipulation of the data as well as a REST API with read-only access.
## Implementation
* Spring Boot 1.5
* Vaadin 8
* Spring Data Rest
* Maven
* Swagger
* Spring Security
## Build
* mvn clean package spring-boot:repackage
## Run
* create application.properties file with content like -> src/main/resources/application-local.properties
* java -jar testproject-0.0.1-SNAPSHOT.war --spring.config.location=PATH-TO/application.properties
* initial temporary user with all roles: tempuser / temppass
* CMS: http://localhost:8080/cms
* API: http://localhost:8080/api
* Swagger: http://localhost:8080/api/swagger-ui.html

