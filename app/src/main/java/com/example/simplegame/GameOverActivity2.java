package com.example.simplegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity2 extends AppCompatActivity {

    private Button startgameagain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over2);
        startgameagain= (Button) findViewById(R.id.play_game_btn);
        startgameagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent mainIntent= new Intent(GameOverActivity2.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}