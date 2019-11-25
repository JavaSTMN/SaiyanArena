package main.ui;

import main.card.Card;
import main.card.Minion;
import main.events.ICardListener;

import javax.swing.*;
import java.awt.*;


public class CardView extends JPanel implements ICardListener {

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

    public static void main(String[] args) {
        JFrame gameInstance = new JFrame();
        gameInstance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Minion minion = new Minion("test",2,1,1);
        CardView view = new CardView(minion);
        gameInstance.add(view);
        gameInstance.setSize(143,229);
        gameInstance.setVisible(true);
       // gameInstance.pack();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon backGround = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\frame-minion.png");
        g.drawImage(backGround.getImage(),0,0,null);
    }



    public void initComponent() {

        ImageIcon manaIcon = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\mana-cost.png");
        ImageIcon attackIcon = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\attack.png");
        ImageIcon healthIcon = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\health.png");
        ImageIcon cardNameIcon  = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\name-card.png");




        GridLayout layout = new GridLayout(3,2);

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
}
