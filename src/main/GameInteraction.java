package main;

import main.card.Card;
import main.card.IAttacking;
import main.card.ITarget;
import main.model.Player;

public class GameInteraction {
    private Game game;

    public Game getGame() {
        return game;
    }

    public GameInteraction(Game game) {
        this.game = game;
    }

    private void doGameAction(Player player, GameAction action) {
        action.alterGame(game);
        game.endPhase();
    }

    public void endTurn(Player player) {
        if(!game.getPlayer(PlaySide.ACTIVE_PLAYER).equals(player)) return;

        doGameAction(player, Game::endTurn);
    }

    public void attack(Player player, IAttacking attacker, ITarget target) {
        doGameAction(player, (currentGame) -> currentGame.attack(attacker, target));
    }

    public void playCard(Player player, Card card) {
        if(!game.getPlayer(PlaySide.ACTIVE_PLAYER).equals(player)) return;

        doGameAction(player, (currentGame) -> currentGame.playCard(card));
    }
}
