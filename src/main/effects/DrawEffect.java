package main.effects;

import main.Game;
import main.PlaySide;
import main.model.Player;

public class DrawEffect implements IEffect {
    private Game game;
    private int cardNumber;

    public DrawEffect(Game game, int cardNumber) {
        this.game = game;
        this.cardNumber = cardNumber;
    }

    @Override
    public void execute() {
        Player currentPlayer = game.getPlayer(PlaySide.ACTIVE_PLAYER);

        for(int i=0; i<cardNumber; i++) {
            currentPlayer.drawFromDeck();
        }
    }
}
