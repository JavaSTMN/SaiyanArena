package main.model;

import main.card.Card;
import main.card.Minion;
import main.card.Weapon;

public class Board {
    private CardContainer<Card> deck;
    private CardContainer<Minion> graveyard;
    private CardContainer<Minion> minions;

    private Weapon weapon;

    public CardContainer<Minion> getMinions() {
        return minions;
    }

    public CardContainer<Card> getDeck() {
        return deck;
    }

    public CardContainer<Minion> getGraveyard() {
        return graveyard;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Board() {
        minions = new CardContainer<>(7);
        graveyard = new CardContainer<>();
    }

    public void setDeck(CardContainer<Card> deck) {
        this.deck = deck;
    }

    public void summonMinion(Minion minion) {
        minions.add(minion);
    }

    public void summonWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void placeInGraveyard(Minion minion) {
        minions.remove(minion);
        graveyard.add(minion);
    }
}
