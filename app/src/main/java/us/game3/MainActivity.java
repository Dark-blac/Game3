package us.game3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity{

     public static int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = this.getSharedPreferences("SHARED_KEY", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();



        editor.apply();

      if (!(score <= prefs.getInt("score", 0))){
          score = prefs.getInt("score", 0);
      }


        //play
        findViewById(R.id.buttonPlay).setOnClickListener(view ->  startActivity(new Intent(this, GameActivity.class)));

        //quit
        findViewById(R.id.Quit).setOnClickListener(view -> finish());

        //scores
        findViewById(R.id.buttonScores).setOnClickListener(view -> Toast.makeText(this, "highest score"+score, Toast.LENGTH_SHORT).show());
    }
}






