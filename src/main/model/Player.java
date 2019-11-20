package main.model;

import main.PlaySide;
import main.card.Card;
import main.card.Hero;
import main.card.Minion;

public class Player {
    private CardContainer<Card> hand;
    private CardContainer<Card> deck;
    private CardContainer<Card> graveyard;
    private CardContainer<Minion> board;

    private Hero hero;
    private ManaReserve reserve;
    private PlaySide side;

    int fatigueDamage = 0;

    public Player(Hero hero, CardContainer<Card> hand, CardContainer<Card> deck) {
        this.hero = hero;
        this.hand = hand;
        this.deck = deck;

        reserve = new ManaReserve();
        board = new CardContainer<Minion>();
        graveyard = new CardContainer<Card>();
    }

    public void drawNextCard() {
        if(!deck.isEmpty()) {
            Card card = deck.peekFirst();
            placeInHand(card);
        }
        else {
            fatigueDamage +=1;
            hero.takeDamage(fatigueDamage);
        }
    }

    public void placeInHand(Card card) {
        if(hand.isFull())
            graveyard.add(card);
        else
            hand.add(card);
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

    public ManaReserve getManaReserve() {
        return reserve;
    }

    public CardContainer<Card> getHand() {
        return hand;
    }

    public CardContainer<Card> getDeck() {
        return deck;
    }

    public CardContainer<Minion> getBoard() {
        return board;
    }

    public CardContainer<Card> getGraveyard() { return graveyard; }
}
