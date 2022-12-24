package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// add semester schedule
// update semester schedule
// delete semester schedule

public class SemesterScheduleMenuGUI extends JFrame{
    private JButton addEvent;
    private JButton updateEvent;
    private JButton deleteEvent;
    private JButton showEvent;
    private JButton backButton;

    public SemesterScheduleMenuGUI() {
        setTitle("Semester Schdule");
        setSize(350,200);
        setLayout(new GridLayout(1,1,10,10));

        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JLabel welcomeLabel = new JLabel("SEMESTER SCHEDULE", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        mainPanel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        // Adding buttons

        addEvent = new JButton("Add Event");
        buttonPanel.add(addEvent);
        // event handling
        addEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SemesterScheduleGUI addSchedule = new SemesterScheduleGUI("addevent");
                addSchedule.setVisible(true); //frame will only be visible with set "true"
                addSchedule.setLocationRelativeTo(null); //for opening in center
            }
        });

        updateEvent = new JButton("Update Event");
        buttonPanel.add(updateEvent);
        // event handling
        updateEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SemesterScheduleGUI updateSchedule = new SemesterScheduleGUI("updateevent");
                updateSchedule.setVisible(true); //frame will only be visible with set "true"
                updateSchedule.setLocationRelativeTo(null); //for opening in center
            }
        });


        deleteEvent = new JButton("Delete Event");
        buttonPanel.add(deleteEvent);
        deleteEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SemesterScheduleGUI updateSchedule = new SemesterScheduleGUI("deleteevent");
                updateSchedule.setVisible(true); //frame will only be visible with set "true"
                updateSchedule.setLocationRelativeTo(null); //for opening in center
            }
        });

        showEvent = new JButton("Show Semester Schedule");
        buttonPanel.add(showEvent);
        showEvent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SemesterScheduleGUI displaySchedule = new SemesterScheduleGUI("showevent");
                displaySchedule.setVisible(true); //frame will only be visible with set "true"
                displaySchedule.setLocationRelativeTo(null); //for opening in center

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

        mainPanel.add(buttonPanel);
        add(mainPanel);
    }


}
