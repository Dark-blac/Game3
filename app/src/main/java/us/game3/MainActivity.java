package us.game3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //play
        findViewById(R.id.buttonPlay).setOnClickListener(view -> startActivity(new Intent(this, GameActivity.class)));

        //quit
        findViewById(R.id.Quit).setOnClickListener(view -> finish());

        //scores
        findViewById(R.id.buttonScores).setOnClickListener(view -> {
            final String text = "highest score: " + HighScore.getHighScore(this);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        });
    }
}






