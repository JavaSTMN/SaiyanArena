package main.model;

import main.PlaySide;
import main.card.Card;
import main.card.Hero;
import main.card.Minion;
import main.card.Weapon;
import main.events.IBoardListener;
import main.events.IPlayerListener;

public class Player {
    private Board board;
    private CardContainer<Card> hand;

    private Hero hero;
    private ManaReserve reserve;
    private PlaySide side;

    private IPlayerListener playerListener;
    private IBoardListener boardListener;

    private int fatigueDamage = 0;

    public ManaReserve getManaReserve() {
        return reserve;
    }

    public CardContainer<Card> getHand() {
        return hand;
    }

    public CardContainer<Card> getDeck() {
        return board.getDeck();
    }

    public Board getBoard() {
        return board;
    }

    public Hero getHero() { return hero;}

    public Player(Hero hero, Board board) {
        this.hero = hero;
        this.board = board;

        hand = new CardContainer<>(10);
        reserve = new ManaReserve();
    }

    public void drawFromDeck() {
        Card card = drawNextCard();
        placeInHand(card);
    }

    public void placeInHand(Card card)  {
        if(card == null) return;

        if(!hand.isFull())
            hand.add(card);

        fireDrawEvent();
    }

    private Card drawNextCard() {
        CardContainer<Card> deck = board.getDeck();
        Card card = deck.peekFirst();

        if(card == null) {
            fatigueDamage +=1;
            hero.takeDamage(fatigueDamage);
        }

        return card;
    }

    public void summonMinion(Minion minion) {
        hand.remove(minion);
        minion.executeEffects();
        board.summonMinion(minion);
        boardListener.refresh();
    }

    public void equipWeapon(Weapon weapon) {
        hand.remove(weapon);
        board.summonWeapon(weapon);
    }

    public void playCard(Card card) {
        hand.remove(card);
        card.executeEffects();
    }

    public void setHandListener(IPlayerListener listener) {
        this.playerListener = listener;
    }

    public void setBoardListener(IBoardListener listener) {this.boardListener = listener;}

    public void setSide(PlaySide side) {
        this.side =  side;
    }

    public PlaySide getSide() {
        return side;
    }

    public boolean isDead() {
        return hero.getLifePoints() <= 0;
    }

    public boolean hasAvailableMana(int amount) {
        return reserve.hasAvailable(amount);
    }

    public void refreshMinions() {
        boardListener.refresh();
    }

    public void refresh() {
        playerListener.refresh();
        playerListener.onPlayCard();
    }

    private void fireDrawEvent() {
        if(playerListener != null)
            playerListener.onDrawCard();
    }
}
