package com.yashgarg.codsoft;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class QuizApp {
    static ArrayList<Question> questions;
    public static Timer timer;
    private static boolean timesup = false;
    public static Scanner fileScanner;
    public static int score = 0;
    public static void main(String[] args) {
        try {
            File file = new File("questions.txt");
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error : questions.txt file not found");
            throw new RuntimeException(e);
        }
        int numberOfQues = Integer.parseInt(fileScanner.nextLine());

        questions = new ArrayList<Question>();
        for (int i = 0; i < numberOfQues; i++) {
            questions.add(new Question(fileScanner.nextLine(),fileScanner.nextLine(),fileScanner.nextLine(),fileScanner.nextLine(),fileScanner.nextLine(),fileScanner.nextLine()));
        }
//        Timer timer = new Timer();
//        Triva task = new Triva(quiz.questions);
//        // 1 sec -> 1000 ms
//        // 1 min -> 60 sec -> 60 * 1000 ms
//        timer.schedule(task, 0,10000);

        askQuestion(questions, numberOfQues);
    }
    static void askQuestion(ArrayList<Question> questions, int numberOfQues){
        for (int i = 0; i < numberOfQues; i++) {
            timesup = false;
            Question currentQuestion = questions.get(i);
            currentQuestion.showQuestion();
            timer = new Timer();
            Scanner sc = new Scanner(System.in);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up! You didn't answer in time.");
                    System.out.print("Enter Any Key to Proceed !");
                    timesup = true;
                    synchronized (sc){
                        sc.notify();
                    }
                }
            };
            timer.schedule(task, 10000);
            String userAnswer = sc.nextLine();
            timer.cancel();
            if (userAnswer.equals(currentQuestion.getAns()) && !timesup) {
                score += 10;
                System.out.println("correct");
            } else if (timesup) {
                System.out.println("answer is too late!");
            } else {
                System.out.println("wrong");
            }
        }
        System.out.println("FINAL SCORE : " + score);
    }
}

class Question{
    String ques;
    String opt1;
    String opt2;
    String opt3;
    String opt4;
    String correctOpt;
    Question(String ques, String opt1,
             String opt2, String opt3, String opt4, String correctOpt){
        this.ques = ques;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.correctOpt = correctOpt;
    }
    public void showQuestion(){
        System.out.println(ques);
        System.out.println("A. " +opt1);
        System.out.println("B. " +opt2);
        System.out.println("C. " +opt3);
        System.out.println("D. " +opt4);
    }
    public String getAns(){
        return correctOpt;
    }
}
