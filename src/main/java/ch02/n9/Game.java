package ch02.n9;

public class Game {
    public Game(){
        GamePanel gamePanel = new GamePanel(400, 400);
        GameWindow gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
    }
}
