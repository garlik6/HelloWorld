package ch02.n9;

import javax.swing.*;

public class GameWindow {

    public GameWindow(GamePanel gamePanel){
        JFrame jFrame = new JFrame();
        jFrame.setSize(gamePanel.getHeight(),gamePanel.getWidth());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.requestFocusInWindow();
    }
}
