package org.example.studentgradecalculator;

import java.util.Scanner;

public class StudentGradeCalculator {
    public void calculateGrade(){
        System.out.println("Enter the marks below:");
        Scanner sc = new Scanner(System.in);
        System.out.print("Python: ");
        int pythonMarks = sc.nextInt();
        System.out.print("Java: ");
        int javaMarks = sc.nextInt();
        System.out.print("Php: ");
        int phpMarks = sc.nextInt();
        System.out.print("Ruby: ");
        int rubyMarks = sc.nextInt();
        System.out.print("DSA: ");
        int dsaMarks = sc.nextInt();

        calculate(pythonMarks, javaMarks, phpMarks, rubyMarks, dsaMarks);
    }
    private void calculate(int subject1, int subject2, int subject3, int subject4, int subject5){
        int totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
        System.out.println("Total marks are: " + totalMarks);
        int avg = totalMarks / 5;
        System.out.println("Average marks are: " + avg);
        char grade;
        if (avg > 60 && avg <= 72) {
            grade = 'C';
        } else if (avg > 72 && avg <= 87) {
            grade = 'B';
        } else if (avg > 87 && avg <= 100) {
            grade = 'A';
        } else {
            grade = 'F';
        }
        System.out.println("Grade is: " + grade);

    }
    public static void main(String[] args) {
        StudentGradeCalculator calculator = new StudentGradeCalculator();
        calculator.calculateGrade();
    }
}
