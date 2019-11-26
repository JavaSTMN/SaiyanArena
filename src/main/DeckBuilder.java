package main;

import main.card.Card;
import main.model.CardContainer;

public class DeckBuilder {
    private CardContainer<Card> deck;

    public DeckBuilder createEmptyDeck(int maxElements) {
        deck = new CardContainer<>(20);

        return this;
    }

    public DeckBuilder addPair(Card card) {
        this.addCard(card).addCard(card);

        return this;
    }

    public DeckBuilder addCard(Card card) {
        deck.add(card);

        return this;
    }

    public CardContainer<Card> build() {
        return deck;
    }
}
