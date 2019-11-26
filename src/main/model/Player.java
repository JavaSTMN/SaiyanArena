package main.model;

import main.PlaySide;
import main.card.Card;
import main.card.Hero;
import main.card.Minion;
import main.card.Weapon;
import main.events.IHandListener;

public class Player {
    private Board board;
    private CardContainer<Card> hand;

    private Hero hero;
    private ManaReserve reserve;
    private PlaySide side;

    private IHandListener listener;

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

        hand = new CardContainer<>(8);
        reserve = new ManaReserve();
    }

    public void drawFromDeck() {
        Card card = drawNextCard();
        placeInHand(card);
    }

    public void placeInHand(Card card)  {
        boolean fullHand = hand.isFull();

        if(!fullHand)
            hand.add(card);

        fireDrawEvent(card, fullHand);
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
        board.summonMinion(minion);
    }

    public void equipWeapon(Weapon weapon) {
        hand.remove(weapon);
        board.summonWeapon(weapon);
    }

    public void playCard(Card card) {
        hand.remove(card);
        card.executeEffects();
        firePlayEvent(card);
    }

    public void setHandListener(IHandListener listener) {
        this.listener = listener;
    }

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

    private void firePlayEvent(Card card) {
        if(listener != null)
            listener.onPlayCard(card);
    }

    private void fireDrawEvent(Card card, boolean fullHand) {
        if(listener != null)
            listener.onDrawCard(card, fullHand);
    }
}
