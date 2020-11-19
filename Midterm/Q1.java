
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Q1 {

    public static void main(String[] args) {
        //Part (A) 

        //3 questions are created:  Each question has the question text and model answer
        Question Q1v113 = new Question("Q1v113 question", "answer for Q1v113");
        Question q1v132 = new Question("Q1v132 question", "answer for Q1v132");
        Question q1v009 = new Question("Q1v009 question", "answer for Q1v009");

        //3 student objects are created.  Their names are passed to the constructor.
        Student honest = new Student("Honest");
        Student copier = new Student("Copier");
        Student sharer = new Student("Sharer");

        Canvas canvas = Canvas.getInstance();
        canvas.assignQuestion(honest, q1v009);
        canvas.assignQuestion(copier, Q1v113);
        canvas.assignQuestion(sharer, q1v132);

        honest.printQuestion(); //Output [Honest should answer Q1v009 question]
        copier.printQuestion(); //Output [Copier should answer Q1v113 question]
        sharer.printQuestion(); //Output [Sharer should answer Q1v132 question]

        //Part (B) 
        AnswerScript as1, as2, as3;
        as1 = honest.write("wrong answer for Q1v009");
        as2 = copier.write("answer for Q1v132");
        as3 = sharer.write("answer for Q1v132");

        as1.dump(); //Output [Honest answered "wrong answer for Q1v009" for "Q1v009 question"]
        as2.dump(); //Output [Copier answered "answer for Q1v132" for "Q1v113 question"]
        as3.dump(); //Output [Sharer answered "answer for Q1v132" for "Q1v132 question"]

        //Part (C)
        canvas.reportCheating(); //Output [Suspected: Sharer => Copier. Report both to the Department.]
    }
}

class Question {

    private String questionText;
    private String modelAnswer;

    public Question(String questionText, String modelAnswer) {
        this.questionText = questionText;
        this.modelAnswer = modelAnswer;
    }

    public String getModelAnswer() {
        return modelAnswer;
    }

    @Override
    public String toString() {
        return questionText;
    }
}


class Student {

   
    private String name; //the name of the student
    private Question question; //the question that the student should answer

    public Student(String name) {
        this.name = name;
        this.question = null;
        Canvas.getInstance().addStudent(this);
    }

    public String getName() {
        return name;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void printQuestion() {
        System.out.println(name + " should answer " + question);

    }

    public AnswerScript write(String result) {
        return new AnswerScript(this, result);
    }

}


class Canvas {


    private static Canvas instance = new Canvas();
    private static ArrayList<Student> students;
    private static ArrayList<AnswerScript> anslist;
    private static ArrayList<String> cheatingLog;

    private Canvas() {
        students = new ArrayList();
        anslist = new ArrayList();
        cheatingLog = new ArrayList();
    }

    public static Canvas getInstance() {
        return instance;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addAnsScript(AnswerScript answerScript) {
        anslist.add(answerScript);
        checkCheating(answerScript);
    }

    private void checkCheating(AnswerScript answerScript) {
        for (AnswerScript ans : anslist) {
            if (answerScript.getAns().equals(ans.getAns())
                    && !ans.getStudent().getQuestion().equals(answerScript.getStudent().getQuestion())) {

                cheatingLog.add("Suspected: " + answerScript.getStudent().getName() + " => " + ans.getStudent().getName() + ". Report both to the Department.");

            }
        }
    }

    public void assignQuestion(Student writer, Question question) {
        for (Student student : students) {
            if (student == writer) {
                student.setQuestion(question);
            }
        }
    }

    public ArrayList getStudentList() {
        return students;
    }

    public void reportCheating() {
        cheatingLog.forEach(loggMessage -> System.out.println(loggMessage));
    }

}


class AnswerScript {


    private Student writer;
    private String ans; 

    public AnswerScript(Student writer, String ans) {
        this.writer = writer;
        this.ans = ans;
        Canvas.getInstance().addAnsScript(this);
    }

    public static AnswerScript searchAnswerScript(ArrayList<AnswerScript> ansList, Student student) {
        for (AnswerScript ans : ansList) {
            if (ans.getStudent() == student) {
                return ans;
            }
        }
        return null;
    }

    public Student getStudent() {
        return writer;
    }

    public String getAns() {
        return ans;
    }

    public void dump() {
        System.out.println(writer.getName() + " answered \"" + ans + "\" for \"" + writer.getQuestion() + "\"");
    }

}

