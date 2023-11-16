package com.example.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    public static int BOARD_SIZE = 3;

    public static enum GameStatus {
        Incomplete, XWins, ZWins, Tie;
    }

    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];

    boolean crossTurn = true;

    public TicTacToe() {
        super.setTitle("Tic-Tac-Toe");
        super.setSize(600, 700);
        BorderLayout layout = new BorderLayout();
        super.setLayout(layout);

        JPanel boardPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(BOARD_SIZE, BOARD_SIZE);
        boardPanel.setLayout(gridLayout);
        Font font = new Font("Comic Sans", 1, 150);
        
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = new JButton("");
                buttons[row][col] = button;
                button.setFont(font);
                button.addActionListener(this);
                boardPanel.add(button);
            }
        }

        super.add(boardPanel, BorderLayout.CENTER);
        
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Comic Sans", Font.PLAIN, 50));
        restartButton.setForeground(new Color(210, 105, 30));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        
        super.add(restartButton, BorderLayout.SOUTH);

        super.setResizable(false);
        super.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        makeMove(clickedButton);
        GameStatus status = this.getGameStatus();
        if (status == GameStatus.Incomplete) {
            return;
        }
        declareWinner(status);

        int choice = JOptionPane.showConfirmDialog(this, "Want to restart the Game ?? ");
        if (choice == JOptionPane.YES_OPTION) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    buttons[row][col].setText("");
                }
            }
            crossTurn = true;
        } else {
            super.dispose();
        }
    }


    private void makeMove(JButton clickedButton) {
        String btnText = clickedButton.getText();
        if (btnText.length() > 0) {
            JOptionPane.showMessageDialog(this, "Invalid Move");
            return;
        } else {
            if (crossTurn) {
                clickedButton.setForeground(new Color(220, 20, 60));
                clickedButton.setText("X");
            } else {
                clickedButton.setForeground(new Color(46, 139, 87));
                clickedButton.setText("0");
            }
            crossTurn = !crossTurn;
        }
    }

    private GameStatus getGameStatus() {
        String text1 = "", text2 = "";
        int row = 0, col = 0;

        //inside rows
        row = 0;
        while (row < BOARD_SIZE) {
            col = 0;
            while (col < BOARD_SIZE - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row][col + 1].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                col++;
            }
            if (col == BOARD_SIZE - 1) {
                if (text1.equals("X")) {
                    return GameStatus.XWins;
                } else if (text1.equals("0")) {
                    return GameStatus.ZWins;
                }
            }
            row++;
        }

        //inside columns
        col = 0;
        while (col < BOARD_SIZE) {
            row = 0;
            while (row < BOARD_SIZE - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row + 1][col].getText();
                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                row++;
            }
            if (row == BOARD_SIZE - 1) {
                if (text1.equals("X")) {
                    return GameStatus.XWins;
                } else if (text1.equals("0")) {
                    return GameStatus.ZWins;
                }
            }
            col++;
        }

        //DIAGONAL 1
        row = 0;
        col = 0;
        while (row < BOARD_SIZE - 1) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row + 1][col + 1].getText();
            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }
            row++;
            col++;
        }
        if (row == BOARD_SIZE - 1) {
            if (text1.equals("X")) {
                return GameStatus.XWins;
            } else if (text1.equals("0")) {
                return GameStatus.ZWins;
            }
        }

        //DIAGONAL 2
        row = BOARD_SIZE - 1;
        col = 0;
        while (row > 0) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row - 1][col + 1].getText();
            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }
            row--;
            col++;
        }
        if (row == 0) {
            if (text1.equals("X")) {
                return GameStatus.XWins;
            } else if (text1.equals("0")) {
                return GameStatus.ZWins;
            }
        }
        String txt = "";
        for (row = 0; row < BOARD_SIZE; row++) {
            for (col = 0; col < BOARD_SIZE; col++) {
                txt = buttons[row][col].getText();
                if (txt.length() == 0) {
                    return GameStatus.Incomplete;
                }
            }
        }
        return GameStatus.Tie;
    }

    private void declareWinner(GameStatus gs) {
        if (gs == GameStatus.XWins) {
            JOptionPane.showMessageDialog(this, "X Wins");
        } else if (gs == GameStatus.ZWins) {
            JOptionPane.showMessageDialog(this, "Z Wins");
        } else {
            JOptionPane.showMessageDialog(this, "It's a Tie");
        }
    }

    private void restartGame() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col].setText("");
            }
        }
        crossTurn = true;
    }

}