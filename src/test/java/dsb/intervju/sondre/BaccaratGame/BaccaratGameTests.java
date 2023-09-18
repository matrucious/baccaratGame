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

	@Test
	public void testDealInitialCards() {
		game.dealInitialCards();
		String result = game.gameResult();
		// We don't know the exact cards, but we know both players should have cards in their hands.
		assertTrue(result.contains("John"));
		assertTrue(result.contains("Emil"));
	}

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

	@Test
	public void testResetRound() {
		game.dealInitialCards();
		game.resetRound();
		String result = game.gameResult();
		assertTrue(result.contains("Tie!"));
	}

	@Test
	public void testInvalidCardExceptionInDealInitialCards() {
		Deck mockDeck = MockUtils.createMockedDeckWithCards(
				new Card("INVALID", "FIVE")
		);
		game = new BaccaratGame(mockDeck, "John");
		assertThrows(InvalidCardException.class, () -> game.dealInitialCards());
	}

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
