package main.events;

import main.card.Minion;

public interface IBoardListener {
    void onMinionSummoned(Minion minion);
    void onMinionDied(Minion minion);
}
