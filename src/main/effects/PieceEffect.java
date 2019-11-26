package main.effects;

import main.Game;
import main.PlaySide;
import main.model.ManaReserve;
import main.model.Player;

public class PieceEffect implements IEffect {
    private Player currentPlayer;

    public PieceEffect(Game game) {
        this.currentPlayer = game.getPlayer(PlaySide.ACTIVE_PLAYER);
    }

    @Override
    public void execute() {
        ManaReserve reserve = currentPlayer.getManaReserve();

        reserve.setActualAvailable(2);
    }
}
