package ui;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card {

    public static void main(String[] args) throws IOException {
        JFrame frame = buildFrame();

        final BufferedImage image = ImageIO.read(new File("C:\\Users\\Peter Duvauchelle\\IdeaProjects\\JFrame\\src\\ui\\imgSrc\\frame-minion-neutral.png"));
        final BufferedImage imageManaCost = ImageIO.read(new File("C:\\Users\\Peter Duvauchelle\\IdeaProjects\\JFrame\\src\\ui\\imgSrc\\cost-mana.png"));
        final BufferedImage imageAttackPoint = ImageIO.read(new File("C:\\Users\\Peter Duvauchelle\\IdeaProjects\\JFrame\\src\\ui\\imgSrc\\attack-minion.png"));
        final BufferedImage imageHealthPoint = ImageIO.read(new File("C:\\Users\\Peter Duvauchelle\\IdeaProjects\\JFrame\\src\\ui\\imgSrc\\health.png"));

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 100, 0, null);
                g.drawImage(imageManaCost,90,30,null);
                g.drawImage(imageAttackPoint,70,630,null);
                g.drawImage(imageHealthPoint,530,640,null);
            }
        };

        frame.add(pane);
    }


    private static JFrame buildFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }


}