package test;

import java.util.Scanner;

public class Student {
    private static int id=0;
    private String name;
    private int numberOfSubjects;
    private float[] marks;

    public Student(){
        id++;
        numberOfSubjects=0;
        name=null;
        marks=new float[numberOfSubjects];
    }

    public Student(String name, int numberOfSubjects, float[] marks){
        id++;
        this.name=name;
        this.numberOfSubjects=numberOfSubjects;
        for(int i=0; i<numberOfSubjects; i++){
            this.marks[i]=marks[i];
        }
    }

    public void readData(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter student name: ");
        name = input.nextLine();
        System.out.print("Enter number of subjects: ");
        numberOfSubjects = input.nextInt();

        marks = new float[numberOfSubjects];
        for(int i=0; i<numberOfSubjects; i++){
            System.out.print("Enter marks of subject "+(i+1)+": ");
            marks[i] = input.nextFloat();
        }
    }

    public void showData(){
        System.out.println("Student ID: "+id);
        System.out.println("Student Name: "+name);
        System.out.println("Marks: ");
        for (int i=0; i<numberOfSubjects; i++){
            System.out.println(" Subject "+(i+1)+": "+marks[i]);
        }

    }
}
