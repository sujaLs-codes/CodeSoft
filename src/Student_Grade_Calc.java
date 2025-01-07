import java.util.Scanner;

public class Student_Grade_Calc {
    public static void main(String[] args) {
        System.out.println(">>>>>>>> STUDENT GRADE CALCULATOR <<<<<<<<");

        //Number of Subjects
        int subjects;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of subjects := ");
        subjects = s.nextInt();

        //Input marks for each subject
        int[] marks = new int[subjects];
        int totalmarks = 0;

        for(int i = 0;i < subjects;i++){
            System.out.print("Enter marks for subject (" + (i + 1) + ") [out of 100] := ");
            marks[i] = s.nextInt();
            totalmarks += marks[i];
        }
        //Calculating Average Percentage
        double AveragePercentage = (double) totalmarks /subjects;

        char Grades;
        if (AveragePercentage >= 90) {
            Grades = 'A';
        } else if (AveragePercentage >= 80) {
            Grades = 'B';
        } else if (AveragePercentage >= 70) {
            Grades = 'C';
        } else if (AveragePercentage >= 60) {
            Grades = 'D';
        }else {
            Grades = 'E';
        }

        System.out.println(">>>>>> Results are <<<<<<");
        System.out.println("Total obtained marks are := " + totalmarks);
        System.out.println("AveragePercentage are := " + AveragePercentage);
        System.out.println("Grade := " + Grades);

        s.close();
    }
}
