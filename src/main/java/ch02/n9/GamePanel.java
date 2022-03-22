package ch02.n9;

import ch02.n9.inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
    private final int height;
    private final int width;

    public Car getCar() {
        return car;
    }

    private final Car car;
    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public GamePanel(int width , int height) {
        this.width = width;
        this.height = height;
        car = new Car(1000, 0.2,1000, width, height);
        addKeyListener(new KeyboardInputs(this));
    }
    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        Car car = new Car(100, 0.5,100, width, height);
        g.fillRoundRect((int) Math.round(car.getX()), (int) Math.round(car.getY()),10,10,1,1);
    }
}
