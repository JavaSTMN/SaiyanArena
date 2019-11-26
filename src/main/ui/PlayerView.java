package main.ui;

import main.GameInteraction;
import main.card.Card;
import main.model.CardContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerView extends JPanel {
    private GameInteraction gameInteraction;

    private JPanel cardComponents;
    private JLabel deckLabel;
    private JLabel heroHP;
    private JLabel manaLabel;

    public PlayerView(GameInteraction gameInteraction) {
        this.gameInteraction = gameInteraction;

        initComponents();
    }

    public void initComponents() {
        Color background = new Color(100, 100, 100);

        this.setLayout(new BorderLayout());
        this.setBackground(background);
        this.setPreferredSize(new Dimension(800, 220));

        manaLabel = new JLabel("Mana : 0/10");
        manaLabel.setForeground(Color.BLUE);

        heroHP = new JLabel("Vie : 30/30");
        heroHP.setForeground(Color.RED);

        deckLabel = new JLabel("Deck : 20 left");
        deckLabel.setForeground(Color.WHITE);

        cardComponents = new JPanel();
        cardComponents.setLayout(new FlowLayout(FlowLayout.CENTER));
        cardComponents.setBackground(background);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.setBackground(background);

        panel.add(manaLabel);
        panel.add(heroHP);

        this.add(panel, BorderLayout.NORTH);
        this.add(cardComponents, BorderLayout.CENTER);
        this.add(deckLabel, BorderLayout.EAST);
    }

    public void setCards(GameInteraction gameInteraction, CardContainer<Card> cards) {
        cardComponents.removeAll();
        for(Card card : cards) {
            CardView view = new CardView(card);

            view.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    gameInteraction.playCard(view.getCard());
                }
            });

            cardComponents.add(view);
        }

        cardComponents.revalidate();
        cardComponents.repaint();
    }
 }
