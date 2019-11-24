package main.model;

import main.card.Card;
import main.card.Minion;
import main.card.Weapon;

public class Board {
    private CardContainer<Card> deck;
    private CardContainer<Minion> graveyard;
    private CardContainer<Minion> minions;

    private Weapon weapon;

    public Board(CardContainer<Card> deck) {
        this.deck = deck;

        minions = new CardContainer<>(7);
        graveyard = new CardContainer<>();
    }

    public void summonMinion(Minion minion) {
        minions.add(minion);
    }

    public void summonWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void placeInGraveyard(Minion minion) {
        graveyard.add(minion);
    }

    public CardContainer<Minion> getMinions() {
        return minions;
    }

    public CardContainer<Card> getDeck() {
        return deck;
    }

    public CardContainer<Minion> getGraveyard() {
        return graveyard;
    }
}
