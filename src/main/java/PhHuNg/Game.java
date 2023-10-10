package PhHuNg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class QuizQuestion {
    private String question;
    private String answer;

    public QuizQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /*
     * show word theo dinh dang "wordtarget  | wordexplain".
     */
}

public class Game {
    private static ArrayList<QuizQuestion> quizQuestions;

    public void setQuizQuestions() {
        quizQuestions = new ArrayList<QuizQuestion>();
        try {
            File inFile = new File("src/main/java/PhHuNg/QuizQuestion.txt");
            FileReader fileReader = new FileReader(inFile);

            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] word = line.split("\t");
                QuizQuestion q = new QuizQuestion(word[0], word[1]);
                quizQuestions.add(q);
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void printQuizQuestion() {
        Scanner input0 = new Scanner(System.in);
        Random rand = new Random();
        int n = rand.nextInt(quizQuestions.size());
        QuizQuestion q = quizQuestions.get(n);
        System.out.printf(q.getQuestion());
        System.out.print("Your answer: ");
        String answerc = input0.nextLine();
        if (answerc.toUpperCase().equals(q.getAnswer())) {
            System.out.println("Correct.");
        } else {
            System.out.println("Incorrect. The correct answer is: " + q.getAnswer());
        }
    }

    public void gameInitializer() {
        Scanner input0 = new Scanner(System.in);
        String input1 = "Y";
        setQuizQuestions();
        while (input1.equals("Y") || input1.equals("y")) {
            printQuizQuestion();
            System.out.print("Continue? [Y] Yes / [N] No: ");
            input1 = input0.nextLine();
        }


    }

}