package dsb.intervju.sondre.BaccaratGame.model;

import dsb.intervju.sondre.BaccaratGame.exceptions.InvalidCardException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    // Logic Functions
    public void addCardToHand(Card card) {
        if (card == null) {
            throw new InvalidCardException("Card cannot be null.");
        }

        List<String> validSuits = Arrays.asList("DIAMONDS", "CLUBS", "HEARTS", "SPADES");
        List<String> validValues = Arrays.asList("ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING");

        if (!validSuits.contains(card.getSuit()) || !validValues.contains(card.getValue())) {
            throw new InvalidCardException("Invalid card suit or value.");
        }

        hand.add(card);
    }

    public int calculateScore() {
        int totalValue = hand.stream().mapToInt(Card::getCardValue).sum();
        return totalValue % 10;
    }

    public boolean shouldDrawThirdCard() {
        if (hand.isEmpty()) {
            return false;  // Don't draw if the hand is empty
        }

        int score = calculateScore();
        return score <= 5;
    }

    public boolean shouldDefaultPlayerDrawThirdCard(Card playerThirdCard) {
        if (hand.isEmpty()) {
            return false;  // Don't draw if the hand is empty
        }

        int score = calculateScore();

        if (playerThirdCard == null) {
            return score <= 5;
        }

        int playerThirdCardValue = playerThirdCard.getCardValue();

        return switch (score) {
            case 0, 1, 2 -> true;
            case 3 -> playerThirdCardValue != 8;
            case 4 -> playerThirdCardValue >= 2 && playerThirdCardValue <= 7;
            case 5 -> playerThirdCardValue >= 4 && playerThirdCardValue <= 7;
            case 6 -> playerThirdCardValue == 6 || playerThirdCardValue == 7;
            default -> false;  // For scores 7, 8, and 9
        };
    }

    public void clearHand() {
        this.hand.clear();
    }


    @Override
    public String toString() {
        return name + " | " + calculateScore() + " | " + hand;
    }
}
