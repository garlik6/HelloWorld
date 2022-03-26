package ch02.n9;
import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    private final int height;
    private final int width;
    private int x;
    private int y;
    private final JLabel topLabel;

    @Override
    public int getHeight() {
        return height;
    }

    public void setTopLabelText(String text) {
        topLabel.setText("Fuel = " + text);
    }

    @Override
    public int getWidth() {
        return width;
    }

    public GamePanel(int width , int height, int x, int y) {
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
        topLabel = new JLabel("Top");
        topLabel.setVerticalAlignment(JLabel.TOP);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setForeground(Color.GREEN);
        this.add(topLabel);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void paintComponent(Graphics g){
        g.fillRoundRect(x, y,10,10,1,1);
    }
}
