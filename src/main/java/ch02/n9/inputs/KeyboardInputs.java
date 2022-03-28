package ch02.n9.inputs;
import ch02.n9.Game;
import ch02.n9.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public record KeyboardInputs(GamePanel gamePanel, Game game) implements KeyListener {
    private static final int increment = 5;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                System.out.println("It is W");
                game.DriveByNumberOfMilesY(-increment);
            }
            case KeyEvent.VK_A -> {
                game.DriveByNumberOfMilesX(-increment);
                System.out.println("It is A");
            }
            case KeyEvent.VK_S -> {
                game.DriveByNumberOfMilesY(increment);
                System.out.println("It is S");
            }
            case KeyEvent.VK_D -> {
                game.DriveByNumberOfMilesX(increment);
                System.out.println("It is D");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
