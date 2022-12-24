package com.company;

import javax.swing.*;
import java.io.Serializable;
import java.util.Scanner;


public class Excom extends Volunteer implements Serializable {
    private static String password="cscui@excom";
    private String date;
    private String eventName;
    private boolean isChair;
    private boolean isVChair;
    private boolean isGS;
    private boolean isTreasurer;
    private boolean isWMaster;
    private boolean isJS;

    public Excom(){
        super();
        date = eventName= null;
        isChair = isVChair = isGS = isTreasurer = isWMaster = isJS = false;
    }

    public Excom(String name, String regNo, String contact, String email) {
        super(name, regNo, contact, email);
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return super.toString()+"Excom{" +
                "password='" + password + '\'' +
                ", date='" + date + '\'' +
                ", eventName='" + eventName + '\'' +
                '}' + '\'' + ", Position='" + getPosition();
    }

    public boolean isChair() {
        return isChair;
    }

    public void setChair(boolean chair) {
        isChair = chair;
    }

    public boolean isVChair() {
        return isVChair;
    }

    public void setVChair(boolean VChair) {
        isVChair = VChair;
    }

    public boolean isGS() {
        return isGS;
    }

    public void setGS(boolean GS) {
        isGS = GS;
    }

    public boolean isTreasurer() {
        return isTreasurer;
    }

    public void setTreasurer(boolean treasurer) {
        isTreasurer = treasurer;
    }

    public boolean isWMaster() {
        return isWMaster;
    }

    public void setWMaster(boolean WMaster) {
        isWMaster = WMaster;
    }

    public boolean isJS() {
        return isJS;
    }

    public void setJS(boolean JS) {
        isJS = JS;
    }

    public String getPosition(){
        if(isGS()){
            return "General Secretary";
        }
        else if(isChair())
            return "Chairperson";
        else if(isVChair())
            return "Vice Chairperson";
        else if(isWMaster())
            return "Web Master";
        else if(isTreasurer())
            return "Treasurer";
        else if(isJS())
            return "Joint Secretary";
        else
            return null;
    }

    public void setPosition(String position){
        if(position.equalsIgnoreCase("chairperson")){
            setChair(true);
        }
        else if(position.equalsIgnoreCase("vice-chairperson")){
            setVChair(true);
        }
        else if(position.equalsIgnoreCase("general secretary")){
            setGS(true);
        }
        else if(position.equalsIgnoreCase("treasurer")){
            setTreasurer(true);
        }
        else if(position.equalsIgnoreCase("webmaster")){
            setWMaster(true);
        }
        else if(position.equalsIgnoreCase("joint secretary")){
            setJS(true);
        }

    }
}
