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

        getPlayer(PlaySide.WAITING_PLAYER).drawNextCard();
        // faire piocher la piece au waiting player

        // Etape draft

        beginTurn();
    }

    private void beginTurn() {
        Player currentPlayer = getPlayer(PlaySide.ACTIVE_PLAYER);
        currentPlayer.drawNextCard();

        ManaReserve reserve = currentPlayer.getManaReserve();
        if(!reserve.isFull())
            reserve.fill();

        reserve.refull();
    }

    private void endTurn() {

    }

    private Player getPlayer(PlaySide side) {
        if(playerOne.getSide() == side)
            return playerOne;

        return playerTwo;
    }

    private void checkGameOver() {

    }

    private void shuffleDecks() {
        playerOne.getDeck().shuffle();
        playerTwo.getDeck().shuffle();
    }

    private void initHand(Player player) {
        player.drawNextCard();
        player.drawNextCard();
        player.drawNextCard();
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
