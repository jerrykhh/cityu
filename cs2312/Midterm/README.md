# Midterm Question

### Q1
100 students are to take an online exam.  To detect cheating, the teacher sets 100 versions for each question.  Canvas then randomly assign one different version for each student to answer.
Without knowing this arrangement, some students may share their answers to others.  This is considered as a serious act of academic dishonesty.  Canvas can detect that easily, and report to the Department.

Requirement
Now, you are given a program for a simplified scenario: only 3 versions of a question, and 3 students involved.  Also, we assume that any sharer does not share a wrong answer (i.e. He only shares the correct answer for his question).

You are given main() and the class Question, and the frameworks for classes Student, AnswerScript, and Canvas.  The program should give the outputs as shown in the comments within the square brackets [ ] inside main(). Your task: Add all required code as follows for Part A - C.   

(a) [12 marks]
In Part A inside main(), objects for students and questions are created (each question object has the question text and the model answer);  canvas assigns questions to students; then each student prints his question.  The canvas should be implemented as a Singleton.  
(Singleton: 6 marks, others: 6 marks)

(b) [8 marks]
In Part B inside main(), students write their answers to produce the AnswerScript objects; then the answer scripts are dumped.

In the dumped output, we can see if any student has answered for the a mismatch question.  This evidence of cheating is checked in Part C.

(c) [10 marks]
In Part C inside main(), canvas checks all answer scripts for cheating cases.  (Hint: Add an array list of AnswerScript objects in the canvas; When an answer script is written, add it to the array list.) 
For each cheating case detected, canvas should print a line as shown in the comment in Part C inside main().  (p.s. In the simplified scenario, there is only one case, therefore only one line is printed.)
If no case is detected, leave the output empty.


Submission: Please include the code of Main, Question, Student, Canvas, and AnswerScript for this question.  If your code is incomplete (e.g. not doing all of part(a) and not covering the solution for part (b) or part(c)), you can still get partial marks.


### Q2
MoneyShared is a class which models a sum of money that is to be shared evenly.

Your task: Finish the given MoneyShared class and the main method which executes as shown in the given rundowns below (Underlined contents are input by the user).
```
Rundown #1				
Initial ($): 1000
Divided by: 5
Amount becomes: $200

Rundown #2
Initial ($): nine
Input mismatch!

Rundown #3
Initial ($): -50
Negative number!

Rundown #4
Initial ($): 50
Divided by: -10
Negative number!

Rundown #5
Initial ($): 50
Divided by: 6
Not divisible!
```
Note: Exception handlers should be provided in main() which handles special cases as shown in Rundown #2-5. The exceptions are classified as:
(i) input mismatch (see #2)
(ii) negative number (see #3,4)
(iii) indivisible operation (see #5)

Exception class for (i) is java.util.InputMismatchException
Exception classes for (ii) and (iii) are provided in the given code.

Submission: Please include the code of Main, MoneyShared, NegativeExcp, and NotDivExcp for this question.





### Q3
A procedure involves one or more steps. 

For each procedure, we create the first step, and then append new steps or other procedures as wanted.

You are given main() that creates steps and builds procedures.  The program should give the outputs as shown in the comments within the square brackets [] inside main(). Your task: Complete the Step class.

Submission: Please include the code of Main and Step for this question.






### Q4
A social network classifies members into different age groups:  Child, Teenager, Adult, Elderly

Each member is given an integer score, initialized as 0.  Once a member makes a friend within the same age group, both members get 1 score.  If the friend is in another age group, then 10 scores are given to each of them.

You are given the interface/classes for modelling the age groups (They are simply empty: without field or method.)

The given main() creates 4 members and records how they make friends, then shows their scores and
list of friends.  The program should give the outputs as shown in the comments within the square brackets [ ] inside main().

Your task:
Add all required code in the frameworks for Member (15 marks) and SocialNetwork (5 marks).

Hints:
- You should not need to add extra fields in these classes.
- You can check the classes of two objects to see if they belong to the same class
     using .getClass():  if (obj1.getClass() == obj2.getClass())


Submission: Please include the code of all classes and interfaces for the completed program.