package main.ui;

import main.AttackTool;
import main.Game;
import main.GameInteraction;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private GameInteraction gameInteraction;

    private PlayerView playerOne;
    private PlayerView playerTwo;

    private BoardView boardPlayerOne;
    private BoardView boardPlayerTwo;

    public GameView(Game game) {
        gameInteraction = new GameInteraction(game);
        AttackTool tool = new AttackTool(gameInteraction);

        boardPlayerOne = new BoardView(gameInteraction, tool, game.getPlayerOne());
        boardPlayerTwo = new BoardView(gameInteraction, tool, game.getPlayerTwo());

        playerOne = new PlayerView(gameInteraction, tool, game.getPlayerOne());
        playerTwo = new PlayerView(gameInteraction, tool, game.getPlayerTwo());

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // create center Panel
        JPanel centerPanel = new JPanel();
        BoxLayout layout = new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS);
        centerPanel.setLayout(layout);

        centerPanel.add(boardPlayerTwo);
        centerPanel.add(boardPlayerOne);

        // add components to JPanel
        this.add(playerTwo, BorderLayout.NORTH);
        this.add(playerOne, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

}
