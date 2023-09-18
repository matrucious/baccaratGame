package dsb.intervju.sondre.BaccaratGame.service;

import dsb.intervju.sondre.BaccaratGame.exceptions.InvalidCardException;
import dsb.intervju.sondre.BaccaratGame.exceptions.OutOfCardsException;
import dsb.intervju.sondre.BaccaratGame.model.BaccaratGame;
import dsb.intervju.sondre.BaccaratGame.model.Card;
import dsb.intervju.sondre.BaccaratGame.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String SHUFFLE_API_URL = "https://intervju.dsbnorge.no/api/v1/shuffle";    // Can be replaced with an envar

    public String playBaccaratOnce() {
        List<Card> cards = fetchShuffledDeck();
        if (cards.size() != 52) {
            return "Error fetching or parsing the deck.";
        }

        Deck deck = new Deck(cards);
        BaccaratGame game = new BaccaratGame(deck, "Player");

        try {
            game.dealInitialCards();
            game.playRound();
        } catch (InvalidCardException e) {
            return "Error: " + e.getMessage();
        }
        return game.gameResult();
    }

    public ResponseEntity<String> playBaccarat(String playerName, int numberOfRounds, boolean newDeckEachRound) {
        List<Card> cards = fetchShuffledDeck();
        if (cards.size() != 52) {
            return ResponseEntity.badRequest().body("Error fetching or parsing the deck.");
        }

        Deck deck = new Deck(cards);
        BaccaratGame game = new BaccaratGame(deck, playerName);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numberOfRounds; i++) {
            try {
                game.dealInitialCards();
                game.playRound();
                result.append(game.gameResult()).append("\n\n");
                game.resetRound();
            } catch (InvalidCardException e)
            {
                return ResponseEntity.badRequest().body("Error: " + e.getMessage());
            }
            catch (OutOfCardsException e) {
                result.append("Error: ").append(e.getMessage()).append("\n").append("Completed ").append(i - 1).append(" rounds.");
                break;
                // TODO: Implement logic/option to continue with a new deck
            }

            if (newDeckEachRound) {
                cards = fetchShuffledDeck();
                if (cards.size() != 52) {
                    return ResponseEntity.badRequest().body("Error fetching or parsing the deck during round " + (i + 1) + ".");
                }
                game.setDeck(new Deck(cards));
            }
        }

        return ResponseEntity.ok(result.toString());
    }

    private List<Card> fetchShuffledDeck() {
        Card[] cards = webClientBuilder.build()
                .get()
                .uri(SHUFFLE_API_URL)
                .retrieve()
                .bodyToMono(Card[].class)
                .block();

        assert cards != null;
        return new ArrayList<>(Arrays.asList(cards));

    }
}
