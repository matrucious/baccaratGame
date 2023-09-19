package dsb.intervju.sondre.BaccaratGame;

import dsb.intervju.sondre.BaccaratGame.model.BaccaratGame;
import dsb.intervju.sondre.BaccaratGame.model.Card;
import dsb.intervju.sondre.BaccaratGame.model.Deck;
import dsb.intervju.sondre.BaccaratGame.exceptions.InvalidCardException;
import dsb.intervju.sondre.BaccaratGame.utils.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BaccaratGameTests {

	private BaccaratGame game;

	@BeforeEach
	public void setup() {
		Deck mockDeck = MockUtils.createMockedFullDeck();
		game = new BaccaratGame(mockDeck, "John");
	}

	// Test that the game is initialized correctly
	@Test
	public void testDealInitialCards() {
		game.dealInitialCards();
		String result = game.gameResult();
		assertTrue(result.contains("John"));
		assertTrue(result.contains("Emil"));
	}

	// Test that the game plays as intended
	@Test
	public void testPlayRoundNaturalWin() {
		Deck mockDeck = MockUtils.createMockedDeckWithCards(
				new Card("DIAMONDS", "EIGHT"), new Card("SPADES", "TWO"),
				new Card("DIAMONDS", "NINE"), new Card("SPADES", "THREE"),
				new Card("DIAMONDS", "FOUR"), new Card("SPADES", "FIVE"),
				new Card("DIAMONDS", "SIX"), new Card("SPADES", "SEVEN")
		);
		game = new BaccaratGame(mockDeck, "John");
		game.dealInitialCards();
		game.playRound();
		assertTrue(game.gameResult().contains("Winner: Emil"));
	}

	// Test for making sure a third card is being used by player
	@Test
	public void testPlayRoundThirdCardDrawn() {
		Deck mockDeck = MockUtils.createMockedDeckWithCards(
				new Card("DIAMONDS", "TWO"), new Card("SPADES", "THREE"),
				new Card("DIAMONDS", "THREE"), new Card("SPADES", "FOUR"),
				new Card("DIAMONDS", "FIVE")
		);
		game = new BaccaratGame(mockDeck, "John");
		game.dealInitialCards();
		game.playRound();
		String result = game.gameResult();
		assertTrue(result.contains("John | 0")); // 2 + 3 + 5 = 10 % 10 = 0
	}

	// Test for reset logic
	@Test
	public void testResetRound() {
		game.dealInitialCards();
		game.resetRound();
		String result = game.gameResult();
		assertTrue(result.contains("Tie!"));
	}

	// Test for invalid card exception in dealing initial cards
	@Test
	public void testInvalidCardExceptionInDealInitialCards() {
		Deck mockDeck = MockUtils.createMockedDeckWithCards(
				new Card("INVALID", "FIVE")
		);
		game = new BaccaratGame(mockDeck, "John");
		assertThrows(InvalidCardException.class, () -> game.dealInitialCards());
	}

	// Test for invalid card exception in play round logic
	@Test
	public void testInvalidCardExceptionInPlayRound() {
		Deck mockDeck = MockUtils.createMockedDeckWithCards(
				new Card("DIAMONDS", "TWO"), new Card("SPADES", "THREE"),
				new Card("DIAMONDS", "THREE"), new Card("SPADES", "FOUR"),
				new Card("INVALID", "FIVE")
		);
		game = new BaccaratGame(mockDeck, "John");
		game.dealInitialCards();
		assertThrows(InvalidCardException.class, () -> game.playRound());
	}
}
