package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExcomMenuGUI extends JFrame {

    private JButton volunteerInfoBtn;
    private JButton excomInfoBtn;
    private JButton semesterScheduleBtn;
    private JButton noticeBoardBtn;
    private JButton signOutBtn;

    public ExcomMenuGUI(Excom member){
        //arraylist read from excom file. used for login
        setTitle("Excom Section");
        setSize(470,270);
        setLayout(new GridLayout(1,1,10,10));

        JPanel mainPanel = new JPanel(new GridLayout(6,1));
        JLabel introLabel = new JLabel("Hi, "+member.getName()+"! What do you want to do next?", SwingConstants.CENTER);
        introLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        mainPanel.add(introLabel);

        JPanel volunteerInfoBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        volunteerInfoBtn = new JButton("Volunteer Information ");
        volunteerInfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VolunteerInformationMenuGUI volunteerInformationMenuGUI = new VolunteerInformationMenuGUI();
                volunteerInformationMenuGUI.setVisible(true);
                volunteerInformationMenuGUI.setLocationRelativeTo(null);
            }
        });
        volunteerInfoBtnPanel.add(volunteerInfoBtn);
        mainPanel.add(volunteerInfoBtnPanel);

        JPanel excomInfoBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        excomInfoBtn = new JButton("Excom Information");
        excomInfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExcomInformationMenuGUI excomInformationMenuGUI = new ExcomInformationMenuGUI();
                excomInformationMenuGUI.setVisible(true);
                excomInformationMenuGUI.setLocationRelativeTo(null);
            }
        });
        excomInfoBtnPanel.add(excomInfoBtn);
        mainPanel.add(excomInfoBtnPanel);

        JPanel semesterScheduleBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        semesterScheduleBtn = new JButton("Semester Schedule");
        semesterScheduleBtnPanel.add(semesterScheduleBtn);
        mainPanel.add(semesterScheduleBtnPanel);
        // Event handling
        semesterScheduleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SemesterScheduleMenuGUI schedule = new SemesterScheduleMenuGUI();
                schedule.setVisible(true);
                schedule.setLocationRelativeTo(null);
            }

        });
        JPanel noticeBoardBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        noticeBoardBtn = new JButton("Notice Board");
        noticeBoardBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	NoticesMenuGUI noticeBoard = new NoticesMenuGUI();
            	noticeBoard.setVisible(true);
            	noticeBoard.setLocationRelativeTo(null);
            }
          });
        noticeBoardBtnPanel.add(noticeBoardBtn);
        mainPanel.add(noticeBoardBtnPanel);

        JPanel signOutBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signOutBtn = new JButton("Sign Out");
        signOutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomeGUI welcomeGUI = new WelcomeGUI();
                welcomeGUI.setVisible(true);
                welcomeGUI.setLocationRelativeTo(null);
            }
        });
        signOutBtnPanel.add(signOutBtn);
        mainPanel.add(signOutBtnPanel);

        add(mainPanel);
    }

}
