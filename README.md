# MyAnimeLounge - Backend Service

> This repository houses the backend microservice of MyAnimeLounge

## Tech Stack
- OpenJDK 8
- Spring Boot 2.7.3
- PostgreSQL

## How to set up
- Clone this repo
- Create a postgres database
- Update `\src\main\resources\application.properties` with correct credentials
- Run `./mvnw spring-boot:run`
- After running the server, you need to add the roles into the database,
for that please open the database shell of the respective database of the server and run this command:

```sql
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

After this is done, the server should be ready to be used, by default it uses port 8080

> Built with ❤️ by Sayan Biswas <[me@sayanbiswas.in](mailto:me@sayanbiswas.in)>