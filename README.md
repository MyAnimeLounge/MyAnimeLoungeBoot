# MyAnimeLounge - Backend Service

> This repository houses the backend microservice of MyAnimeLounge

## Tech Stack
- OpenJDK 8
- Spring Boot 2.7.3
- PostgreSQL

## How to set up
- Clone this repo
- Create a postgres database
- Copy `application.properties.sample` contents and create a file named `application.properties` in **resources** directory, after that fill the file with correct credentials.
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

# Planned Features
- Anime tracking: Allow users to keep track of what anime they have watched and their progress. ✅
- Watchlist: Provide users with the ability to create a watchlist of anime they want to watch in the future.
- Recommendations: Offer personalized anime recommendations based on the user's watch history, ratings, and other factors.
- Social features: Allow users to share their anime lists, ratings, and reviews with friends and connect with other anime fans.
- Ratings and reviews: Enable users to rate and review anime they have watched, which can help other users discover new anime.
- Watch history: Allow users to view their watch history, including the date they watched an anime and how many times they have re-watched it.
- Custom lists: Allow users to create custom lists based on their preferences, such as favorite anime, currently watching, completed, and dropped anime.
- Schedule planner: Provide users with a schedule planner to help them plan their anime-watching schedule and avoid conflicts.
- Genre and category filters: Allow users to filter anime by genre, category, release year, and other criteria.
- Notification and reminder system: Send users notifications and reminders when new episodes of their favorite anime are released, or when an anime on their watchlist becomes available.

> Built with ❤️ by Sayan Biswas <[me@sayanbiswas.in](mailto:me@sayanbiswas.in)>
