import java.util.Collections;
import java.util.Random;

public class Game {
    private Player playerOne;
    private Player playerTwo;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void beginPlay() {
        if(flip() > 0){
            playerTwo.setSide(PlaySide.ACTIVE_PLAYER);
            playerOne.setSide(PlaySide.WAITING_PLAYER);
        }
        else
        {
            playerOne.setSide(PlaySide.ACTIVE_PLAYER);
            playerTwo.setSide(PlaySide.WAITING_PLAYER);
        }

        shuffleDecks();

        initHand(playerOne);
        initHand(playerTwo);

        giveExtraToSecondPlayer();

        // Etape draft

        if(playerOne.getSide() == PlaySide.ACTIVE_PLAYER) {
            // debut de tour premier joueur
        }
        else{
            // sinon second joueur
        }
    }

    private void beginTurn() {

    }

    private void shuffleDecks() {
        playerOne.getDeck().shuffle();
        playerTwo.getDeck().shuffle();
    }

    private void initHand(Player player) {
        CardContainer<Card> hand = player.getHand();
        CardContainer<Card> deck = player.getDeck();

        hand.add(deck.peekFirst());
        hand.add(deck.peekFirst());
        hand.add(deck.peekFirst());
    }

    private void giveExtraToSecondPlayer() {
        CardContainer<Card> hand = playerTwo.getHand();

        hand.peekFirst();

        // rajouter carte piece
    }

    private int flip() {
        Random random = new Random();

        return random.nextInt(2);
    }
}

enum PlaySide {
    WAITING_PLAYER,
    ACTIVE_PLAYER,
}
