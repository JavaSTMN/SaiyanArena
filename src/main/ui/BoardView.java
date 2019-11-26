package main.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BoardView extends JPanel {

    public BoardView() {
        initComponents();
    }

    public void initComponents() {
        Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
        this.setBorder(border);
        this.setBackground(new Color(47, 59, 79));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
    }
}
