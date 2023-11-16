package com.example.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

//@SpringBootApplication
public class TicTacToeApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TicTacToeApplication.class, args);
//        TicTacToe t = new TicTacToe();
        SwingUtilities.invokeLater(() -> new TicTacToe());

    }

}
