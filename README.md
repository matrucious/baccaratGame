# Baccarat Game Project

This project simulates the Baccarat card game. Players are dealt cards, and based on the Baccarat rules, they might draw a third card. The winner is determined based on the scores.

## Prerequisites

- Java JDK (recommended version 20 or above)
- Maven (for dependency management and running tests)

## Installation and Setup

1. Clone the repository:
   ```
   git clone [https://github.com/sondre-izy/baccaratGame](https://github.com/sondre-izy/baccaratGame)
   ```

2. Navigate to the project directory:
   ```
   cd BaccaratGame
   ```

3. Install the required dependencies using Maven:
   ```
   mvn install
   ```

## Running the Application

To run the Baccarat game simulation:
```
java -jar target/BaccaratGame-0.0.1-SNAPSHOT.jar
```

Note: Ensure you have built the project using Maven before attempting to run the jar.

## Running Tests

To run the tests for the project:
```
mvn test
```

## Features

- Simulates a game of Baccarat between a player and a banker named Emil.
- Provides error handling for invalid cards and when the deck runs out of cards.
- Offers a RESTful API to play the game and view results.
- Offers a Swagger UI for simple usage of the applicatioon

## Troubleshooting

If you encounter any issues while setting up or running the project, please check the following:

1. Ensure you have the correct version of Java and Maven installed.
2. Ensure all dependencies are correctly installed using `mvn install`.
3. Check the application logs for any errors or exceptions.

## Contributing

As this is the proposed solution to a case, there are no contributions expected. Please inform the author if any issues arise with this

