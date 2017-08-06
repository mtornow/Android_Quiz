package com.mtsum.android_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int score = 0; //number of correct answers
    int questionNumber = 0; //number of current question where the user is at
    int edittextNumber = 0; //number of the current editText question where the user is at
    int[] answers = {-1, 15, 1, 3, -1, 4, 2, 0};
    /*answers for the individual questions -1 for edittext questions, 1,2,3,4 for radiobutton answers,
    / -1 for edittext questions (always -1, will then refer to extra stored String answers)
    / 1,2,3,4 for radiobutton answers, according to the buttons up->down
    /0 to 26 for checkbox answers, whereas the answers are calculated as follows
    /0 if no answer is correct 5,6,7,8 points (addition) if answers are correct up->down butttons */
    String[] edittextAnswers = {"quiz", "answer"};
    String[] questions = {"How much is the fish?", "Which one is superior?",
            "Who is my favourite ?", "What is love?", "What comes after a?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String getQuestiontype() {
        LinearLayout checkboxLayout = (LinearLayout) findViewById(R.id.checkbox_answer);
        LinearLayout edittextLayout = (LinearLayout) findViewById(R.id.edittext_answer);
        if (checkboxLayout.getVisibility() == View.VISIBLE) {
            return "checkbox";
        } else if (edittextLayout.getVisibility() == View.VISIBLE) {
            return "edittext";
        } else {
            return "radio";
        }
    }

    //Calculates answerScore for RadioButton-Style answers
    //@return returns the calculated answerScore
    private int CheckRadioAnswer() {
        int answerScore = 0;
        RadioButton answer1Button = (RadioButton) findViewById(R.id.radio_answer1);
        RadioButton answer2Button = (RadioButton) findViewById(R.id.radio_answer2);
        RadioButton answer3Button = (RadioButton) findViewById(R.id.radio_answer3);
        RadioButton answer4Button = (RadioButton) findViewById(R.id.radio_answer4);
        if (answer1Button.isChecked() == true) {
            answerScore += 1;
        } else if (answer2Button.isChecked() == true) {
            answerScore += 2;
        } else if (answer3Button.isChecked() == true) {
            answerScore += 3;
        } else if (answer4Button.isChecked() == true) {
            answerScore += 4;
        }
        return answerScore;
    }

    private int CheckCheckboxAnswer() {
        int answerScore = 0;
        CheckBox answer1Button = (CheckBox) findViewById(R.id.checkbox_answer1);
        CheckBox answer2Button = (CheckBox) findViewById(R.id.checkbox_answer2);
        CheckBox answer3Button = (CheckBox) findViewById(R.id.checkbox_answer3);
        CheckBox answer4Button = (CheckBox) findViewById(R.id.checkbox_answer4);
        if (answer1Button.isChecked() == true) {
            answerScore += 1;
        } else if (answer2Button.isChecked() == true) {
            answerScore += 2;
        } else if (answer3Button.isChecked() == true) {
            answerScore += 4;
        } else if (answer4Button.isChecked() == true) {
            answerScore += 8;
        }
        return answerScore;

    }

    private int CheckEdittextAnswer(){
        EditText edittext1 = (EditText) findViewById(R.id.edittext_answer1);
        if(edittext1.getText().toString().equalsIgnoreCase(edittextAnswers[edittextNumber]) && answers[questionNumber] == -1){
            return 1;
        }
        else{
            return 0;
        }
    }

    private void nextQuestion() {
        //TODO shift to next question
        TextView question = (TextView) findViewById(R.id.question);

        question.setText(questions[questionNumber]);
    }

    private void rightAnswer() {
        score += 1;
        Toast.makeText(this, "Your answer was correct.", Toast.LENGTH_SHORT).show();
    }

    private void wrongAnswer() {
        Toast.makeText(this, "Your answer was wrong.", Toast.LENGTH_SHORT).show();
    }

    public void SubmitAnswer(View view) {
        String questionType = getQuestiontype();
        if (questionType == "radio") {
            if (answers[questionNumber] == CheckRadioAnswer()) {
                rightAnswer();
                nextQuestion();
            } else {
                wrongAnswer();
                nextQuestion();
            }
        } else if (questionType == "checkbox") {
            if (answers[questionNumber] == CheckCheckboxAnswer()) {
                rightAnswer();
                nextQuestion();
            } else {
                wrongAnswer();
                nextQuestion();
            }
        } else if (questionType == "edittext") {
            if (CheckEdittextAnswer() == 1) {
                edittextNumber +=1;
                rightAnswer();
                nextQuestion();
            } else {
                edittextNumber +=1;
                wrongAnswer();
                nextQuestion();
            }
        } else {
            Toast.makeText(this, "AN ERROR OCCURED, PLEASE RESTART THE APP", Toast.LENGTH_SHORT).show();
        }
    }
}
