package main;

import main.GamePanel;
import debugSystem.DebugLayout;
import javax.swing.*;

public class Main {
    public static void main (String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("RPG Game");//name of screen
        frame.setSize(420,420);//size of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// to X out
        frame.setResizable(false);//resize

        // frame
       GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);

        frame.pack();
        frame.setVisible(true); // visibly
        gamePanel.startGameThread();

    }



}