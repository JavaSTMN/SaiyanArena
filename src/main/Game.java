package main;

import main.card.Card;
import main.card.Minion;
import main.model.CardContainer;
import main.model.ManaReserve;
import main.model.Player;
import main.utils.Coin;

import java.util.Random;

public class Game {

    private Coin coin;
    private Player playerOne;
    private Player playerTwo;

    private int turnNumber;
    private int sideSwitched;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        coin = new Coin();

        turnNumber = 0;
        sideSwitched = 0;
    }

    public void beginPlay() {
        int result = coin.flip();

        if(result> 0){
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

        beginTurn();
    }

    private void beginTurn() {
        if(sideSwitched%2 < 1)
            turnNumber++;

        Player currentPlayer = getPlayer(PlaySide.ACTIVE_PLAYER);
        currentPlayer.drawNextCard();

        ManaReserve reserve = currentPlayer.getManaReserve();
        if(!reserve.isFull())
            reserve.fill();

        reserve.refull();

        CardContainer<Minion> currentPlayerBoard = currentPlayer.getBoard();

        for (Minion minion : currentPlayerBoard) {
            minion.active();
        }
    }

    private void endTurn() {
        HandleMinions(getPlayer(PlaySide.ACTIVE_PLAYER));
        HandleMinions(getPlayer(PlaySide.WAITING_PLAYER));

        updateSide();

        beginTurn();
    }

    private GameResult CheckGameOver() {
        if(playerOne.isDead())
            return new GameResult(playerTwo, turnNumber);

        if(playerTwo.isDead())
            return new GameResult(playerOne, turnNumber);

        return null;
    }

    public void updateSide() {
        getPlayer(PlaySide.ACTIVE_PLAYER).setSide(PlaySide.WAITING_PLAYER);
        getPlayer(PlaySide.WAITING_PLAYER).setSide(PlaySide.ACTIVE_PLAYER);

        sideSwitched++;
    }

    private Player getPlayer(PlaySide side) {
        if(playerOne.getSide() == side)
            return playerOne;

        return playerTwo;
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

    private void HandleMinions(Player player) {
        CardContainer<Minion> board = player.getBoard();
        CardContainer<Card> graveyard = player.getGraveyard();

        for(Minion minion : board) {
            if(minion.isDead()) {
                board.remove(minion);
                graveyard.add(minion);
            }
        }
    }
}

