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


    public CardView(Card card) {
        initComponent();
        setCard(card);
    }

    public void setCard(Card card) {
        this.card = card;
        card.setListener(this);
        String cardName = card.getName();
        int manaCost = card.getManaCost();


        this.JcardNameLabel.setText(cardName);
        this.JmanaLabel.setText(Integer.toString(manaCost));

        if(card instanceof Minion) {
            Minion minion = (Minion) card;
            int currentHealth = minion.getLifePoints();
            int currentAttack = minion.getAttack();
            this.JhealthLabel.setText(Integer.toString(currentHealth));
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

        GridLayout layout = new GridLayout(4,1);

        this.setLayout(layout);


        this.JmanaLabel = new JLabel();
        this.JmanaLabel.setIcon(manaIcon);
        this.JmanaLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JmanaLabel.setVerticalTextPosition(JLabel.CENTER);

        this.JcardNameLabel = new JLabel();
        this.JcardNameLabel.setIcon(cardNameIcon);
        this.JcardNameLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JcardNameLabel.setVerticalTextPosition(JLabel.CENTER);

        this.JattackLabel = new JLabel();
        this.JattackLabel.setIcon(attackIcon);
        this.JattackLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JattackLabel.setVerticalTextPosition(JLabel.CENTER);
        this.JattackLabel.setHorizontalAlignment(JLabel.LEFT);

        this.JhealthLabel = new JLabel();
        this.JhealthLabel.setIcon(healthIcon);
        this.JhealthLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JhealthLabel.setVerticalTextPosition(JLabel.CENTER);
        this.JhealthLabel.setHorizontalAlignment(JLabel.RIGHT);



        add(this.JmanaLabel);
        add(this.JcardNameLabel);
        add(this.JattackLabel);
        add(this.JhealthLabel);
        this.setSize(200,200);
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
