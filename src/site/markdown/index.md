# Spring WS LDAP Security Example

Example for setting up LDAP Security on a web service with Spring Boot.

## Usage

The project requires an authorization server. The Docker compose file will take care of this, while running the project:

```
docker-compose -f docker/docker-compose.yml --project-name spring-ws-ldap-security-example up
```

And the web service be available at [http://localhost:8080/](http://localhost:8080/).

## Requests with Postman

To make things easier import `src/test/resources/LDAP.postman_collection.json` into Postman. It includes all the queries needed to test the project.

## Users

| User    | Password | Permissions |
|---------|----------|-------------|
| admin   | 1234     | all         |
