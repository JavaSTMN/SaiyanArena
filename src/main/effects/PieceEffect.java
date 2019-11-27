package main.effects;

import main.Game;
import main.PlaySide;
import main.model.ManaReserve;
import main.model.Player;

public class PieceEffect implements IEffect {
    private Game game;

    public PieceEffect(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        ManaReserve reserve = game.getPlayer(PlaySide.ACTIVE_PLAYER).getManaReserve();

        reserve.addMana(1);
    }
}
