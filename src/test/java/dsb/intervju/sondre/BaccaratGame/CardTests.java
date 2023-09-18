package dsb.intervju.sondre.BaccaratGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dsb.intervju.sondre.BaccaratGame.model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTests {

    private Card card;

    @BeforeEach
    public void setup() {
        card = new Card();
    }

    @Test
    public void testCardCreation() {
        card.setSuit("DIAMONDS");
        card.setValue("FIVE");

        assertEquals("DIAMONDS", card.getSuit());
        assertEquals("FIVE", card.getValue());
    }

    @Test
    public void testToStringMethod() {
        card.setSuit("DIAMONDS");
        card.setValue("FIVE");

        String expected = "DIAMONDS_5";
        assertEquals(expected, card.toString());

        card.setValue("TEN");
        expected = "DIAMONDS_10";
        assertEquals(expected, card.toString());

        card.setValue("KING");
        expected = "DIAMONDS_KING";
        assertEquals(expected, card.toString());
    }

    @Test
    public void testGetCardValue() {
        card.setSuit("DIAMONDS");
        card.setValue("FIVE");
        assertEquals(5, card.getCardValue());

        card.setValue("TEN");
        assertEquals(0, card.getCardValue());

        card.setValue("ACE");
        assertEquals(1, card.getCardValue());

        card.setValue("KING");
        assertEquals(0, card.getCardValue());
    }
}
