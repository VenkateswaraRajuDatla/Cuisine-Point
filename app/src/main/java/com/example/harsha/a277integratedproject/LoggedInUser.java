package com.example.harsha.a277integratedproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoggedInUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_user);
    }

    public void onclickshow(View v){
        Intent i=new Intent(LoggedInUser.this,GoogleRecommendations.class);
        startActivity(i);


    }
}
