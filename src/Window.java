import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    /*
    *  TODO: Add ability to restart the game
    *  TODO: Add visual indicator who's turn it is
    * */

    static int WIDTH = 1000;
    static int HEIGHT = 1000 * 9 / 16;

    Field field = new Field();
    JPanel south = new JPanel();
    JPanel west = new JPanel();
    JPanel east = new JPanel();

    Window() {
        this.windowSetup();
        this.menuSetup();
    }

    private void windowSetup() {
        this.setTitle("Connect Four");
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }

    private void menuSetup() {
        field.setPreferredSize(new Dimension(WIDTH / 2, 4 * HEIGHT / 5));
        south.setPreferredSize(new Dimension(WIDTH, HEIGHT / 5));
        east.setPreferredSize(new Dimension(WIDTH / 4, 4 * HEIGHT / 5));
        west.setPreferredSize(new Dimension(WIDTH / 4, 4 * HEIGHT / 5));

        south.setBackground(new Color(142, 89, 37));
        west.setBackground(Color.orange);
        east.setBackground(Color.orange);

        this.add(field, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        new Window();
    }
}
