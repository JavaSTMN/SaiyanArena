package main.card;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEConfig;
import main.Game;
import main.effects.*;

public class CardDatabase {
    private Game game;

    public CardDatabase(Game game) {
        this.game = game;
    }

    public Card createRaidLeader() {
        Minion minion = new Minion("Raid Leader", 3, 2, 2);
        minion.setDescription("<html>Your other minions <br/>have +1 Attack</html>");
        AttackBuff buff = new AttackBuff(game, 1);

        minion.addEffect(buff);
        minion.addUndoableEffect(buff);

        minion.setDieAction(buff::undo);

        return minion;
    }

    public Card createStonuskBoar() {
        Minion minion = new Minion("Stonusk Boar", 1, 1 ,1);
        minion.setDescription("<html><b>Charge</b></html>");
        Charge charge = new Charge(minion);

        minion.addEffect(charge);

        return minion;
    }

    public Card createArcaneIntellect() {
        Card card = new Card("Arcane Intellect", 3);
        card.setDescription("Draw 2 cards.");
        DrawEffect draw = new DrawEffect(game, 2);

        card.addEffect(draw);

        return card;
    }

    public Card createGrizzly() {
        Minion minion = new Minion("Grizzly Ferpoil", 3, 3, 3);
        minion.setDescription("<html><b>Taunt</b></html>");

        return minion;
    }

    public Card createArcaneMissiles() {
        Card card = new Card("Arcane Missiles", 1);
        card.setDescription("<html>Deal 3 damage <br/> randomly split among <br/> all enemies.</html>");

        RandomTargetDamage damages = new RandomTargetDamage(game, 1, 3);

        card.addEffect(damages);

        return card;
    }

    public Card createRaptor() {
        Minion minion = new Minion("Bloodenfen Raptor", 2, 3 ,2);

        return minion;
    }
}
