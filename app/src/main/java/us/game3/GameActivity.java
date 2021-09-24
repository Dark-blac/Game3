package us.game3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener {

    Button buttonObjectChoice1, buttonObjectChoice2, buttonObjectChoice3, buttonBack;
    TextView textObjectPartA, textObjectPartB, textObjectScore;
    int correctAnswer, currentScore = 0, currentLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        textObjectPartA = findViewById(R.id.textPartA);
        textObjectPartB = findViewById(R.id.textPartB);
        textObjectScore = findViewById(R.id.textScore);

        buttonObjectChoice1 = findViewById(R.id.buttonChoice1);
        buttonObjectChoice2 = findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 = findViewById(R.id.buttonChoice3);
        buttonBack = findViewById(R.id.back);

        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

        setQuestion();

    }//onCreate ends here

    @Override
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        int answerGiven = 0;
        switch (view.getId()) {

            case R.id.buttonChoice1:
                //initialize a new int with the value contained in buttonObjectChoice1
                //Remember we put it there ourselves previously
                answerGiven = Integer.parseInt(buttonObjectChoice1.getText().toString());
                break;

            case R.id.buttonChoice2:
                answerGiven = Integer.parseInt(buttonObjectChoice2.getText().toString());
                break;

            case R.id.buttonChoice3:
                answerGiven = Integer.parseInt(buttonObjectChoice3.getText().toString());
                break;
            case R.id.back:
                finish();
                return;
        }

        updateScoreAndLevel(answerGiven);
        setQuestion();

    }

    void setQuestion() {
        int numberRange = currentLevel * 3;
        Random randInt = new Random();

        int partA = randInt.nextInt(numberRange);
        partA++;

        int partB = randInt.nextInt(numberRange);
        partB++;

        correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer - 2;
        int wrongAnswer2 = correctAnswer + 2;

        textObjectPartA.setText(String.valueOf(partA));
        textObjectPartB.setText(String.valueOf(partB));

        //set the multi choice buttons
        //A number between 0 and 2
        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout) {
            case 0:
                buttonObjectChoice1.setText(String.valueOf(correctAnswer));
                buttonObjectChoice2.setText(String.valueOf(wrongAnswer1));
                buttonObjectChoice3.setText(String.valueOf(wrongAnswer2));
                break;

            case 1:
                buttonObjectChoice2.setText(String.valueOf(correctAnswer));
                buttonObjectChoice3.setText(String.valueOf(wrongAnswer1));
                buttonObjectChoice1.setText(String.valueOf(wrongAnswer2));
                break;

            case 2:
                buttonObjectChoice3.setText(String.valueOf(correctAnswer));
                buttonObjectChoice1.setText(String.valueOf(wrongAnswer1));
                buttonObjectChoice2.setText(String.valueOf(wrongAnswer2));
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    void updateScoreAndLevel(int answerGiven) {

        if (isCorrect(answerGiven)) {
            currentScore++;

            if (currentScore > HighScore.getHighScore(this)) {
                HighScore.setHighScore(this, currentScore);
            }

            currentLevel++;
        } else {
            currentScore = 0;
            currentLevel = 1;
        }

        textObjectScore.setText("Score: " + currentScore);
    }

    boolean isCorrect(int answerGiven) {
        boolean correctTrueOrFalse;
        if (answerGiven == correctAnswer) {
            Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_SHORT).show();
            correctTrueOrFalse = true;
        } else {//Uh-oh!
            Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_SHORT).show();
            correctTrueOrFalse = false;
        }

        return correctTrueOrFalse;
    }

}
