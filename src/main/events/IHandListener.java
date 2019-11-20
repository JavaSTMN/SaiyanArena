package main.events;

import main.card.Card;

public interface IHandListener {
    void DrawCardEvent(Card card);
}
