package main;

import main.card.Card;
import main.card.Minion;
import main.events.IBoardListener;
import main.events.IHandListener;
import main.model.CardContainer;
import main.model.ManaReserve;
import main.model.Player;
import main.utils.Coin;

import java.util.Collections;
import java.util.Random;

public class Game {
    private Coin coin;
    private Player playerOne;
    private Player playerTwo;

    private int turnNumber;
    private int sideSwitched;

    private IBoardListener boardListener;
    private IHandListener handListener;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        coin = new Coin();

        turnNumber = 0;
        sideSwitched = 0;
    }

    public void addHandListener(IHandListener listener) { this.handListener = listener; }

    public void addBoardListener(IBoardListener listener) {
        this.boardListener = listener;
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

        getPlayer(PlaySide.ACTIVE_PLAYER).setHandListener(handListener);

        shuffleDecks();
        initHand(playerOne);
        initHand(playerTwo);

        getPlayer(PlaySide.WAITING_PLAYER).drawNextCard();

        beginTurn();
    }

    public void playCard(Card card) {
        Player currentPlayer = getPlayer(PlaySide.ACTIVE_PLAYER);
        CardContainer<Card> hand = currentPlayer.getHand();

        if(card instanceof Minion) {
            Minion minion = (Minion) card;
            trySummonMinion(currentPlayer, hand, minion);
        }
        else {
            CardContainer<Card> graveyard = currentPlayer.getGraveyard();

            hand.remove(card);
            card.executeEffects();
            graveyard.add(card);
        }
    }

    public void trySummonMinion(Player player, CardContainer<Card> hand, Minion minion) {
        CardContainer<Minion> board = player.getBoard();
        if(!board.isFull()) {
            summonMinion(board, hand, minion);
            boardListener.onMinionSummoned(minion);
        }
    }

    private void summonMinion(CardContainer<Minion> board, CardContainer<Card> hand, Minion minion) {
        hand.remove(minion);
        board.add(minion);
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

    private void updateSide() {
        getPlayer(PlaySide.ACTIVE_PLAYER).setSide(PlaySide.WAITING_PLAYER);
        getPlayer(PlaySide.WAITING_PLAYER).setSide(PlaySide.ACTIVE_PLAYER);

        getPlayer(PlaySide.ACTIVE_PLAYER).setHandListener(handListener);

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

