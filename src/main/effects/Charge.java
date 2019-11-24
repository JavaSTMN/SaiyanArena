package main.effects;

import main.card.Minion;

public class Charge implements IEffect {
    private Minion minion;

    public Charge(Minion minion) {
        this.minion = minion;
    }

    @Override
    public void execute() {
        minion.active();
    }
}
