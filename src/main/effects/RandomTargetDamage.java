package main.effects;

import main.Game;
import main.PlaySide;
import main.card.Hero;
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

        ArrayList<ITarget> targets = chooseTargets(opponentPlayer);

        for(ITarget target : targets) {
            target.takeDamage(1);
        }
    }

    private ArrayList<ITarget> chooseTargets(Player opponentPlayer) {
        Board opponentBoard = opponentPlayer.getBoard();
        Hero opponentHero = opponentPlayer.getHero();

        ArrayList<ITarget> candidates = new ArrayList<>();
        CardContainer<Minion> minions = opponentBoard.getMinions().Clone();

        minions.forEach(x -> candidates.add(x));
        candidates.add(opponentHero);

        ArrayList<ITarget> targets = new ArrayList<>();
        while(minions.size() > 0|| targets.size() <= targetNumber) {
            ITarget target = chooseTarget(candidates);
            targets.add(target);
        }

        return targets;
    }

    private ITarget chooseTarget(ArrayList<ITarget> candidates) {
        int index = random.nextInt(3);

        ITarget target = candidates.get(index);
        candidates.remove(target);

        return target;
    }
}
