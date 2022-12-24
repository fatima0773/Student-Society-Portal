package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoticesMenuGUI extends JFrame{
	private JButton updateNoticeBoard;
	private JButton showNoticeBoard;
    private JButton backButton;
	public NoticesMenuGUI(){
		setTitle("Notice Board");
        setSize(350,200);
        setLayout(new GridLayout(1,1,10,10));
        
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JLabel menuLabel = new JLabel("NOTICE BOARD MENU", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        mainPanel.add(menuLabel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        updateNoticeBoard = new JButton("Update");        
        updateNoticeBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	NoticesGUI noticeBoard = new NoticesGUI("updatenotice"); 
            	noticeBoard.setVisible(true); //frame will only be visible with set "true"
            	noticeBoard.setLocationRelativeTo(null); //for opening in center
            }
        });
        buttonPanel.add(updateNoticeBoard);

        showNoticeBoard = new JButton("Display");        
        showNoticeBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	NoticesGUI noticeBoard = new NoticesGUI("displaynotice"); 
            	noticeBoard.setVisible(true); //frame will only be visible with set "true"
            	noticeBoard.setLocationRelativeTo(null); //for opening in center
            }
        });

        buttonPanel.add(showNoticeBoard);

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
