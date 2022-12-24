package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class LoginGUI extends JFrame {
    private JTextField tfRegNo;
    private JPasswordField tfPass;
    private JButton loginButton;
    private JButton backButton;
    private ArrayList<Excom> excomMembers = new ArrayList<>();
    private ArrayList<Volunteer> volunteers = new ArrayList<>();

    public LoginGUI(String sectionName){
        if(sectionName.toLowerCase().equals("excom")){
            readFile(excomMembers, "ExcomMembers.dat");

            //arraylist read from excom file. used for login
            setTitle("Excom Login");
            setSize(350,350);
            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(3,1));
            JLabel loginLabel = new JLabel("E X C O M   L O G I N", SwingConstants.CENTER);
            loginLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(loginLabel);

            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel loginFieldPanel = new JPanel(new GridLayout(4,1));
            JLabel labelRegNo = new JLabel("Reg. no: (Eg: fa18bse001)");
            tfRegNo = new JTextField(10);
            JLabel labelPass = new JLabel("Password: ");
            tfPass = new JPasswordField(10);
            loginFieldPanel.add(labelRegNo);
            loginFieldPanel.add(tfRegNo);
            loginFieldPanel.add(labelPass);
            loginFieldPanel.add(tfPass);
            inputPanel.add(loginFieldPanel);
            mainPanel.add(inputPanel);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            loginButton = new JButton("Log in");
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i=0;
                    for(; i<excomMembers.size(); i++) {
                        String myPass=String.valueOf(tfPass.getPassword());
                        if (excomMembers.get(i).getRegNo().equalsIgnoreCase(tfRegNo.getText()) &&
                                excomMembers.get(i).getPassword().equals(myPass)) {
                            JOptionPane.showMessageDialog(null, "Login Successful!");
                            dispose();
                            ExcomMenuGUI excomMenu = new ExcomMenuGUI(excomMembers.get(i));
                            excomMenu.setVisible(true);                //frame will only be visible with set "true"
                            excomMenu.setLocationRelativeTo(null);     //for opening in center
                            break;
                        }
                    }
                    if(!excomMembers.get(i).getRegNo().equalsIgnoreCase(tfRegNo.getText()) || i==excomMembers.size()){
                        JOptionPane.showMessageDialog(null, "Invalid Reg. No or Password!");
                    }
                }
            });
            buttonPanel.add(loginButton);

            backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    WelcomeGUI welcomeGUI = new WelcomeGUI();
                    welcomeGUI.setVisible(true);                //frame will only be visible with set "true"
                    welcomeGUI.setLocationRelativeTo(null);     //for opening in center
                }
            });
            buttonPanel.add(backButton);
            mainPanel.add(buttonPanel);
            add(mainPanel);
        }

        if(sectionName.toLowerCase().equals("volunteer")){
            readFile(volunteers, "Volunteers.dat");

            //arraylist read from excom file. used for login
            setTitle("Volunteer Login");
            setSize(370,200);
            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(3,1));
            JLabel loginLabel = new JLabel("V O L U N T E E R   L O G I N", SwingConstants.CENTER);
            loginLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(loginLabel);

            JPanel inputPanel = new JPanel(new FlowLayout());
            JPanel loginFieldPanel = new JPanel(new GridLayout(1,2,20,10));
            JLabel labelRegNo = new JLabel("Reg. no: (Eg: fa18bse001)");
            tfRegNo = new JTextField(10);
            loginFieldPanel.add(labelRegNo);
            loginFieldPanel.add(tfRegNo);
            inputPanel.add(loginFieldPanel);
            mainPanel.add(inputPanel);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
            loginButton = new JButton("Log in");
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i=0;
                    for(; i<volunteers.size(); i++) {
                        if (volunteers.get(i).getRegNo().equalsIgnoreCase(tfRegNo.getText())) {
                            JOptionPane.showMessageDialog(null, "Login Successful!");
                            dispose();
                            VolunteerMenuGUI volunteerMenu = new VolunteerMenuGUI(volunteers.get(i));
                            volunteerMenu.setVisible(true);                //frame will only be visible with set "true"
                            volunteerMenu.setLocationRelativeTo(null);     //for opening in center
                            break;
                        }
                    }
                    if(i == volunteers.size()){
                        JOptionPane.showMessageDialog(null, "Invalid Reg. No or Password!");
                    }
                }
            });
            buttonPanel.add(loginButton);

            backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    WelcomeGUI welcomeGUI = new WelcomeGUI();
                    welcomeGUI.setVisible(true);                //frame will only be visible with set "true"
                    welcomeGUI.setLocationRelativeTo(null);     //for opening in center
                }
            });
            buttonPanel.add(backButton);
            mainPanel.add(buttonPanel);
            add(mainPanel);
        }

    }

    public void readFile(ArrayList arrayList, String fileName){
        try{
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName));
                arrayList.clear();
                while(true){
                    arrayList.add((Volunteer) reader.readObject());
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
    public void writeFile(ArrayList arrayList, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));

            for(int i=0; i<arrayList.size(); i++) {
                out.writeObject(arrayList.get(i));
            }
            System.out.println("size: "+arrayList.size());
            out.close();

        }catch(Exception e) {
            System.out.println("Exception caught: "+e.toString());
        }
    }

}
