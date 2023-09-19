package dsb.intervju.sondre.BaccaratGame.model;

public class BaccaratGame {

    private final Player player;
    private final Player banker;
    private Deck deck;


    public BaccaratGame(Deck deck, String playerName) {
        this.deck = deck;
        this.player = new Player(playerName);
        this.banker = new Player("Emil");
    }

    // Deal two cards to each player
    public void dealInitialCards() {
        player.addCardToHand(deck.drawCard());
        banker.addCardToHand(deck.drawCard());
        player.addCardToHand(deck.drawCard());
        banker.addCardToHand(deck.drawCard());
    }

    public void playRound() {
        // Check if either player has a natural win
        if (player.calculateScore() >= 8 || banker.calculateScore() >= 8) {
            return;
        }

        // Determine if you should draw a third card
        if (player.shouldDrawThirdCard()) {
            player.addCardToHand(deck.drawCard());
        }

        // Determine if Emil should draw a third card
        Card yourThirdCard = (player.getHand().size() >= 3) ? player.getHand().get(2) : null;
        if (banker.shouldDefaultPlayerDrawThirdCard(yourThirdCard)) {
            banker.addCardToHand(deck.drawCard());
        }
    }

    // Returns a string with the result of the game
    public String gameResult() {
        int youScore = player.calculateScore();
        int bankerScore = banker.calculateScore();

        if (youScore > bankerScore) {
            return "Winner: " + player.getName() + "\n" + player + "\n" + banker;
        } else if (youScore < bankerScore) {
            return "Winner: " + banker.getName() + "\n" + player + "\n" + banker;
        } else {
            return "Tie!\n" + player + "\n" + banker;
        }
    }

    // Resets the game for a new round
    public void resetRound() {
        player.clearHand();
        banker.clearHand();
    }

    // Set deck for new round in case of new deck each round
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

}
