package com.example.sahelii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_aboutus);
        Button button=findViewById (R.id.contactUs);
        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                composeEmail();
            }
        });
    }
    public void composeEmail() {
        String subject="Contacting for Sahelii";
        String mailto = "mailto:suhasini150404@gmail.com" +
                "?cc="+"sanuhingalkar2004@gmail.com"+
                "&bcc="+"saikulkarni1453@gmail.com"+
                "&bcc="+"kanchan.jaiswal2005@gmail.com"+
                "&subject=" + Uri.encode(subject);


        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }


    }
}
