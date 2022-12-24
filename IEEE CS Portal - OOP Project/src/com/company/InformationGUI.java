package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class InformationGUI extends JFrame {
    private JTextField tfName;
    private JTextField tfRegNo;
    private JTextField tfContact;
    private JTextField tfEmail;
    private JComboBox position;
    private JButton addRecord;
    private JButton updateRecord;
    private JButton searchRecord;
    private JButton deleteRecord;
    private JButton backButton;
    private ArrayList<Excom> excomArrayList = new ArrayList<>();
    private ArrayList<Volunteer> volunteerArrayList = new ArrayList<>();

    public InformationGUI(String option, String type){
        if(option.equalsIgnoreCase("update")){
            if(type.equalsIgnoreCase("excom")){
                setTitle("Update Excom Information");
            }
            else{
                setTitle("Update Volunteer Information");
            }
            setSize(450,500);
            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(3,1));
            JLabel headingLabel = new JLabel("U P D A T E   I N F O R M A T I O N", SwingConstants.CENTER);
            headingLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(headingLabel);

            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
            JPanel informationPanel = new JPanel(new GridLayout(5,2,10,5));
            JLabel labelName = new JLabel("Name: ");
            tfName = new JTextField(10);
            JLabel labelRegNo = new JLabel("Registration No.: (Eg: fa18bse001)");
            tfRegNo = new JTextField(10);
            JLabel labelContact = new JLabel("Contact: (Eg: 0321xxxxxxx)");
            tfContact = new JTextField(10);
            JLabel labelEmail = new JLabel("Email: ");
            tfEmail = new JTextField(10);

            informationPanel.add(labelName);
            informationPanel.add(tfName);
            informationPanel.add(labelRegNo);
            informationPanel.add(tfRegNo);
            informationPanel.add(labelContact);
            informationPanel.add(tfContact);
            informationPanel.add(labelEmail);
            informationPanel.add(tfEmail);

            if(type.equalsIgnoreCase("excom")){
                JLabel labelPosition = new JLabel("Position: ");
                String[] excomPositions = {"Chairperson", "Vice-Chairperson", "General Secretary", "Treasurer", "Web Master", "Joint Secretary"};
                position = new JComboBox(excomPositions);
                informationPanel.add(labelPosition);
                informationPanel.add(position);
            }
            inputPanel.add(informationPanel);
            mainPanel.add(inputPanel);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            addRecord = new JButton("Add Record");
            buttonPanel.add(addRecord);
            addRecord.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Checker check = new Checker();
                    tfName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    tfContact.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    tfEmail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

                    if(tfRegNo.getText().isEmpty() || tfName.getText().isEmpty() || tfContact.getText().isEmpty()
                            || tfEmail.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Fill all information!");
                    }
                    else if(!check.isName(tfName.getText()) || !check.isContact(tfContact.getText()) || !check.isEmail(tfEmail.getText())){
                        JOptionPane.showMessageDialog(null, "Enter Valid Inputs!");
                        if(!check.isName(tfName.getText())){
                            tfName.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                        if(!check.isContact(tfContact.getText())){
                            tfContact.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                        if(!check.isEmail(tfEmail.getText())){
                            tfEmail.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                    }
                    else{
                        //searches reg Number
                        if(searchMember(type)==-1){
                            if (type.equalsIgnoreCase("excom")){
                                readFile(excomArrayList, "ExcomMembers.dat");
                                Excom addInfo = new Excom();
                                addInfo.setName(tfName.getText());
                                addInfo.setRegNo(tfRegNo.getText());
                                addInfo.setContact(tfContact.getText());
                                addInfo.setEmail(tfEmail.getText());
                                addInfo.setPosition((String)position.getSelectedItem());
                                excomArrayList.add(addInfo);
                                writeFile(excomArrayList, "ExcomMembers.dat");
                            }
                            else{
                                Volunteer addInfo = new Volunteer();
                                addInfo.setName(tfName.getText());
                                addInfo.setRegNo(tfRegNo.getText());
                                addInfo.setContact(tfContact.getText());
                                addInfo.setEmail(tfEmail.getText());
                                volunteerArrayList.add(addInfo);
                                writeFile(volunteerArrayList, "Volunteers.dat");
                            }
                            JOptionPane.showMessageDialog(null, "Member added successfully!");
                            tfName.setText("");
                            tfRegNo.setText("");
                            tfContact.setText("");
                            tfEmail.setText("");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Member information already exists!");
                        }
                    }
                }
            });

            searchRecord = new JButton("Search Record");
            buttonPanel.add(searchRecord);
            searchRecord.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int ind = searchMember(type);
                    if(ind!=-1){
                        if (type.equalsIgnoreCase("excom")){
                            tfName.setText(excomArrayList.get(ind).getName());
                            tfContact.setText(excomArrayList.get(ind).getContact());
                            tfEmail.setText(excomArrayList.get(ind).getEmail());
                            position.setSelectedItem(excomArrayList.get(ind).getPosition());
                        }
                        else{
                            tfName.setText(volunteerArrayList.get(ind).getName());
                            tfContact.setText(volunteerArrayList.get(ind).getContact());
                            tfEmail.setText(volunteerArrayList.get(ind).getEmail());
                        }
                    }
                    else if(!tfRegNo.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Member not found!");
                    }
                }
            });

            updateRecord = new JButton("Update Record");
            buttonPanel.add(updateRecord);
            updateRecord.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Checker check = new Checker();
                    tfName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    tfContact.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    tfEmail.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    if(tfRegNo.getText().isEmpty() || tfName.getText().isEmpty() || tfContact.getText().isEmpty()
                            || tfEmail.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Fill all information!");
                    }
                    else if(!check.isName(tfName.getText()) || !check.isContact(tfContact.getText()) || !check.isEmail(tfEmail.getText())){
                        JOptionPane.showMessageDialog(null, "Enter Valid Inputs!");
                        if(!check.isName(tfName.getText())){
                            tfName.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                        if(!check.isContact(tfContact.getText())){
                            tfContact.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                        if(!check.isEmail(tfEmail.getText())){
                            tfEmail.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
                        }
                    }
                    else {
                        int ind = searchMember(type);
                        if (ind != -1) {
                            if (type.equalsIgnoreCase("excom")) {
                                excomArrayList.get(ind).setName(tfName.getText());
                                excomArrayList.get(ind).setRegNo(tfRegNo.getText());
                                excomArrayList.get(ind).setContact(tfContact.getText());
                                excomArrayList.get(ind).setEmail(tfEmail.getText());
                                excomArrayList.get(ind).setPosition((String) position.getSelectedItem());
                                writeFile(excomArrayList, "ExcomMembers.dat");
                            } else {
                                volunteerArrayList.get(ind).setName(tfName.getText());
                                volunteerArrayList.get(ind).setRegNo(tfRegNo.getText());
                                volunteerArrayList.get(ind).setContact(tfContact.getText());
                                volunteerArrayList.get(ind).setEmail(tfEmail.getText());
                                writeFile(volunteerArrayList, "Volunteers.dat");
                            }
                            JOptionPane.showMessageDialog(null, "Member information updated Successfully!");
                            tfName.setText("");
                            tfRegNo.setText("");
                            tfContact.setText("");
                            tfEmail.setText("");

                        } else if (!tfRegNo.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Member not found!");
                        }
                    }
                }
            });

            deleteRecord = new JButton("Delete Record");
            buttonPanel.add(deleteRecord);
            deleteRecord.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ind = searchMember(type);
                    if(ind!=-1){
                        if(type.equalsIgnoreCase("excom")){
                            excomArrayList.get(ind).setName(tfName.getText());
                            excomArrayList.get(ind).setRegNo(tfRegNo.getText());
                            excomArrayList.get(ind).setContact(tfContact.getText());
                            excomArrayList.get(ind).setEmail(tfEmail.getText());
                            excomArrayList.get(ind).setPosition((String)position.getSelectedItem());
                        }
                        else{
                            volunteerArrayList.get(ind).setName(tfName.getText());
                            volunteerArrayList.get(ind).setRegNo(tfRegNo.getText());
                            volunteerArrayList.get(ind).setContact(tfContact.getText());
                            volunteerArrayList.get(ind).setEmail(tfEmail.getText());
                        }

                        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the record?", "Delete Record",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if(result == JOptionPane.YES_OPTION){
                            if(type.equalsIgnoreCase("excom")){
                                excomArrayList.remove(ind);
                                writeFile(excomArrayList, "ExcomMembers.dat");
                            }
                            else{
                                volunteerArrayList.remove(ind);
                                writeFile(volunteerArrayList, "Volunteers.dat");
                            }
                            JOptionPane.showMessageDialog(null, "Member deleted successfully!");
                            dispose();
                        }
                    }
                    else if(!tfRegNo.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Member not found!");
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
            mainPanel.add(buttonPanel);
            add(mainPanel);

        }
        else if(option.equalsIgnoreCase("show")){
            if(type.equalsIgnoreCase("excom")){
                setTitle("Excom Information");
            }
            else{
                setTitle("Volunteer Information");
            }

            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(3,1, 10, 10));
            JLabel showLabel;
            if(type.equalsIgnoreCase("excom")){
                showLabel = new JLabel("E X C O M   I N F O R M A T I O N", SwingConstants.CENTER);
            }
            else{
                showLabel = new JLabel("V O L U N T E E R   I N F O R M A T I O N", SwingConstants.CENTER);
            }
            showLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(showLabel);

            JTable table;
            if(type.equalsIgnoreCase("excom")){
                readFile(excomArrayList, "ExcomMembers.dat");
                int rows = excomArrayList.size();
                // defining column names in a string
                String [] columnNames = {"Positon", "Name", "Registration Number", "Contact", "Email"};

                String [][] data = new String[rows][5];
                for (int i = 0; i<rows; i++) {
                    data[i][0] = excomArrayList.get(i).getPosition();
                    data[i][1] = excomArrayList.get(i).getName();
                    data[i][2] = excomArrayList.get(i).getRegNo();
                    data[i][3] = excomArrayList.get(i).getContact();
                    data[i][4] = excomArrayList.get(i).getEmail();
                }
                table = new JTable (data, columnNames);
            }
            else{
                readFile(volunteerArrayList, "Volunteers.dat");
                int rows = volunteerArrayList.size();

                // defining column names in a string
                String [] columnNames = {"Name", "Registration Number", "Contact", "Email"};
                String [][] data = new String[rows][4];
                for (int i = 0; i<rows; i++) {
                    data[i][0] = volunteerArrayList.get(i).getName();
                    data[i][1] = volunteerArrayList.get(i).getRegNo();
                    data[i][2] = volunteerArrayList.get(i).getContact();
                    data[i][3] = volunteerArrayList.get(i).getEmail();
                }
                table = new JTable (data, columnNames);
            }

            setSize(750,450);
            JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

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

    public void readFile(ArrayList arrayList, String fileName){
        try{
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName));
                arrayList.clear();
                while(true){
                    if(fileName.equalsIgnoreCase("ExcomMembers.dat"))
                        arrayList.add((Excom) reader.readObject());
                    else
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

    public int searchMember(String type){
        if(type.equalsIgnoreCase("excom")){
            readFile(excomArrayList, "ExcomMembers.dat");
            if(tfRegNo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Enter Registration Number!");
            }
            else {
                for (int i=0; i<excomArrayList.size(); i++){
                    if(excomArrayList.get(i).getRegNo().equalsIgnoreCase(tfRegNo.getText())){
                        return i;
                    }
                }
            }
        }
        else{
            readFile(volunteerArrayList, "Volunteers.dat");
            if(tfRegNo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Enter Registration Number!");
            }
            else {
                for (int i=0; i<volunteerArrayList.size(); i++){
                    if(volunteerArrayList.get(i).getRegNo().equalsIgnoreCase(tfRegNo.getText())){
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
