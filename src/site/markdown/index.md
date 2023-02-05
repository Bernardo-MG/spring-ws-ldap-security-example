# Spring WS LDAP Security Example

Example for LDAP security with Spring.

The project requires an authorization server. The Docker compose file will take care of this, while running the project:

```
docker-compose -f docker/docker-compose.yml up
```

## Requests with Postman

Import `src/test/resources/LDAP.postman_collection.json` to get queries for all the operations including authentication.

## Users

| User    | Password | Permissions |
|---------|----------|-------------|
| admin   | 1234     | all         |
