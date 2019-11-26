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
        Player currentPlayer = game.getPlayer(PlaySide.ACTIVE_PLAYER);
        ManaReserve reserve = currentPlayer.getManaReserve();

        reserve.setActualAvailable(2);
    }
}
