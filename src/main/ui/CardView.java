package main.ui;

import main.card.Card;
import main.card.Minion;
import main.events.ICardListener;

import javax.swing.*;
import java.awt.*;


public class CardView extends JPanel implements ICardView, ICardListener {
    private Card card;
    private JLabel JillustrationMinionLabel;
    private JLabel JmanaLabel;
    private JLabel JattackLabel;
    private JLabel JhealthLabel;
    private JLabel JcardNameLabel;
    private JLabel JcardDescLabel;


    public CardView(Card card) {
        initComponent();
        setCard(card);
    }

    public void setCard(Card card) {
        this.card = card;
        card.setListener(this);
        String cardName = card.getName();
        String description = card.getDescription();
        int manaCost = card.getManaCost();


        this.JcardNameLabel.setText(cardName);
        this.JmanaLabel.setText(Integer.toString(manaCost));
        this.JcardDescLabel.setText(description);

        if(card instanceof Minion) {
            Minion minion = (Minion) card;
            int currentHealth = minion.getLifePoints();
            int currentAttack = minion.getAttack();

            this.JhealthLabel.setVisible(true);
            this.JhealthLabel.setText(Integer.toString(currentHealth));

            this.JattackLabel.setVisible(true);
            this.JattackLabel.setText(Integer.toString(currentAttack));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon backGround = new ImageIcon("src/ressources/frame-minion.png");
        g.drawImage(backGround.getImage(),0,0,null);
    }



    public void initComponent() {
        ImageIcon manaIcon = new ImageIcon("src/ressources/mana-cost.png");
        ImageIcon attackIcon = new ImageIcon("src/ressources/attack.png");
        ImageIcon healthIcon = new ImageIcon("src/ressources/health.png");
        ImageIcon cardNameIcon  = new ImageIcon("src/ressources/name-card.png");

        BorderLayout layout = new BorderLayout();

        this.setLayout(layout);

        this.JmanaLabel = new JLabel();
        this.JmanaLabel.setForeground(Color.WHITE);
        this.JmanaLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        this.JmanaLabel.setIcon(manaIcon);
        this.JmanaLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JmanaLabel.setVerticalTextPosition(JLabel.CENTER);

        this.JcardNameLabel = new JLabel();
        this.JcardNameLabel.setIcon(cardNameIcon);
        this.JcardNameLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JcardNameLabel.setVerticalTextPosition(JLabel.CENTER);

        JcardDescLabel = new JLabel();
        this.JcardDescLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
        this.JcardDescLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JcardDescLabel.setVerticalTextPosition(JLabel.CENTER);
        this.JcardDescLabel.setHorizontalAlignment(JLabel.CENTER);

        this.JattackLabel = new JLabel();
        this.JattackLabel.setForeground(Color.BLACK);
        this.JattackLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        this.JattackLabel.setIcon(attackIcon);
        this.JattackLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JattackLabel.setVerticalTextPosition(JLabel.CENTER);
        this.JattackLabel.setHorizontalAlignment(JLabel.LEFT);
        this.JattackLabel.setVisible(false);

        this.JhealthLabel = new JLabel();
        this.JhealthLabel.setForeground(Color.WHITE);
        this.JhealthLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        this.JhealthLabel.setIcon(healthIcon);
        this.JhealthLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JhealthLabel.setVerticalTextPosition(JLabel.CENTER);
        this.JhealthLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.JhealthLabel.setVisible(false);

        add(this.JmanaLabel, BorderLayout.NORTH);
        add(this.JcardNameLabel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridLayout(2,1));
        southPanel.setOpaque(false);
        southPanel.add(JcardDescLabel);

        JPanel underSouthPanel = new JPanel();
        underSouthPanel.setLayout(new GridLayout(1,2));
        underSouthPanel.setOpaque(false);
        underSouthPanel.add(JattackLabel);
        underSouthPanel.add(JhealthLabel);

        southPanel.add(underSouthPanel);

        add(southPanel, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(132,199));
    }

    @Override
    public void refresh() {
        setCard(card);
    }

    @Override
    public Card getCard() {
        return card;
    }
}
