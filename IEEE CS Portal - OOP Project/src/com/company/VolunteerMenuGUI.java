package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolunteerMenuGUI extends JFrame {
    private JButton showNoticBoardBtn;
    private JButton excomInfoBtn;
    private JButton signOutBtn;
    private JButton semesterScheduleBtn;

    public VolunteerMenuGUI(Volunteer member){
        //arraylist read from excom file. used for login
        setTitle("Volunteer Section");
        setSize(450,240);
        setLayout(new GridLayout(1,1,10,10));

        JPanel mainPanel = new JPanel(new GridLayout(5,1));
        JLabel introLabel = new JLabel("Hi, "+member.getName()+"! What do you want to do next?", SwingConstants.CENTER);
        introLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        mainPanel.add(introLabel);

        JPanel excomInfoBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        excomInfoBtn = new JButton("Excom Information");
        excomInfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InformationGUI excomInformationGUI = new InformationGUI("show","excom");
                excomInformationGUI.setVisible(true);
                excomInformationGUI.setLocationRelativeTo(null);
            }
        });
        excomInfoBtnPanel.add(excomInfoBtn);
        mainPanel.add(excomInfoBtnPanel);

        JPanel noticeBoardBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        showNoticBoardBtn = new JButton("Notice Board");
        showNoticBoardBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NoticesGUI noticeBoard = new NoticesGUI("displaynotice");
                noticeBoard.setVisible(true);
                noticeBoard.setLocationRelativeTo(null);
            }
        });
        noticeBoardBtnPanel.add(showNoticBoardBtn);
        mainPanel.add(noticeBoardBtnPanel);

        JPanel semesterScheduleBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        semesterScheduleBtn = new JButton("Tentative Semester Schedule");
        semesterScheduleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SemesterScheduleGUI semesterScheduleGUI = new SemesterScheduleGUI("showevent");
                semesterScheduleGUI.setVisible(true);
                semesterScheduleGUI.setLocationRelativeTo(null);
            }
        });
        semesterScheduleBtnPanel.add(semesterScheduleBtn);
        mainPanel.add(semesterScheduleBtnPanel);

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
