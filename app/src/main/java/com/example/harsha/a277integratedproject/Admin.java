package com.example.harsha.a277integratedproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity implements HttpResponse {
    EditText restrauntNameText,addressText, cityText, cuisineText, phoneNumberText;
    Button addRestarauntButton, editRestarauntButton, showRestaurantsButton,getRecommendationsButton;
    HttpUrlRequest httpUrlRequest=new HttpUrlRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        restrauntNameText         = findViewById(R.id.hotelName);
        addressText             = findViewById(R.id.address);
        cityText          = findViewById(R.id.city);
        cuisineText          = findViewById(R.id.cuisine);
        phoneNumberText       = findViewById(R.id.phoneNumber);
        addRestarauntButton       = findViewById(R.id.addHotel);
        editRestarauntButton = findViewById(R.id.updateHotel);
        showRestaurantsButton     = findViewById(R.id.allHotels);
        getRecommendationsButton =findViewById(R.id.recommendations);
        httpUrlRequest.delegate = this;

        addRestarauntButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String request[] = new String[9];
                request[0] = "http://cuisine-point-mysql.herokuapp.com/restaurants/add";
                request[1] = "POST";
                request[2] = "restaurant";
                request[3] = restrauntNameText.getText().toString();
                request[4] = "jsmith";
                request[5] = addressText.getText().toString();
                request[6] = cityText.getText().toString();
                request[7] = cuisineText.getText().toString();
                request[8] = phoneNumberText.getText().toString();
                sendRequest(request);
            }
        });

        editRestarauntButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String request[] = new String[9];
                request[0] = "http://cuisine-point-mysql.herokuapp.com/restaurants/update";
                request[1] = "POST";
                request[2] = "restaurant";
                request[3] = restrauntNameText.getText().toString();
                request[4] = "jsmith";
                request[5] = addressText.getText().toString();
                request[6] = cityText.getText().toString();
                request[7] = cuisineText.getText().toString();
                request[8] = phoneNumberText.getText().toString();
                sendRequest(request);
            }
        });

        showRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String request[] = new String[2];
                request[0] = "http://cuisine-point-mysql.herokuapp.com/restaurants";
                request[1] = "GET";

                sendRequest(request);
            }
        });

        getRecommendationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String request[] = new String[9];
                request[0] = "http://cuisine-point-mysql.herokuapp.com/restaurants/cuisine";
                request[1] = "POST";
                request[2] = "getByCuisine";
                request[3] = "american";
                sendRequest(request);
            }
        });
    }

    public void sendRequest(String ...request) {
        httpUrlRequest = new HttpUrlRequest();
        httpUrlRequest.delegate = Admin.this;
        httpUrlRequest.execute(request);

    }

    @Override
    public void getResponse(String response) {
        try {
            Log.v("Users List MainActivity", response);

            if(response.equals("{\"message\":\"Restaurant added\"}"))
            {
                Toast.makeText(getApplicationContext(),"Restaurant added", Toast.LENGTH_LONG).show();
            }
            else if(response.equals("{\"message\":\"Restaurant updated\"}"))
            {
                Toast.makeText(getApplicationContext(),"Restaurant updated", Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent(this, AdminListActivity.class);

                intent.putExtra("EXTRA_MESSAGE", response);
                startActivity(intent);
                finish();
            }
        }
        catch(Exception ex){
            Log.e("sdcard-err2:",ex.getMessage());  //  null pointer exception : println needs a message
        }
    }
}

