package main.ui;

import main.GameInteraction;
import main.card.Card;
import main.card.Minion;
import main.events.IBoardListener;
import main.model.CardContainer;
import main.model.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardView extends JPanel implements IBoardListener {
    private GameInteraction gameInteraction;
    private Player player;

    public BoardView(GameInteraction gameInteraction, Player player) {
        this.gameInteraction = gameInteraction;
        this.player = player;
        player.setBoardListener(this);

        initComponents();
    }

    public void initComponents() {
        Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
        this.setBorder(border);
        this.setBackground(new Color(47, 59, 79));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    @Override
    public void refresh() {
        setMinions(gameInteraction, player.getBoard().getMinions());
    }

    void setMinions(GameInteraction gameInteraction, CardContainer<Minion> minions) {
        removeAll();
        for(Minion minion : minions) {
            CardView view = new CardView(minion);

            //view.addMouseListener()

            this.add(view);
        }

        this.revalidate();
        this.repaint();
    }
}
