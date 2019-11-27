package main;

import main.card.IAttacking;
import main.card.ITarget;
import main.model.Player;

public class AttackTool {
    private GameInteraction gameInteraction;

    private IAttacking attacker;
    private ITarget target;

    private IAttacking targetAsAttacker;
    private ITarget attackerAsTarget;

    public AttackTool(GameInteraction gameInteraction) {
        this.gameInteraction = gameInteraction;
    }

    public void setAttacker(IAttacking attacker) {
        this.attacker = attacker;
    }

    public void setReposter(IAttacking reposter) {
        this.targetAsAttacker = reposter;
    }

    public void setTarget(ITarget target) {
        this.target = target;
    }

    public void setAttackerTarget(ITarget target) {
        this.attackerAsTarget = target;
    }

    public void executeAttack() {
        if(attacker == null) return;

        if(!attacker.canAttack()) return;

        Game game = gameInteraction.getGame();
        Player currentPlayer = game.getPlayer(PlaySide.ACTIVE_PLAYER);
        Player waitingPlayer = game.getPlayer(PlaySide.WAITING_PLAYER);

        gameInteraction.attack(currentPlayer, attacker, target);

        if(targetAsAttacker != null)
            gameInteraction.attack(waitingPlayer, targetAsAttacker, attackerAsTarget);
    }
}
