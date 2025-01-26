import java.util.*;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizApplication {
    private List<Question> questions;
    private int score;
    private List<Boolean> results;

    public QuizApplication() {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.results = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Welcome to the Quiz ---");
        System.out.println("You have 10 seconds to answer each question.");
        System.out.println("Press Enter to start the quiz!");
        scanner.nextLine();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);

            System.out.printf("\nQuestion %d: %s%n", i + 1, question.getQuestionText());
            String[] options = question.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.printf("%d. %s%n", j + 1, options[j]);
            }

            System.out.print("Enter your choice (1-4): ");
            long startTime = System.currentTimeMillis();

            boolean answered = false;
            int answer = -1;
            while (!answered) {
                if (System.currentTimeMillis() - startTime > 10_000) {
                    System.out.println("\nTime's up! Moving to the next question.");
                    break;
                }
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                    answered = true;
                }
            }
            if (answered && answer >= 1 && answer <= 4) {
                if (answer - 1 == question.getCorrectAnswerIndex()) {
                    System.out.println("Correct!");
                    score++;
                    results.add(true);
                } else {
                    System.out.println("Incorrect.");
                    results.add(false);
                }
            } else if (!answered) {
                results.add(false);
            } else {
                System.out.println("Invalid choice. Moving to the next question.");
                results.add(false);
            }
        }

        scanner.close();
        displayResults();
    }

    private void displayResults() {
        System.out.println("\n--- Quiz Results ---");
        System.out.printf("Your score: %d/%d%n", score, questions.size());
        System.out.println("Summary:");

        for (int i = 0; i < results.size(); i++) {
            System.out.printf("Question %d: %s%n", i + 1, results.get(i) ? "Correct" : "Incorrect");
        }

        System.out.println("Thank you for participating in the quiz!");
    }

    public static void main(String[] args) {
        QuizApplication quizApp = new QuizApplication();

        // Add questions to the quiz
        quizApp.addQuestion(new Question(
            "What is the capital of France?",
            new String[]{"Berlin", "Madrid", "Paris", "Rome"},
            2
        ));
        quizApp.addQuestion(new Question(
            "Which programming language is known for its simplicity?",
            new String[]{"C++", "Java", "Python", "JavaScript"},
            2
        ));
        quizApp.addQuestion(new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"Earth", "Mars", "Jupiter", "Venus"},
            1
        ));
        quizApp.addQuestion(new Question(
            "What is the chemical symbol for water?",
            new String[]{"H2O", "O2", "CO2", "HO2"},
            0
        ));
        quizApp.addQuestion(new Question(
            "Who wrote 'Hamlet'?",
            new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "J.K. Rowling"},
            1
        ));
        quizApp.startQuiz();
    }
}
