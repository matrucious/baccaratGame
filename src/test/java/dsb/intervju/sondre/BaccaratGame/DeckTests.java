package dsb.intervju.sondre.BaccaratGame;

import dsb.intervju.sondre.BaccaratGame.model.Card;
import dsb.intervju.sondre.BaccaratGame.model.Deck;
import dsb.intervju.sondre.BaccaratGame.exceptions.OutOfCardsException;
import dsb.intervju.sondre.BaccaratGame.utils.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTests {

    private Deck deck;

    @BeforeEach
    public void setup() {
        deck = MockUtils.createMockedFullDeck();
    }

    @Test
    public void testDeckSizeUponCreation() {
        assertEquals(52, deck.size());
    }

    @Test
    public void testDeckHasNoDuplicates() {
        Set<Card> uniqueCards = new HashSet<>(deck.getCards());
        assertEquals(52, uniqueCards.size());
    }

    @Test
    public void testDrawingCardReducesDeckSize() {
        deck.drawCard();
        assertEquals(51, deck.size());
    }

    @Test
    public void testDrawingAllCardsEmptiesDeck() {
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        assertTrue(deck.isEmpty());
    }

    @Test
    public void testDrawingFromEmptyDeckThrowsException() {
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        assertThrows(OutOfCardsException.class, () -> deck.drawCard());
    }
}
