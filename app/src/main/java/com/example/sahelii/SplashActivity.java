package com.example.sahelii;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                SharedPreferences sharedPreferences=getSharedPreferences(LoginActivity.PREFS_NAME,0);
                boolean hasLoggedIn=sharedPreferences.getBoolean("hasLoggedIn",false);

                if(hasLoggedIn==true){
                    Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        }, 2000);
    }


}