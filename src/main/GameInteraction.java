package main;

import main.card.Card;
import main.card.IAttacking;
import main.card.ITarget;

public class GameInteraction {
    private Game game;

    public GameInteraction(Game game) {
        this.game = game;
    }

    public void doGameAction(GameAction action) {
        action.alterGame(game);
        game.endPhase();
    }

    public void endTurn() {
        doGameAction(Game::endTurn);
    }

    public void attack(IAttacking attacker, ITarget target) {
        doGameAction((currentGame) -> currentGame.attack(attacker, target));
    }

    public void playCard(Card card) {
        doGameAction((currentGame) -> currentGame.playCard(card));
    }
}
