package ch02.n9.inputs;

import ch02.n9.Car;
import ch02.n9.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public record KeyboardInputs(GamePanel gamePanel) implements KeyListener {
    private static final int increment = 5;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Car car = gamePanel.getCar();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                System.out.println("It is W");
                car.DriveByNumberOfMilesY(-increment);
                gamePanel.paintComponent(gamePanel.getGraphics());
            }
            case KeyEvent.VK_A -> {
                car.DriveByNumberOfMilesX(-increment);
                System.out.println("It is A");
                gamePanel.paintComponent(gamePanel.getGraphics());
            }
            case KeyEvent.VK_S -> {
                car.DriveByNumberOfMilesY(increment);
                System.out.println("It is S");
                gamePanel.paintComponent(gamePanel.getGraphics());
            }
            case KeyEvent.VK_D -> {
                car.DriveByNumberOfMilesX(increment);
                System.out.println("It is D");
                gamePanel.paintComponent(gamePanel.getGraphics());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
