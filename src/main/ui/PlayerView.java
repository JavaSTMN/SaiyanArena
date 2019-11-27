package main.ui;

import main.AttackTool;
import main.GameInteraction;
import main.PlaySide;
import main.card.Card;
import main.events.IPlayerListener;
import main.model.CardContainer;
import main.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerView extends JPanel implements IPlayerListener {
    private GameInteraction gameInteraction;
    private AttackTool tool;
    private Player player;

    private JPanel cardComponents;
    private JLabel deckLabel;
    private JLabel heroHP;
    private JLabel manaLabel;
    private JButton endTurnButton;

    public PlayerView(GameInteraction gameInteraction, AttackTool tool, Player player) {
        this.gameInteraction = gameInteraction;
        this.tool = tool;
        this.player = player;
        player.setHandListener(this);

        initComponents();
    }

    public void initComponents() {
        Color background = new Color(100, 100, 100);

        this.setLayout(new BorderLayout());
        this.setBackground(background);
        this.setPreferredSize(new Dimension(800, 220));

        manaLabel = new JLabel("Mana : 0/10");
        manaLabel.setForeground(Color.BLUE);
        manaLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

        heroHP = new JLabel("Vie : 30/30");
        heroHP.setForeground(Color.RED);
        heroHP.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        heroHP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setAttackBehavior();
            }
        });


        deckLabel = new JLabel("Deck : 20 left");
        deckLabel.setForeground(Color.WHITE);
        deckLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        deckLabel.setHorizontalTextPosition(JLabel.CENTER);
        deckLabel.setVerticalTextPosition(JLabel.CENTER);
        deckLabel.setIcon(new ImageIcon("src/ressources/cards/Card_Back.png"));

        endTurnButton = new JButton("EndTurn");
        endTurnButton.addActionListener(actionEvent -> gameInteraction.endTurn(player));
        endTurnButton.setVisible(false);

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

        JPanel eastPanel = new JPanel(new GridLayout(2,1));
        eastPanel.add(endTurnButton);
        eastPanel.add(deckLabel);
        eastPanel.setBackground(background);

        this.add(eastPanel, BorderLayout.EAST);
    }

    @Override
    public void onDrawCard() {
        CardContainer<Card> cards = player.getHand();
        CardContainer<Card> deck = player.getDeck();

        setCards(gameInteraction, cards);

        deckLabel.setText(Integer.toString(deck.size()));
    }

    @Override
    public void onPlayCard() {
        CardContainer<Card> cards = player.getHand();

        setCards(gameInteraction, cards);
    }

    @Override
    public void refresh() {
        int mana = player.getManaReserve().getActualAvailable();
        int health = player.getHero().getLifePoints();

        manaLabel.setText("Mana : " + mana);
        heroHP.setText("(" + health + " HP)");

        endTurnButton.setVisible(false);

        if(player.getSide() == PlaySide.ACTIVE_PLAYER)
            endTurnButton.setVisible(true);
    }

    public void setCards(GameInteraction gameInteraction, CardContainer<Card> cards) {
        cardComponents.removeAll();
        for(Card card : cards) {
            CardView view = new CardView(card);

            view.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    gameInteraction.playCard(player, view.getCard());
                }
            });

            cardComponents.add(view);
        }

        cardComponents.revalidate();
        cardComponents.repaint();
    }

    public void setAttackBehavior() {
        tool.setTarget(player.getHero());
        tool.executeAttack();
    }

}
