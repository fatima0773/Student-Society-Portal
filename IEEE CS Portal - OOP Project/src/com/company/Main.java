package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        WelcomeGUI welcomeWindow = new WelcomeGUI();
        welcomeWindow.setVisible(true);                //frame will only be visible with set "true"
        welcomeWindow.setLocationRelativeTo(null);     //for opening in center
        welcomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
