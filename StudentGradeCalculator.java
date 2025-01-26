import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        double[] marks = new double[numSubjects];
        double totalMarks = 0;
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextDouble();
            totalMarks += marks[i];
        }
        double averagePercentage = totalMarks / numSubjects;
        char grade = calculateGrade(averagePercentage);
        System.out.println("\n--- Results ---");
        System.out.printf("Total Marks: %.2f%n", totalMarks);
        System.out.printf("Average Percentage: %.2f%%%n", averagePercentage);
        System.out.println("Grade: " + grade);
        scanner.close();
    }
    public static char calculateGrade(double average) {
        if (average >= 90) {
            return 'A';
        } 
        else if (average >= 80) {
            return 'B';
        } 
        else if (average >= 70) {
            return 'C';
        } 
        else if (average >= 60) {
            return 'D';
        }
         else {
            return 'F';
        }
    }
}
