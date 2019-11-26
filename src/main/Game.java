package main;

import main.card.*;

import main.effects.PieceEffect;
import main.events.IBoardListener;
import main.events.IHandListener;
import main.model.Board;
import main.model.ManaReserve;
import main.model.Player;
import main.utils.Coin;

public class Game {
    private Coin coin;
    private Player playerOne;
    private Player playerTwo;

    private IBoardListener boardListener;
    private IHandListener handListener;

    public Player getPlayer(PlaySide side) {
        if(playerOne.getSide() == side)
            return playerOne;

        return playerTwo;
    }

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        coin = new Coin();
    }

    public void addHandListener(IHandListener listener) {
        this.handListener = listener;
    }

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

        getPlayer(PlaySide.WAITING_PLAYER).drawFromDeck();

        Card coin = new Card("La piece", 0);
        coin.addEffect(new PieceEffect(this));
        getPlayer(PlaySide.WAITING_PLAYER).placeInHand(coin);

        beginTurn();
    }

    public void attack(IAttacking attacker, ITarget target) {
        attacker.attack(target);
    }

    public void playCard(Card card) {
        Player currentPlayer = getPlayer(PlaySide.ACTIVE_PLAYER);

        if(!canPlayCard(card)) return;

        if(card instanceof Minion) {
            Minion minion = (Minion) card;
            Board board = currentPlayer.getBoard();

            if(!board.getMinions().isFull()) {
                currentPlayer.summonMinion(minion);
                boardListener.onMinionSummoned(minion);
            }
        }
        else if(card instanceof Weapon) {
            Weapon weapon = (Weapon) card;
            currentPlayer.equipWeapon(weapon);
        }
        else {
            currentPlayer.playCard(card);
        }
    }

    private boolean canPlayCard(Card card) {
        Player currentPlayer = getPlayer(PlaySide.ACTIVE_PLAYER);
        int manaCost = card.getManaCost();

        if (currentPlayer.hasAvailableMana(manaCost))
            return false;

        return true;
    }

    private void beginTurn() {
        Player currentPlayer = getPlayer(PlaySide.ACTIVE_PLAYER);
        currentPlayer.drawFromDeck();

        ManaReserve reserve = currentPlayer.getManaReserve();
        if(!reserve.isFull())
            reserve.fill();

        reserve.refull();

        Board board = currentPlayer.getBoard();
        for (Minion minion : board.getMinions()) {
            minion.active();
        }
    }

    public void endTurn() {
        updateSide();
        beginTurn();
    }

    public void endPhase() {
        HandleMinions(playerOne);
        HandleMinions(playerTwo);
    }

    private void updateSide() {
        getPlayer(PlaySide.ACTIVE_PLAYER).setSide(PlaySide.WAITING_PLAYER);
        getPlayer(PlaySide.WAITING_PLAYER).setSide(PlaySide.ACTIVE_PLAYER);

        getPlayer(PlaySide.ACTIVE_PLAYER).setHandListener(handListener);
    }

    private void shuffleDecks() {
        playerOne.getDeck().shuffle();
        playerTwo.getDeck().shuffle();
    }

    private void initHand(Player player) {
        player.drawFromDeck();
        player.drawFromDeck();
        player.drawFromDeck();
    }

    private void HandleMinions(Player player) {
        Board board = player.getBoard();

        for(Minion minion : board.getMinions()) {
            if(minion.isDead()) {
                board.placeInGraveyard(minion);
            }
        }
    }
}
