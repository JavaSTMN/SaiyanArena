package main.effects;

import main.Game;
import main.PlaySide;
import main.card.Minion;
import main.model.Board;
import main.model.CardContainer;

import java.util.ArrayList;

public class AttackBuff implements IEffect, IUndoable {
    private Board board;
    private int attackBonus;
    private CardContainer<Minion> buffedMinions;

    public AttackBuff(Game game, int amount) {
        this.board = game.getPlayer(PlaySide.ACTIVE_PLAYER).getBoard();
        attackBonus = amount;

        buffedMinions = new CardContainer<>();
    }

    @Override
    public void execute() {
        buffedMinions = board.getMinions().Clone();

        for(Minion minion : buffedMinions) {
            minion.addAttack(attackBonus);
        }
    }

    @Override
    public void undo() {
        ArrayList<Minion> aliveBuffedMinions = findAliveBuffedMinions();

        for(Minion minion: aliveBuffedMinions) {
            minion.removeAttack(attackBonus);
        }
    }

    private ArrayList<Minion> findAliveBuffedMinions() {
        ArrayList<Minion> aliveMinions = new ArrayList<>();

        for(Minion minion : buffedMinions) {
            if(!minion.isDead())
                aliveMinions.add(minion);
        }

        return aliveMinions;
    }
}
