package it.matteospaggiari.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int TOTAL_RIGHT_ANSWER = 8;
    final int NUMBER_QUESTIONS = 6;
    protected ImageView headerImage;
    protected int rightAnswersUser;
    protected int wrongAnswersUser;
    protected int unansweredQuestionsUser;
    protected RadioGroup firstAnswer;
    protected RadioGroup secondAnswer;
    protected CheckBox answerFiveThirdQuestion;
    protected CheckBox answerTwelveThirdQuestion;
    protected CheckBox answerSixThirdQuestion;
    protected CheckBox answerNineThirdQuestion;
    protected EditText answerFourthQuestion;
    protected CheckBox firstAnswerFifthQuestion;
    protected CheckBox secondAnswerFifthQuestion;
    protected CheckBox thirdAnswerFifthQuestion;
    protected CheckBox fourthAnswerFifthQuestion;
    protected RadioGroup sixAnswer;
    protected Button submit;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        headerImage = findViewById(R.id.header_image_view);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If landscape hide image
            headerImage.setVisibility(View.GONE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            // If portrait show image
            headerImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference of the views
        firstAnswer = findViewById(R.id.answers_first_question);
        secondAnswer = findViewById(R.id.answers_second_question);
        answerFiveThirdQuestion = findViewById(R.id.answer_five_third_question);
        answerTwelveThirdQuestion = findViewById(R.id.answer_twelve_third_question);
        answerSixThirdQuestion = findViewById(R.id.answer_six_third_question);
        answerNineThirdQuestion = findViewById(R.id.answer_nine_third_question);
        answerFourthQuestion = findViewById(R.id.answer_fourth_question);
        firstAnswerFifthQuestion = findViewById(R.id.first_answer_fifth_question);
        secondAnswerFifthQuestion = findViewById(R.id.second_answer_fifth_question);
        thirdAnswerFifthQuestion = findViewById(R.id.third_answer_fifth_question);
        fourthAnswerFifthQuestion = findViewById(R.id.fourth_answer_fifth_question);
        sixAnswer = findViewById(R.id.answers_sixth_question);
        submit = findViewById(R.id.submit_button);

        // Create a Toast with the score when the user presses the submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize and reset user responses to 0
                rightAnswersUser = 0;
                wrongAnswersUser = 0;
                unansweredQuestionsUser = 0;
                // Call the private method that controls the answers
                controlAnswer();
                // Show the message (with a Toast) with the score obtained
                Toast.makeText(getApplicationContext(),messageScore(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void controlAnswer() {
        /*
        * Private method that controls user responses
        * */

        // First question
        if(firstAnswer.getCheckedRadioButtonId() == -1) {
            unansweredQuestionsUser++;
        } else if(R.id.answer_twelve_first_question == firstAnswer.getCheckedRadioButtonId()) {
            rightAnswersUser++;
        } else {
            wrongAnswersUser++;
        }

        // Second question
        if(secondAnswer.getCheckedRadioButtonId() == -1) {
            unansweredQuestionsUser++;
        } else if(R.id.answer_yes_second_question == secondAnswer.getCheckedRadioButtonId()) {
            rightAnswersUser++;
        } else {
            wrongAnswersUser++;
        }

        // Third question
        if(!answerFiveThirdQuestion.isChecked() && !answerTwelveThirdQuestion.isChecked() && !answerSixThirdQuestion.isChecked() && !answerNineThirdQuestion.isChecked()) {
            unansweredQuestionsUser++;
        }
        if(answerFiveThirdQuestion.isChecked()) {
            wrongAnswersUser++;
        }
        // Correct: 12
        if(answerTwelveThirdQuestion.isChecked()) {
            rightAnswersUser++;
        }
        // Correct: 6
        if(answerSixThirdQuestion.isChecked()) {
            rightAnswersUser++;
        }
        if(answerNineThirdQuestion.isChecked()) {
            wrongAnswersUser++;
        }

        // Fourth question
        if(answerFourthQuestion.getText().toString().equals("")) {
            unansweredQuestionsUser++;
        } else if(answerFourthQuestion.getText().toString().equals("4")) {
            rightAnswersUser++;
        } else {
            wrongAnswersUser++;
        }

        // Fifth question
        if(!firstAnswerFifthQuestion.isChecked() && !secondAnswerFifthQuestion.isChecked() && !thirdAnswerFifthQuestion.isChecked() && !fourthAnswerFifthQuestion.isChecked()) {
            unansweredQuestionsUser++;
        }
        // Correct: C-D
        if(firstAnswerFifthQuestion.isChecked()) {
            rightAnswersUser++;
        }
        if(secondAnswerFifthQuestion.isChecked()) {
            wrongAnswersUser++;
        }
        if(thirdAnswerFifthQuestion.isChecked()) {
            wrongAnswersUser++;
        }
        // Correct: E-F#
        if(fourthAnswerFifthQuestion.isChecked()) {
            rightAnswersUser++;
        }

        // Sixth question
        if(sixAnswer.getCheckedRadioButtonId() == -1) {
            unansweredQuestionsUser++;
        } else if(R.id.answer_semitone_sixth_question == sixAnswer.getCheckedRadioButtonId()) {
            rightAnswersUser++;
        } else {
            wrongAnswersUser++;
        }
    }

    private String messageScore() {
        /*
        * Private method that creates the score message
        * @return String containing the message to be inserted in the Toast
        * */

        String messageScore;

        // Right answers
        messageScore = getResources().getString(R.string.right_answers) + " " + rightAnswersUser + "/" + TOTAL_RIGHT_ANSWER + "\n";

        // Wrong answers
        messageScore += getResources().getString(R.string.wrong_answers) + " " + wrongAnswersUser + "/" + TOTAL_RIGHT_ANSWER + "\n";

        // Missing answers
        messageScore += getResources().getString(R.string.unanswered_questions) + " " + unansweredQuestionsUser + "/" + NUMBER_QUESTIONS + "\n";

        // Final message
        if(rightAnswersUser == TOTAL_RIGHT_ANSWER) {
            messageScore += getResources().getString(R.string.compliments);
        } else {
            messageScore += getResources().getString(R.string.try_again);
        }

        return messageScore;
    }


}
