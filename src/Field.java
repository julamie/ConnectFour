import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Field extends JPanel implements ActionListener {

    int columns = 7;
    int rows = 6;
    Cell[][] cells = new Cell[columns][rows];
    Color backgroundColor = Color.LIGHT_GRAY;
    Color currColor = Color.RED;

    Field() {
        super();
        createField();
    }

    // creates a 7x6 field of JButtons acting as a cell
    private void createField() {
        this.setBackground(backgroundColor);
        this.setLayout(new GridLayout(6, 7));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // add cells to the field
                Cell cell = new Cell(j, backgroundColor);

                this.add(cell);
                cell.addActionListener(this);
                cells[j][rows - 1 - i] = cell;
            }
        }
    }

    void setCellColor(int column, int height) {
        cells[column][height].setColor(currColor);
    }

    boolean columnSpaceAvailable(int column) {
        return cells[column][rows - 1].isEmpty();
    }

    // returns the height of the lowest free cell of a column
    int getLowestCellHeight(int column) {
        int i = 0;
        while (!cells[column][i].isEmpty()) {
            i++;
        }

        return i;
    }

    void nextMove() {
        currColor = currColor == Color.RED ? Color.YELLOW : Color.RED;
    }

    /**
     * Checks the field for a row of four
     * @param column of the most recently added stone
     * @param height of the most recently added stone
     * @return if a row of four has been found
     */
    boolean checkRowOfFour(int column, int height) {
        Color color = cells[column][height].getColor();

        // count all stones left of the most recent stone
        int i = column - 1;
        int horMatches = 1;
        while (i >= 0 && cells[i][height].getColor() == color) {
            horMatches++;
            i--;
        }

        // count all from the right
        i = column + 1;
        while (i < columns && cells[i][height].getColor() == color) {
            horMatches++;
            i++;
        }

        // count all stones below most recent stones (above are none)
        i = height - 1;
        int downMatches = 1;
        while (i >= 0 && cells[column][i].getColor() == color) {
            downMatches++;
            i--;
        }

        // count stones upwards diagonally
        i = 1;
        int upDiaMatches = 1;
        while (column - i >= 0 && height - i >= 0 && cells[column - i][height - i].getColor() == color) {
            upDiaMatches++;
            i++;
        }

        i = 1;
        while (column + i < columns && height + i < rows && cells[column + i][height + i].getColor() == color) {
            upDiaMatches++;
            i++;
        }

        // count stones downwards diagonally
        i = 1;
        int downDiaMatches = 1;
        while (column - i >= 0 && height + i < rows && cells[column - i][height + i].getColor() == color) {
            downDiaMatches++;
            i++;
        }

        i = 1;
        while (column + i < columns && height - i >= 0 && cells[column + i][height - i].getColor() == color) {
            downDiaMatches++;
            i++;
        }

        return horMatches >= 4 || downMatches >= 4 || upDiaMatches >= 4 || downDiaMatches >= 4;
    }

    // open up a prompt to ask if the player wants to restart after finishing the game
    void determineWinner() {
        String winner = currColor == Color.RED ? "red" : "yellow";
        String message = "Congratulations " + winner + "! You won!\n\n Would you like to restart?";
        int response = JOptionPane.showConfirmDialog(this, message, "End of game", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) restartGame();
        else {
            // remove all action listeners of the game so that no new stones can be added
            for (Cell[] column : cells) {
                for (Cell cell : column) {
                    cell.removeActionListener(this);
                }
            }
        }
    }

    void restartGame() {
        currColor = Color.RED;

        for (Cell[] column: cells) {
            for (Cell cell: column) {
                cell.setColor(backgroundColor);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cellID = e.getActionCommand();

        int column = Integer.parseInt(cellID);
        if (columnSpaceAvailable(column)) {
            int height = getLowestCellHeight(column);
            setCellColor(column, height);
            if (checkRowOfFour(column, height)) {
                determineWinner();
            }
            else nextMove();
        }
    }
}
