import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    int column;
    Color color;
    Color emptyColor; // color when there is no stone inside
    final int length = Window.WIDTH / 7; // Button is a square
    final int radius = 4 * length / 5;

    Cell(int column, Color color) {
        super();
        this.column = column;
        this.color = color;
        this.emptyColor = color;
        this.setActionCommand(column + "");
        this.setPreferredSize(new Dimension(this.length, this.length));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        // draws an empty circle in the middle of a blue frame
        g.fillRect(0, 0, length, length);
        g.setColor(color);
        g.fillOval((length - radius) / 2, (length - radius) / 2, radius, radius);
    }

    Color getColor() {
        return this.color;
    }

    void setColor(Color color) {
        this.color = color;
        repaint();
    }

    boolean isEmpty() {
        return this.color == this.emptyColor;
    }
}
