package main;

import main.model.Player;

public class GameResult {
    private Player winner;
    private int turns;

    public GameResult(Player winner, int turns) {
        this.turns = turns;
        this.winner = winner;
    }

    public int getTurns() {
        return turns;
    }
}
