package main;

import main.card.CardDatabase;
import main.card.Hero;
import main.card.HeroClass;
import main.model.Board;
import main.model.CardContainer;
import main.model.Player;
import main.ui.GameView;

import javax.swing.*;

public class SeiyantArena {
    public static void main(String[] args) {
        //CardView view = new CardView(new Minion("test", 1, 1, 1));

        SwingUtilities.invokeLater(() -> {
            Player playerOne = new Player(new Hero(HeroClass.MAGE), new Board());
            Player playerTwo = new Player(new Hero(HeroClass.MAGE), new Board());

            Game game = new Game(playerOne, playerTwo);
            GameView gameView = new GameView(game);

            game.beginPlay();

            JFrame mainFrame = new JFrame();
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.add(gameView);
            mainFrame.setSize(1024, 748);
            mainFrame.setVisible(true);
        });
    }
}
