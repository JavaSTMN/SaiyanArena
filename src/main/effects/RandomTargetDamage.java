package main.effects;

import main.Game;
import main.PlaySide;
import main.card.ITarget;
import main.card.Minion;
import main.model.Board;
import main.model.CardContainer;
import main.model.Player;

import java.util.ArrayList;
import java.util.Random;

public class RandomTargetDamage implements IEffect {
    private int targetNumber;
    private int amountDamage;
    private Random random;
    private Game game;

    public RandomTargetDamage(Game game, int amountDamage, int targetNumber) {
        this.game = game;
        this.amountDamage = amountDamage;
        this.targetNumber = targetNumber;

        random = new Random();
    }

    @Override
    public void execute() {
        Player opponentPlayer = game.getPlayer(PlaySide.WAITING_PLAYER);
        Board board = opponentPlayer.getBoard();

        ArrayList<ITarget> targets = chooseTargets(board);

        for(ITarget target : targets) {
            target.takeDamage(1);
        }
    }

    private ArrayList<ITarget> chooseTargets(Board board) {
        CardContainer<Minion> minions = board.getMinions().Clone();
        ArrayList<ITarget> targets = new ArrayList<>();

        while(minions.size() > 0|| targets.size() <= targetNumber) {
            ITarget target = chooseTarget(minions);
            targets.add(target);
        }

        return targets;
    }

    private ITarget chooseTarget(CardContainer<Minion> minions ) {
        int index = random.nextInt(3);

        Minion minion = minions.get(index);
        minions.remove(minion);

        return minion;
    }
}
