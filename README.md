# Trivia Quiz

This is a website where you can test your knowledge in several topics by answering quiz questions.

## Description

To use the application first you have to register a user name. After that you can log in to start playing.
You can choose between casual and ranked play. If you choose to play ranked your performance will be scored based on your answers. By accumulating enough points, you can appear on the leaderboard!

## Getting Started

### Built With

- [React.js](https://react.dev/)
- [Java](https://www.java.com/en/)
- [SpringBoot](https://spring.io/projects/spring-boot)
- [PostreSQL](https://www.postgresql.org/)

### Prerequisits

- JDK 17 or higher
- Maven 3.9.6 or higher
- Node.js and Node Package Manager 9.5.1 or higher
- PostgreSQL installed and configured

### Installation

1. Clone the repository:
```sh
git clone https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-petergubicza
```
2. Build the backend: navigate to the root directory and run this command:
```sh
mvn clean package
```
3. Running the backend:
```sh
java -jar backend/target/trivia.jar
```
4. Build and run the frontend: go to the frontend folder and install NPM packages then start:
```sh
cd frontend
npm install
npm start
```
5. Setup environmental variables for database:
```sh
spring.datasource.url=${DB_URL}
spring.datasource.username=${POSTGRES_USER}
spring.datasource.password=${POSTGRES_PASSWORD}
```
6. Navigate to the specified URL in your web browser:
```sh
http://localhost:5173/
```

## Contact

Gömöri Dávid - gomori.david@gmail.com

Project - https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-petergubicza
