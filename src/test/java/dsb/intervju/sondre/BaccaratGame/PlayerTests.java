package dsb.intervju.sondre.BaccaratGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import dsb.intervju.sondre.BaccaratGame.exceptions.InvalidCardException;
import dsb.intervju.sondre.BaccaratGame.model.Card;
import dsb.intervju.sondre.BaccaratGame.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerTests {

    private Player player;

    @BeforeEach
    public void setup() {
        player = new Player("TestPlayer");
    }

    @Test
    public void testPlayerCreationAndCardAddition() {
        Card card = new Card("DIAMONDS", "FIVE");
        player.addCardToHand(card);

        List<Card> hand = player.getHand();
        assertEquals(1, hand.size());
        assertEquals(card, hand.get(0));
    }

    @Test
    public void testCalculateScore() {
        player.addCardToHand(new Card("DIAMONDS", "FIVE"));  // 5
        player.addCardToHand(new Card("HEARTS", "SEVEN"));   // 7

        int score = player.calculateScore(); // 5 + 7 = 12 -> 2
        assertEquals(2, score);
    }

    @Test
    public void testShouldPlayerDrawThirdCardExcluded() {
        player.addCardToHand(new Card("DIAMONDS", "FIVE"));
        assertTrue(player.shouldDrawThirdCard());

        player.addCardToHand(new Card("HEARTS", "SIX"));
        assertTrue(player.shouldDrawThirdCard());
    }

    @Test
    public void testShouldDefaultPlayerDrawThirdCard() {
        player.addCardToHand(new Card("DIAMONDS", "FIVE"));
        player.addCardToHand(new Card("HEARTS", "SIX"));

        Card thirdCard = new Card("CLUBS", "SIX");
        assertTrue(player.shouldDefaultPlayerDrawThirdCard(thirdCard));

        thirdCard = new Card("CLUBS", "EIGHT");
        assertTrue(player.shouldDefaultPlayerDrawThirdCard(thirdCard)); // Modified this line
    }


    @Test
    public void testClearHand() {
        player.addCardToHand(new Card("DIAMONDS", "FIVE"));
        player.clearHand();

        assertEquals(0, player.getHand().size());
    }

    @Test
    public void testToString() {
        player.addCardToHand(new Card("DIAMONDS", "FIVE"));
        player.addCardToHand(new Card("HEARTS", "SEVEN"));

        String expectedOutput = "TestPlayer | 2 | [DIAMONDS_5, HEARTS_7]";
        assertEquals(expectedOutput, player.toString());
    }

    @Test
    public void testCalculateScoreBoundaryValues() {
        // Minimum values
        player.addCardToHand(new Card("DIAMONDS", "ACE"));  // 1
        player.addCardToHand(new Card("HEARTS", "ACE"));    // 1

        int score = player.calculateScore(); // 1 + 1 = 2
        assertEquals(2, score);

        player.clearHand();

        // Maximum values (non-zero)
        player.addCardToHand(new Card("DIAMONDS", "NINE"));  // 9
        player.addCardToHand(new Card("HEARTS", "NINE"));    // 9

        score = player.calculateScore(); // 9 + 9 = 18 -> 8
        assertEquals(8, score);
    }

    @Test
    public void testEmptyHand() {
        assertEquals(0, player.calculateScore());
        assertFalse(player.shouldDrawThirdCard());
        assertFalse(player.shouldDefaultPlayerDrawThirdCard(null));
    }

    @Test
    public void testExcessiveCardsInHand() {
        for (int i = 0; i < 5; i++) {
            player.addCardToHand(new Card("DIAMONDS", "FIVE"));
        }
        assertEquals(5, player.calculateScore()); // Only the first two should be considered.
    }

    @Test
    public void testInvalidCardValues() {
        // Expect an exception when adding a card with an invalid suit
        assertThrows(InvalidCardException.class, () -> {
            player.addCardToHand(new Card("INVALID_SUIT", "FIVE"));
        });

        // Expect an exception when adding a card with an invalid value
        assertThrows(InvalidCardException.class, () -> {
            player.addCardToHand(new Card("DIAMONDS", "INVALID_VALUE"));
        });
    }



}
