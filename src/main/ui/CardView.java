package main.ui;

import main.card.Card;
import main.card.Minion;
import main.events.ICardListener;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        Minion minion = new Minion("test",2,1,1);
        CardView view = new CardView(minion);
        JFrame gameInstance = new JFrame();
        gameInstance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameInstance.add(view);
        gameInstance.pack();
        gameInstance.setSize(1500,1500);
        gameInstance.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage backGround = null;
        try {
            backGround = ImageIO.read(new File("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\frame-minion-neutral.png"));
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        g.drawImage(backGround,0,0,null);
    }

    public void initComponent() {

        ImageIcon manaIcon = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\cost-mana.png");
        ImageIcon attackIcon = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\attack-minion.png");
        ImageIcon healthIcon = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\health.png");
        ImageIcon frameMinionIcon = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\frame-minion-neutral.png");
        ImageIcon cardNameIcon  = new ImageIcon("C:\\Users\\Peter Duvauchelle\\Desktop\\SeiyantArena\\src\\ressources\\name-banner-minion.png");


        GridLayout layout = new GridLayout(4,4);
        this.setLayout(layout);

        this.JcardNameLabel = new JLabel();
        this.JmanaLabel = new JLabel();

        this.JmanaLabel = new JLabel();
        this.JmanaLabel.setIcon(manaIcon);
        this.JmanaLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JmanaLabel.setVerticalTextPosition(JLabel.CENTER);
        this.JmanaLabel.setLocation(0,0);

        this.JattackLabel = new JLabel();
        this.JattackLabel.setIcon(attackIcon);
        this.JattackLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JattackLabel.setVerticalTextPosition(JLabel.CENTER);
        this.JattackLabel.setLocation(0,0);

        this.JhealthLabel = new JLabel();
        this.JhealthLabel.setIcon(healthIcon);
        this.JhealthLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JhealthLabel.setVerticalTextPosition(JLabel.CENTER);
        this.JhealthLabel.setLocation(0,0);

        this.JcardNameLabel = new JLabel();
        this.JcardNameLabel.setIcon(cardNameIcon);
        this.JcardNameLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.JcardNameLabel.setVerticalTextPosition(JLabel.CENTER);
        this.JcardNameLabel.setLocation(0,0);


        this.add(this.JmanaLabel);
        this.add(this.JattackLabel);
        this.add(this.JhealthLabel);
        this.add(this.JcardNameLabel);
    }

    @Override
    public void refresh() {
        setCard(card);
    }
}
