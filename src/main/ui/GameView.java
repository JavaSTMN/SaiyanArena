package main.ui;

import main.Game;
import main.GameInteraction;
import main.card.Card;
import main.card.Minion;
import main.model.CardContainer;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private PlayerView playerOne;
    private PlayerView playerTwo;

    private BoardView boardPlayerOne;
    private BoardView boardPlayerTwo;

    public GameView(Game game) {
        boardPlayerOne = new BoardView();
        boardPlayerTwo = new BoardView();

        GameInteraction interaction = new GameInteraction(game);
        playerOne = new PlayerView(interaction);
        playerTwo = new PlayerView(interaction);

        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());

        // create center Panel
        JPanel centerPanel = new JPanel();
        BoxLayout layout = new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS);
        centerPanel.setLayout(layout);

        centerPanel.add(boardPlayerOne);
        centerPanel.add(boardPlayerTwo);

        // add components to JPanel
        this.add(playerTwo, BorderLayout.NORTH);
        this.add(playerOne, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }
}
