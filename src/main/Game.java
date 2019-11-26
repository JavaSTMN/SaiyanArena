package main;

import main.card.*;

import main.effects.PieceEffect;
import main.model.Board;
import main.model.CardContainer;
import main.model.ManaReserve;
import main.model.Player;
import main.utils.Coin;

public class Game {
    private Coin coin;
    private Player playerOne;
    private Player playerTwo;

    public Player getPlayer(PlaySide side) {
        if(playerOne.getSide() == side)
            return playerOne;

        return playerTwo;
    }

    public Player findPlayer(Player player) {
        if(playerOne.equals(player)) return playerOne;

        return playerTwo;
    }

    public Player getPlayerOne() { return playerOne; }

    public Player getPlayerTwo() { return playerTwo; }

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        coin = new Coin();
    }

    public void beginPlay() {
        initDecks();

        int result = coin.flip();
        System.out.println(result);

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

        getPlayer(PlaySide.WAITING_PLAYER).drawFromDeck();

        Card coin = new Card("La piece", 0);
        coin.addEffect(new PieceEffect(this));
        getPlayer(PlaySide.WAITING_PLAYER).placeInHand(coin);

        beginTurn();
    }

    private void initDecks() {
        CardDatabase db = new CardDatabase(this);
        playerOne.getBoard().setDeck(createDeck(db));
        playerTwo.getBoard().setDeck(createDeck(db));
    }

    private CardContainer<Card> createDeck(CardDatabase db) {
        DeckBuilder builder = new DeckBuilder();

        builder.createEmptyDeck(20)
                .addPair(db.createStonuskBoar())
                .addPair(db.createRaptor())
                .addPair(db.createRaidLeader())
                .addPair(db.createGrizzly())
                .addPair(db.createArcaneMissiles())
                .addPair(db.createArcaneIntellect());

        return builder.build();
    }

    public void attack(IAttacking attacker, ITarget target) {
        attacker.attack(target);
    }

    public void playCard(Card card) {
        Player currentPlayer = getPlayer(PlaySide.ACTIVE_PLAYER);

        if(!canPlayCard(card)) return;

        ManaReserve reserve = currentPlayer.getManaReserve();
        reserve.use(card.getManaCost());

        if(card instanceof Minion) {
            Minion minion = (Minion) card;
            Board board = currentPlayer.getBoard();

            if(!board.getMinions().isFull()) {
                currentPlayer.summonMinion(minion);
            }
        }
        else if(card instanceof Weapon) {
            Weapon weapon = (Weapon) card;
            currentPlayer.equipWeapon(weapon);
        }
        else {
            currentPlayer.playCard(card);
        }

        refresh();
    }

    private boolean canPlayCard(Card card) {
        Player currentPlayer = getPlayer(PlaySide.ACTIVE_PLAYER);
        int manaCost = card.getManaCost();

        if (currentPlayer.hasAvailableMana(manaCost))
            return true;

        return false;
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

        refresh();
    }

    public void endTurn() {
        updateSide();
        beginTurn();
    }

    public void endPhase() {
        HandleMinions(playerOne);
        HandleMinions(playerTwo);

        refresh();
    }

    private void updateSide() {
        Player waitingPlayer = getPlayer(PlaySide.WAITING_PLAYER);

        getPlayer(PlaySide.ACTIVE_PLAYER).setSide(PlaySide.WAITING_PLAYER);
        waitingPlayer.setSide(PlaySide.ACTIVE_PLAYER);
    }

    private void shuffleDecks() {
        playerOne.getDeck().shuffle();
        playerTwo.getDeck().shuffle();
    }

    private void initHand(Player player) {
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

        player.refreshMinions();
    }

    private void refresh() {
        playerOne.refresh();
        playerTwo.refresh();
    }
}
