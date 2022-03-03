# TradeIn-Website

## About The Project

**Important** This project complements [https://github.com/Joaodss/TradeIn-Website-client](https://github.com/Joaodss/TradeIn-Website-client)

## Required Thecnologies

- Java 17
- Maven
- MySQL

## Project Setup
 
 - Clone the repository repository:
  - <https://github.com/Joaodss/TradeIn-Website>

- Setup the following database name and user, or set up your database by changing the configuration in the `application.properties` file

```sql
CREATE DATABASE TradeIn;

CREATE USER 'tradeIn-admin'@'localhost' IDENTIFIED BY 'tradeIn-123';
GRANT ALL PRIVILEGES ON TradeIn.* TO 'tradeIn-admin'@'localhost';
FLUSH PRIVILEGES;
```

- Create google Service account creadential and save the credential `service_credentials.json` at `\src\main\resources\`

- Run the following command to start each spring application: `mvn spring-boot:run` (rquires Java 17 and Maven), or by using an IDE

- The application will be available at [http://localhost:8080/](http://localhost:8080/) (changin this value will throw CORS error)



