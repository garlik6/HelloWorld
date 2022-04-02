package ch02.n9;

import ch02.n9.inputs.KeyboardInputs;

import javax.swing.*;

public class Game {
    private final GamePanel gamePanel;
    private final GameWindow gameWindow;
    private final int width = 400;
    private final int height = 400;
    private final Car car;

    private Game(GamePanel gamePanel, GameWindow gameWindow, Car car) {
        this.gamePanel = gamePanel;
        this.gameWindow = gameWindow;

        this.car = car;

    }

    public static Game createNewGame() {
        Car car = new Car(200, 0.1, 200, 400, 400);
        GamePanel gamePanel = new GamePanel(400, 400, (int) car.getX(), (int) car.getY());
        GameWindow gameWindow = new GameWindow();
        return new Game(gamePanel, gameWindow, car);
    }

    public Car getCar() {
        return car;
    }

    public void startGame() {
        this.gameWindow.jFrame.setSize(gamePanel.getHeight(), gamePanel.getWidth());
        this.gameWindow.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameWindow.jFrame.add(gamePanel);
        this.gameWindow.jFrame.setLocationRelativeTo(null);
        this.gameWindow.jFrame.setVisible(true);
        this.gameWindow.jFrame.requestFocusInWindow();
        this.gamePanel.addKeyListener(new KeyboardInputs(gamePanel, this));
        this.gamePanel.requestFocus();
    }

    public void DriveByNumberOfMilesX(int increment) {
        car.DriveByNumberOfMilesX(increment);
        gamePanel.setX((int) car.getX());
        gamePanel.paintComponent(gamePanel.getGraphics());
//        gamePanel.setTopLabelText(Double.toString(car.getGasInTank()));
    }

    public void DriveByNumberOfMilesY(int increment) {
        car.DriveByNumberOfMilesY(increment);
        gamePanel.setY((int) car.getY());
        gamePanel.paintComponent(gamePanel.getGraphics());
    }
}
