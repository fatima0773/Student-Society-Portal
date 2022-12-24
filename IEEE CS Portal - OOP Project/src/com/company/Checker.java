package com.company;

public class Checker {
    public boolean isName(String name){
        for(int i=0; i<name.length() && name!=null; i++){
            if(!Character.isAlphabetic(name.charAt(i)) && name.charAt(i)!=' ' && name.charAt(i)!='-'){
                return false;
            }
        }
        return true;
    }

    public boolean isContact(String number){
        if(number.length()==11){
            for(int i=0; i<number.length() && number!=null; i++){
                if(!Character.isDigit(number.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isNumber(String number){
        for(int i=0; i<number.length() && number!=null; i++){
            if(!Character.isDigit(number.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public boolean isEmail(String email){
        if(email.endsWith("@gmail.com")||email.endsWith("@yahoo.com")||email.endsWith("@hotmail.com")||email.endsWith("@outlook.com")
                ||email.endsWith("@ieee.org")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isDate(String date){
        if(date.length()==10){
            if(isNumber(date.substring(0,2)) && date.charAt(2)=='-' && isNumber(date.substring(3,5)) && date.charAt(5)=='-'
                    && isNumber(date.substring(6,9))){
                return true;
            }
        }
        return false;

    }
}
