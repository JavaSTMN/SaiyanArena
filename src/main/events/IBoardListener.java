package main.events;

import main.card.Minion;

public interface IBoardListener {
    void MinionPlacedEvent(Minion minion);
    void MinionDiedEvent(Minion minion);
}
