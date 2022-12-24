package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.*;

// add semester schedule
// update semester schedule
// delete semester schedule

public class SemesterScheduleGUI extends JFrame{
    private JButton addEvent;
    private JButton updateEventBtn;
    private JButton deleteEvent;
    private JButton deleteAllBtn;
    private JButton searchEventBtn;
    private JButton backButton;
    private JTextField tfEventName;
    private JTextField tfEventDate;
    private JTextField tfUpdatedEventName;
    private ArrayList<Excom> semesterSchedule = new ArrayList<>();




    public SemesterScheduleGUI(String scheduleOption) {
        if (scheduleOption.toLowerCase().equals("addevent")) {
            setTitle("Add Event");
            setSize(350,270);
            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(3,1));
            JLabel addLabel = new JLabel("A D D   E V E N T", SwingConstants.CENTER);
            addLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(addLabel);


            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
            JPanel addEventPanel = new JPanel(new GridLayout(2,2,20,10));
            JLabel labelEventName = new JLabel("Event Name: ");
            tfEventName = new JTextField(10);
            JLabel labelEventDate = new JLabel("Date: (dd-mm-yyyy) ");
            tfEventDate = new JTextField(10);
            addEventPanel.add(labelEventName);
            addEventPanel.add(tfEventName);
            addEventPanel.add(labelEventDate);
            addEventPanel.add(tfEventDate);
            inputPanel.add(addEventPanel);
            mainPanel.add(inputPanel);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            addEvent = new JButton("Add Event");
            buttonPanel.add(addEvent);
            addEvent.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Checker check = new Checker();
                    tfEventName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    tfEventDate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

                    if(tfEventName.getText().isEmpty() || tfEventDate.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Fill all information!");
                    }
                    else if(!check.isName(tfEventName.getText()) || !check.isDate(tfEventDate.getText())){
                        JOptionPane.showMessageDialog(null, "Enter Valid Inputs!");
                        if(!check.isName(tfEventName.getText())){
                            tfEventName.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                        if(!check.isDate(tfEventDate.getText())){
                            tfEventDate.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                    }
                    else {
                        readFile();
                        // Adding in file
                        Excom event = new Excom();
                        event.setEventName(tfEventName.getText());
                        event.setDate(tfEventDate.getText());
                        semesterSchedule.add(event);
                        writeFile();

                        JOptionPane.showMessageDialog(null, "Event Added Successfully!");
                        tfEventName.setText("");
                        tfEventDate.setText("");
                    }
                }
            });

            backButton = new JButton("Back");
            buttonPanel.add(backButton);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            mainPanel.add(buttonPanel);
            add(mainPanel);
        }

        else if (scheduleOption.toLowerCase().equals("updateevent")) {
            setTitle("Update Event");
            setSize(350,350);
            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(3,1));
            JLabel updateLabel = new JLabel("U P D A T E   E V E N T", SwingConstants.CENTER);
            updateLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(updateLabel);

            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
            JPanel updateEventPanel = new JPanel(new GridLayout(3,2,20,10));
            JLabel labelEventName = new JLabel("Event Name: ");
            tfEventName = new JTextField(10);
            JLabel labelNewEventName = new JLabel("Updated Event Name: ");
            tfUpdatedEventName = new JTextField(10);
            JLabel labelEventDate = new JLabel("Date: (dd-mm-yyyy) ");
            tfEventDate = new JTextField(10);
            updateEventPanel.add(labelEventName);
            updateEventPanel.add(tfEventName);
            updateEventPanel.add(labelNewEventName);
            updateEventPanel.add(tfUpdatedEventName);
            updateEventPanel.add(labelEventDate);
            updateEventPanel.add(tfEventDate);

            inputPanel.add(updateEventPanel);
            mainPanel.add(inputPanel);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            searchEventBtn = new JButton("Search");
            buttonPanel.add(searchEventBtn);
            searchEventBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ind = searchEventName();
                    if(ind!=-1){
                        tfEventDate.setText(semesterSchedule.get(ind).getDate());
                        tfUpdatedEventName.setText("");
                    }

                }
            });


            updateEventBtn = new JButton("Update");
            buttonPanel.add(updateEventBtn);
            updateEventBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Checker check = new Checker();
                    tfEventName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    tfEventDate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    tfUpdatedEventName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    if (tfUpdatedEventName.getText().isEmpty() || tfEventDate.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please Enter Event Details!");
                    }
                    else if(!check.isName(tfUpdatedEventName.getText()) || !check.isDate(tfEventDate.getText())){
                        JOptionPane.showMessageDialog(null, "Enter Valid Inputs!");
                        if(!check.isName(tfUpdatedEventName.getText())){
                            tfUpdatedEventName.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                        if(!check.isDate(tfEventDate.getText())){
                            tfEventDate.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                    }
                    else {
                        int ind = searchEventName();
                        if (ind != -1) {
                            semesterSchedule.get(ind).setEventName(tfUpdatedEventName.getText());
                            semesterSchedule.get(ind).setDate(tfEventDate.getText());
                            writeFile();

                            JOptionPane.showMessageDialog(null, "Event Updated!");
                            tfUpdatedEventName.setText("");
                            tfEventName.setText(semesterSchedule.get(ind).getEventName());
                            tfEventDate.setText(semesterSchedule.get(ind).getDate());
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Event Not Found!");
                        }
                    }

                }

            });

            backButton = new JButton("Back");
            buttonPanel.add(backButton);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            mainPanel.add(buttonPanel);
            add(mainPanel);
        }
        else if (scheduleOption.toLowerCase().equals("deleteevent")) {
            setTitle("Delete Event");
            setSize(350,270);
            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(3,1));
            JLabel deleteLabel = new JLabel("D E L E T E   E V E N T", SwingConstants.CENTER);
            deleteLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(deleteLabel);

            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
            JPanel deleteEventPanel = new JPanel(new GridLayout(2,2,20,10));
            JLabel labelEventName = new JLabel("Event Name: ");
            tfEventName = new JTextField(10);
            deleteEventPanel.add(labelEventName);
            deleteEventPanel.add(tfEventName);
            inputPanel.add(deleteEventPanel);
            mainPanel.add(inputPanel);

            deleteEvent = new JButton("Delete");
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(deleteEvent);
            deleteAllBtn = new JButton("Delete All");
            buttonPanel.add(deleteAllBtn);
            deleteEvent.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    readFile();
                    if(tfEventName.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Enter Event Name!");
                    }
                    else{
                        int i=0;
                        for(; i<semesterSchedule.size() && !semesterSchedule.isEmpty(); i++) {
                            if (semesterSchedule.get(i).getEventName().equalsIgnoreCase(tfEventName.getText())) {
                                Excom eventFound = semesterSchedule.get(i);
                                semesterSchedule.remove(eventFound);
                                JOptionPane.showMessageDialog(null, "Event Removed!");
                                i=-1;
                                writeFile();
                                dispose();
                                break;
                            }
                        }
                        if(i == semesterSchedule.size() ){
                            JOptionPane.showMessageDialog(null, "Event not found!");

                        }
                    }


                }

            });

            deleteAllBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    readFile();
                    semesterSchedule.clear();
                    writeFile();
                    JOptionPane.showMessageDialog(null, "Semester Schedule Cleared!");
                    dispose();
                }

            });

            backButton = new JButton("Back");
            buttonPanel.add(backButton);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            mainPanel.add(buttonPanel);
            add(mainPanel);
        }
        else if(scheduleOption.toLowerCase().equals("showevent")) {
            setTitle("Semester Schedule Display");

            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(3,1, 10, 10));
            JLabel showLabel = new JLabel("S E M E S T E R   S C H E D U L E", SwingConstants.CENTER);
            showLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(showLabel);

            readFile();
            int rows = semesterSchedule.size();

            // defining column names in a string
            String [] columnNames = {"Event Name", "Date"};
            String [][] data = new String[rows][2];
            for (int i = 0; i<rows; i++) {
                data[i][0] = semesterSchedule.get(i).getEventName();
                data[i][1] = semesterSchedule.get(i).getDate();
            }
            setSize(750,450);
            JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTable table = new JTable(data, columnNames);

            table.getTableHeader().setReorderingAllowed(false);
            table.setPreferredScrollableViewportSize(new Dimension(700,100));
            table.setFillsViewportHeight(true);
            setVisible(true);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10,50,970,200);
            tablePanel.add(new JScrollPane(table));
            mainPanel.add(tablePanel);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
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


    public void readFile(){
        try{
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("SemesterSchedule.dat"));
                semesterSchedule.clear();
                while(true){
                    semesterSchedule.add((Excom) reader.readObject());
                }
            }
            catch (EOFException e){
                System.out.println("File ended: "+e.toString());
            }
        }
        catch (Exception e){
            System.out.println("Exception caught: "+e.toString());
        }
    }

    public void writeFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SemesterSchedule.dat"));

            for(int i=0; i<semesterSchedule.size(); i++) {
                out.writeObject(semesterSchedule.get(i));
            }
            out.close();

        }catch(Exception e) {
            System.out.println("Exception caught: "+e.toString());
        }
    }

    public int searchEventName(){
        readFile();
        if(tfEventName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Enter Event Name!");
        }
        else {

            for (int i=0; i<semesterSchedule.size(); i++){
                if(semesterSchedule.get(i).getEventName().equalsIgnoreCase(tfEventName.getText())){
                    return i;
                }
            }
            JOptionPane.showMessageDialog(null, "Event not found!");

        }
        return -1;
    }


}


