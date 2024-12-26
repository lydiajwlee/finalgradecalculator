import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of graded items (excluding the final exam):");
        int numItems = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        double totalPercentage = 0.0;
        double totalWeightedScore = 0.0;

        for (int i = 1; i <= numItems; i++) {
            System.out.println("Enter the percentage weight of graded item " + i + " (e.g., 20 for 20%):");
            double weight = scanner.nextDouble();

            System.out.println("Enter the score you got in graded item " + i + " (e.g., 85 for 85%):");
            double score = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            totalPercentage += weight;
            totalWeightedScore += (score * weight / 100);
        }

        System.out.println("Enter the percentage weight of the final exam (e.g., 30 for 30%):");
        double finalExamWeight = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        totalPercentage += finalExamWeight;

        if (totalPercentage != 100) {
            System.out.println("Warning: The total percentage weight of all items is " + totalPercentage + "%, which is not equal to 100%.");
        }

        double[] gradeThresholds = {90.0, 80.0, 70.0, 60.0};
        char[] gradeLetters = {'A', 'B', 'C', 'D'};

        // Determine the current letter grade
        char currentGrade = 'F';
        for (int i = 0; i < gradeThresholds.length; i++) {
            if (totalWeightedScore >= gradeThresholds[i]) {
                currentGrade = gradeLetters[i];
                break;
            }
        }

        for (int i = 0; i < gradeThresholds.length; i++) {
            double requiredFinalScore = (gradeThresholds[i] - totalWeightedScore) / (finalExamWeight / 100);
            if (requiredFinalScore <= 100) {
                System.out.printf("To achieve a grade of %c, you need to score at least %.2f%% on the final exam.\n", gradeLetters[i], requiredFinalScore);
            }
        }

        char highestGrade = 'F';
        if (totalPercentage >= 90) {
            highestGrade = 'A';
        } else if (totalPercentage >= 80) {
            highestGrade = 'B';
        } else if (totalPercentage >= 70) {
            highestGrade = 'C';
        } else if (totalPercentage >= 60) {
            highestGrade = 'D';
        }


        System.out.printf("Your current grade is: %.2f%% (%c)\n", totalWeightedScore, currentGrade);
        System.out.printf("The highest grade you can achieve is: %c\n", highestGrade);

        scanner.close();
    }
}
