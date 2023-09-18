package dsb.intervju.sondre.BaccaratGame.model;

public class Card {
    private String suit;
    private String value;

    public Card() {}

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }


    // Getters and Setters
    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return switch (this.value) {
            case "ACE" -> this.suit + "_1";
            case "TWO" -> this.suit + "_2";
            case "THREE" -> this.suit + "_3";
            case "FOUR" -> this.suit + "_4";
            case "FIVE" -> this.suit + "_5";
            case "SIX" -> this.suit + "_6";
            case "SEVEN" -> this.suit + "_7";
            case "EIGHT" -> this.suit + "_8";
            case "NINE" -> this.suit + "_9";
            case "TEN" -> this.suit + "_10";
            default -> this.suit + "_" + this.value;
        };
    }

    public int getCardValue() {
        return switch (this.value) {
            case "ACE" -> 1;
            case "TWO" -> 2;
            case "THREE" -> 3;
            case "FOUR" -> 4;
            case "FIVE" -> 5;
            case "SIX" -> 6;
            case "SEVEN" -> 7;
            case "EIGHT" -> 8;
            case "NINE" -> 9;
            default -> 0;  // For TEN, JACK, QUEEN, KING
        };
    }

}
