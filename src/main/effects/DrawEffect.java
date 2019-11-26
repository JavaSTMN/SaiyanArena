package main.effects;

import main.Game;
import main.PlaySide;
import main.model.Player;

public class DrawEffect implements IEffect {
    private Player currentPlayer;
    private int cardNumber;

    public DrawEffect(Game game, int cardNumber) {
        this.currentPlayer = game.getPlayer(PlaySide.ACTIVE_PLAYER);
        this.cardNumber = cardNumber;
    }

    @Override
    public void execute() {
        for(int i=0; i<cardNumber; i++) {
            currentPlayer.drawFromDeck();
        }
    }
}
