import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    /*
    *  TODO: Add visual indicator who's turn it is
    * */

    static int WIDTH = 700;
    static int HEIGHT = 600;

    Field field = new Field();

    Window() {
        this.setTitle("Connect Four");
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(field, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
