package com.example.sahelii;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SirenHome extends AppCompatActivity {
    Button start_btn, stop_btn;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siren_home);

        start_btn = findViewById(R.id.start_btn);
        stop_btn = findViewById(R.id.stop_btn);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer==null){
                    mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.police_siren);
                    mediaPlayer.start();
                }

            }
        });

        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                    mediaPlayer=null;
                }
            }
        });

    }
}
