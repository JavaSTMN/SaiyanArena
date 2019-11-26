package main;

import main.card.CardDatabase;
import main.card.Minion;
import main.ui.CardView;
import main.ui.GameView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SeiyantArena {
    public static void main(String[] args) {
        //CardView view = new CardView(new Minion("test", 1, 1, 1));

        JFrame gameInstance = new JFrame();
        gameInstance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameInstance.add(new GameView());
        gameInstance.setSize(800,600);
        gameInstance.setVisible(true);
    }
}
