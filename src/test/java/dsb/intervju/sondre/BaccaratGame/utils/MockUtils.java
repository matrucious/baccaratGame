package dsb.intervju.sondre.BaccaratGame.utils;

import dsb.intervju.sondre.BaccaratGame.model.Card;
import dsb.intervju.sondre.BaccaratGame.model.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MockUtils {

    public static Deck createMockedFullDeck() {
        List<Card> fullDeck = generateFullDeck();
        return new Deck(fullDeck);
    }

    public static Deck createMockedDeckWithCards(Card... cards) {
        List<Card> specificDeck = new ArrayList<>(Arrays.asList(cards));
        return new Deck(specificDeck);
    }

    private static List<Card> generateFullDeck() {
        List<Card> fullDeck = new ArrayList<>();
        String[] suits = {"DIAMONDS", "SPADES", "HEARTS", "CLUBS"};
        String[] values = {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"};

        for (String suit : suits) {
            for (String value : values) {
                fullDeck.add(new Card(suit, value));
            }
        }

        Collections.shuffle(fullDeck);  // Randomize the order of the cards

        return fullDeck;
    }
}
