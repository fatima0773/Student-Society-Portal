package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeGUI extends JFrame {
    private JButton volunteerButton;
    private JButton excomButton;

    public WelcomeGUI(){
        setTitle("IEEE CS Portal");
        setSize(350,200);
        setLayout(new GridLayout(1,1,10,10));

        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JLabel welcomeLabel = new JLabel("W E L C O M E!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        mainPanel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        volunteerButton = new JButton("Volunteer Section");
        volunteerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI volunteerLogin = new LoginGUI("volunteer");
                volunteerLogin.setVisible(true);                //frame will only be visible with set "true"
                volunteerLogin.setLocationRelativeTo(null);     //for opening in center
            }
        });
        excomButton = new JButton("Excom Section");
        excomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI excomLogin = new LoginGUI("excom");
                excomLogin.setVisible(true);                //frame will only be visible with set "true"
                excomLogin.setLocationRelativeTo(null);     //for opening in center
            }
        });
        buttonPanel.add(volunteerButton);
        buttonPanel.add(excomButton);
        mainPanel.add(buttonPanel);
        add(mainPanel);
    }
}
