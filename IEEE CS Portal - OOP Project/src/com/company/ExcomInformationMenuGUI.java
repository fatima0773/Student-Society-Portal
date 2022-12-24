package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExcomInformationMenuGUI extends JFrame{
    private JButton updateBtn;
    private JButton showBtn;
    private JButton backButton;

    public  ExcomInformationMenuGUI(){
        setTitle("Excom Section");
        setSize(400,200);
        setLayout(new GridLayout(1,1,10,10));

        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JLabel welcomeLabel = new JLabel("E X C O M   I N F O R M A T I O N", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        mainPanel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        updateBtn = new JButton("Update Record");
        buttonPanel.add(updateBtn);
        // event handling
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InformationGUI excomInformationGUI = new InformationGUI("update","excom");
                excomInformationGUI.setVisible(true); //frame will only be visible with set "true"
                excomInformationGUI.setLocationRelativeTo(null); //for opening in center
            }
        });

        showBtn = new JButton("View Information");
        buttonPanel.add(showBtn);
        showBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InformationGUI excomInformationGUI = new InformationGUI("show", "excom");
                excomInformationGUI.setVisible(true); //frame will only be visible with set "true"
                excomInformationGUI.setLocationRelativeTo(null); //for opening in center

            }

        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel);
        add(mainPanel);

    }
}
