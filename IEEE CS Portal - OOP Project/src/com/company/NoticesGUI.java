package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class NoticesGUI extends JFrame {
	private JButton updateNotice;
	private JTextField tfEventName;
	private JButton showNext;
    private JButton backButton;
	private JTextField tfEventDate;
	private JTextField tfCaption;
	private ArrayList<Excom> EventList = new ArrayList<>();
	private String caption;
	
	
	public NoticesGUI(String noticeOption) {
		
		if (noticeOption.toLowerCase().equals("updatenotice")) {
            //readSemesterFile();
			readNoticeFile();

			setTitle("UpdateNoticeBoard");
            setSize(400,400);
            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(3,1));
            JLabel updateLabel = new JLabel("Update Notice Board", SwingConstants.CENTER);
            updateLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(updateLabel);
            
            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
            JPanel updateNoticePanel = new JPanel(new GridLayout(3,2,20,10));
            
            if (!EventList.isEmpty()) {
            	tfEventName = new JTextField(10);
            	JLabel nameLabel = new JLabel("Event Name: ");
            	tfEventName.setText(EventList.get(0).getEventName());
                JLabel dateLabel = new JLabel("Date: (dd-mm-yyyy) ");
                tfEventDate = new JTextField(10);
                tfEventDate.setText(EventList.get(0).getDate());
                JLabel captionLabel = new JLabel("Event Caption: ");
                readEventCaption();
                tfCaption = new JTextField(10);
                tfCaption.setText(""+caption);

                updateNoticePanel.add(nameLabel);
                updateNoticePanel.add(tfEventName);
                updateNoticePanel.add(dateLabel);
                updateNoticePanel.add(tfEventDate);
                updateNoticePanel.add(captionLabel);
                updateNoticePanel.add(tfCaption);
                inputPanel.add(updateNoticePanel);
                mainPanel.add(inputPanel);
                
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                updateNotice = new JButton("Update");            
                updateNotice.addActionListener(new ActionListener() {
                
    				@Override
    				public void actionPerformed(ActionEvent e) {
                        Checker check = new Checker();
                        tfEventName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                        tfEventDate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

                        if(tfEventName.getText().isEmpty() || tfEventDate.getText().isEmpty() || tfCaption.getText().isEmpty()){
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
                            int index = searchEventName();
                            if (index != -1) {
                            	Excom newEvent = EventList.get(index);
                            	EventList.remove(index);
                                EventList.add(0, newEvent);        						
        						writeEventCaption();
        						JOptionPane.showMessageDialog(null, "Notice Board Updated Successfully");
        						writeNoticeFile();
        						dispose();
                                
                            } else {
                                int input = JOptionPane.showConfirmDialog(null, "Event not found in semester schedule. Do you still want to add this event?");
                                if (input == 0) {
                                    Excom newEvent = new Excom();
                                    newEvent.setEventName(tfEventName.getText());
                                    newEvent.setDate(tfEventDate.getText());
                                    EventList.add(0, newEvent);
                                    writeEventCaption();
                                    writeNoticeFile();
                                    JOptionPane.showMessageDialog(null, "Notice Board Updated Successfully");
                                    dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Event Not Added!");
                                    dispose();
                                }
                            }
                        }
    				}
    				
                });
                
                showNext = new JButton("Next");
                showNext.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (EventList.size()!=1) {
							EventList.remove(0);
							tfEventName.setText(EventList.get(0).getEventName());
							tfEventDate.setText(EventList.get(0).getDate());
							
						}
						else {
							readSemesterFile();
						}
					}
                	
                });
                backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

                buttonPanel.add(updateNotice);
                buttonPanel.add(showNext);
                buttonPanel.add(backButton);
                mainPanel.add(buttonPanel);
            }
            
            add(mainPanel);
		}
		else if (noticeOption.toLowerCase().equals("displaynotice")) {
			readNoticeFile();
			setTitle("DisplayNoticeBoard");
            setSize(400,400);
            setLayout(new GridLayout(1,1,10,10));

            JPanel mainPanel = new JPanel(new GridLayout(4,1));
            JLabel updateLabel = new JLabel("N O T I C E  B O A R D", SwingConstants.CENTER);
            updateLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            mainPanel.add(updateLabel);
            
            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
            JPanel updateNoticePanel = new JPanel(new GridLayout(3,2,20,10));    
            if (!EventList.isEmpty()) {
            	tfEventName = new JTextField(10);
            	JLabel nameLabel = new JLabel("Event Name");  
                tfEventName.setText(EventList.get(0).getEventName());
                JLabel dateLabel = new JLabel("Date");
                tfEventDate = new JTextField(10);
                tfEventDate.setText(EventList.get(0).getDate());
                JLabel captionLabel = new JLabel("Event Caption");
                tfCaption = new JTextField(10);
                readEventCaption();
                tfCaption.setText(""+ caption);
                updateNoticePanel.add(nameLabel);
                updateNoticePanel.add(tfEventName);
                updateNoticePanel.add(dateLabel);
                updateNoticePanel.add(tfEventDate);
                updateNoticePanel.add(captionLabel);
                updateNoticePanel.add(tfCaption);
                inputPanel.add(updateNoticePanel);
                mainPanel.add(inputPanel);

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
            }
            else {
            	JOptionPane.showMessageDialog(null, "No Current Events. Please come back later!");
            }

            add(mainPanel);
		}
		
		
		
	}
	
	public void writeEventCaption() {
		try {
			PrintWriter output = new PrintWriter("EventCaption.txt");
			output.write(tfCaption.getText());
			output.close();
			
		}
		catch(Exception e) {
			System.out.println("Exception caught: " + e.toString());			
  		}
	}
	
	public void readEventCaption() {
		try {
			
			Scanner input = new Scanner(new File("EventCaption.txt"));
			caption = input.nextLine();
			input.close();
			
		}
		catch(Exception e) {
			System.out.println("Exception caught: " + e.toString());
  		}
		
		
	}
	
	public void readSemesterFile(){
        try{
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("SemesterSchedule.dat"));
                EventList.clear();
                while(true){
                	//System.out.println(reader.readObject().toString());
                	EventList.add((Excom) reader.readObject());
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
	
	public void readNoticeFile() {
		try{
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("NoticeBoard.dat"));
                EventList.clear();
                while(true){
                	EventList.add((Excom) reader.readObject());
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
	
	public void writeNoticeFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("NoticeBoard.dat"));

            for(int i=0; i<EventList.size(); i++) {
                out.writeObject(EventList.get(i));
            }
            out.close();

        }catch(Exception e) {
            System.out.println("Exception caught: "+e.toString());
        }
    }
	
    public int searchEventName(){
        readSemesterFile();
        if(tfEventName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Enter Event Name!");
        }
        for (int i=0; i<EventList.size(); i++){
            if(EventList.get(i).getEventName().equalsIgnoreCase(tfEventName.getText())){
                return i;
            }
        }
        return -1;
    }


}
