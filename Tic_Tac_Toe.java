import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tic_Tac_Toe extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;

    public Tic_Tac_Toe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }
    }

    private void makeMove(int row, int col) {
        if (buttons[row][col].getText().isEmpty()) {
            buttons[row][col].setText(String.valueOf(currentPlayer));
            buttons[row][col].setEnabled(false);
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move. Try again.");
        }
    }

    private boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(player)) &&
                    buttons[i][1].getText().equals(String.valueOf(player)) &&
                    buttons[i][2].getText().equals(String.valueOf(player))) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(String.valueOf(player)) &&
                    buttons[1][i].getText().equals(String.valueOf(player)) &&
                    buttons[2][i].getText().equals(String.valueOf(player))) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(player)) &&
                buttons[1][1].getText().equals(String.valueOf(player)) &&
                buttons[2][2].getText().equals(String.valueOf(player))) {
            return true;
        }

        if (buttons[0][2].getText().equals(String.valueOf(player)) &&
                buttons[1][1].getText().equals(String.valueOf(player)) &&
                buttons[2][0].getText().equals(String.valueOf(player))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showGameOverDialog(char winner) {
        String message;
        if (winner == 'X' || winner == 'O') {
            message = "Player " + winner + " wins!";
        } else {
            message = "It's a draw!";
        }
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        currentPlayer = 'X';
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (clickedButton == buttons[i][j]) {
                        makeMove(i, j);
                        if (checkWin(currentPlayer)) {
                            showGameOverDialog(currentPlayer);
                        } else if (isBoardFull()) {
                            showGameOverDialog('-');
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Tic_Tac_Toe();
        });
    }
}

