# Baccarat Game Project

This project simulates the Baccarat card game. Players are dealt cards, and based on the Baccarat rules, they might draw a third card. The winner is determined based on the scores.

## Prerequisites

- Java JDK (recommended version 20 or above)
- Maven (for dependency management and running tests)

## Installation and Setup

1. Clone the repository:
   ```
   git clone https://github.com/sondre-izy/baccaratGame
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


## Using the RESTful API

The application offers a RESTful API to play the game and view results. The API is documented using Swagger, and can be accessed at the following URL:
```
http://localhost:8080/swagger-ui.html
```

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

# Case text - Baccarat Game 
https://intervju.dsbnorge.no/

##  Kan du slå Emil i Baccarat? 

### Oppgaven

- Besvarelsen skal inneholde tester
- Besvarelsen skal benytte et byggeverktøy
- Besvarelsen må kunne bytte endepunkt uten å måtte rekompilere koden
- Det er nok at besvarelsen skriver til standard output
- Du må gjerne lage en frontend hvis du vil vise din frontendkompetanse

## Regler
### Relger (1)
- To spillere, du og Emil, skal spille mot hverandre
- Hver spiller tar to kort hver fra toppen av en tilfeldig stokket kortstokk.
  - Endepunktet finner du her: https://intervju.dsbnorge.no/api/v1/shuffle
- Regn ut den samlede poengsummen til hver spiller
- Poengsummen for hånden er totalen av verdien på kortene modulo 10
  - Nummererte kort mellom 2 og 9 har poeng som angitt på kortet
  - 10 (TEN), Knekt (JACK), Dronning (QUEEN) og Konge (KING) teller som 0 poeng
  - Ess (ACE) teller som 1 poeng

### Regler (2)
- Dersom du eller Emil får en totalverdi på enten 8 eller 9 på den initielle hånden så trekkes ikke flere kort
- Avhengig av poengsummen på hånden så fortsetter eventuelt spillet slik:
- Dersom du har en initiell verdi mellom 0-5 så trekker du et tredje kort
- Dersom du har en initiell verdi mellom 6-7 så trekker du ikke et tredje kort
- Emil trekker et tredje kort avhengig av poengsummen til din hånd
  - Dersom du ikke trakk et kort, så trekker Emil et dersom han har mellom 0-5
  - Dersom du du trakk 2 (TWO) eller 3 (THREE), så trekker Emil dersom han har mellom 0-4
  - Dersom du du trakk 4 (FOUR) eller 5 (FIVE), så trekker Emil dersom han har mellom 0-5
  - Dersom du du trakk 6 (SIX) eller 7 (SEVEN), så trekker Emil dersom han har mellom 0-6
  - Dersom du du trakk 8 (EIGHT), så trekker Emil dersom han har mellom 0-2
  - Dersom du trakk et ESS (ACE), 9 (NINE), 10 (TEN), Knekt (JACK), Dronning (QUEEN) eller Konge (KING), så trekker Emil dersom han har mellom 0-3
  - Spilleren med den høyeste poengsummen vinner spillet

### Regler (3)
- Skriv ut hvem som vant spillet
- Skriv ut poengsummen og kortene til hver spiller. Et kort skrives ut ved å konkatinere suit og value
- Eksempel på output:
```
Vinner: Maja
Emil | 6 | SPADES_4,SPADES_2
Maja | 8 | DIAMONDS_8,DIAMONDS_QUEEN
```

### Om kortstokken
- Kortstokken finnes på https://intervju.dsbnorge.no/api/v1/shuffle
- Kortstokken er et array på 52 elementer
- Hvert element er et objekt med egenskapene suit og value
- suit -> [HEARTS, DIAMONDS, SPADES, CLUBS]
- value -> [TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE]

Eksempel output fra endepunktet:

```
[
  {
    "suit": "CLUBS",
    "value": "KING"
  },
  {
    "suit": "DIAMONDS",
    "value": "SIX"
  },
  {
    "suit": "SPADES",
    "value": "KING"
  },
  {
    "suit": "DIAMONDS",
    "value": "TWO"
  },
  {
    "suit": "DIAMONDS",
    "value": "FOUR"
  },
  {
    "suit": "CLUBS",
    "value": "FIVE"
  },
  {
    "suit": "HEARTS",
    "value": "QUEEN"
  },
  {
    "suit": "HEARTS",
    "value": "JACK"
  },
  {
    "suit": "DIAMONDS",
    "value": "EIGHT"
  },
  {
    "suit": "CLUBS",
    "value": "NINE"
  },
  {
    "suit": "HEARTS",
    "value": "TWO"
  },
  {
    "suit": "DIAMONDS",
    "value": "QUEEN"
  },
  {
    "suit": "SPADES",
    "value": "TWO"
  },
  {
    "suit": "HEARTS",
    "value": "FIVE"
  },
  {
    "suit": "HEARTS",
    "value": "THREE"
  },
  {
    "suit": "HEARTS",
    "value": "SEVEN"
  },
  {
    "suit": "CLUBS",
    "value": "SIX"
  },
  {
    "suit": "SPADES",
    "value": "ACE"
  },
  {
    "suit": "SPADES",
    "value": "FOUR"
  },
  {
    "suit": "HEARTS",
    "value": "TEN"
  },
  {
    "suit": "HEARTS",
    "value": "NINE"
  },
  {
    "suit": "CLUBS",
    "value": "THREE"
  },
  {
    "suit": "SPADES",
    "value": "EIGHT"
  },
  {
    "suit": "CLUBS",
    "value": "TEN"
  },
  {
    "suit": "DIAMONDS",
    "value": "JACK"
  },
  {
    "suit": "CLUBS",
    "value": "TWO"
  },
  {
    "suit": "DIAMONDS",
    "value": "TEN"
  },
  {
    "suit": "HEARTS",
    "value": "FOUR"
  },
  {
    "suit": "HEARTS",
    "value": "KING"
  },
  {
    "suit": "SPADES",
    "value": "SEVEN"
  },
  {
    "suit": "HEARTS",
    "value": "ACE"
  },
  {
    "suit": "HEARTS",
    "value": "SIX"
  },
  {
    "suit": "SPADES",
    "value": "NINE"
  },
  {
    "suit": "DIAMONDS",
    "value": "SEVEN"
  },
  {
    "suit": "DIAMONDS",
    "value": "THREE"
  },
  {
    "suit": "SPADES",
    "value": "TEN"
  },
  {
    "suit": "DIAMONDS",
    "value": "KING"
  },
  {
    "suit": "SPADES",
    "value": "QUEEN"
  },
  {
    "suit": "SPADES",
    "value": "SIX"
  },
  {
    "suit": "DIAMONDS",
    "value": "NINE"
  },
  {
    "suit": "CLUBS",
    "value": "FOUR"
  },
  {
    "suit": "DIAMONDS",
    "value": "ACE"
  },
  {
    "suit": "SPADES",
    "value": "JACK"
  },
  {
    "suit": "CLUBS",
    "value": "QUEEN"
  },
  {
    "suit": "SPADES",
    "value": "FIVE"
  },
  {
    "suit": "DIAMONDS",
    "value": "FIVE"
  },
  {
    "suit": "CLUBS",
    "value": "JACK"
  },
  {
    "suit": "CLUBS",
    "value": "SEVEN"
  },
  {
    "suit": "CLUBS",
    "value": "EIGHT"
  },
  {
    "suit": "CLUBS",
    "value": "ACE"
  },
  {
    "suit": "SPADES",
    "value": "THREE"
  },
  {
    "suit": "HEARTS",
    "value": "EIGHT"
  }
]
```