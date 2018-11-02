package com.example.harsha.a277integratedproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    String item;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner)findViewById(R.id.user_type);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.user_type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 item = parent.getItemAtPosition(position).toString();
                Log.i("item", "onItemSelected is : "+item);
                if(item.equals("GoogleRecommendations"))
                {
                    Log.i("GoogleRecommendations", "Inside GoogleRecommendations : "+item);
                    Toast.makeText(parent.getContext(),"Selected: "+item,Toast.LENGTH_LONG).show();

                }
                else if(item.equals("Admin"))
                {
                    Log.i("Admin", "Inside Admin: "+item);
                    Toast.makeText(parent.getContext(),"Selected: "+item,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    public void onButtonClick(View v){
        if(v.getId()==R.id.Blogin)
        {
            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.TFpassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);
            if(pass.equals(password) && item.equals("User"))
            {
                Log.i("Inside","Inside new GoogleRecommendations activity");
                Intent i =new Intent(MainActivity.this,LoggedInUser.class);
                //i.putExtra("Username",str);
                startActivity(i);
            }
            else if(pass.equals(password) && item.equals("Admin")){
                Log.i("Inside","Inside new Admin activity");
                Intent i =new Intent(MainActivity.this,Admin.class);
                startActivity(i);
            }
            else {
                Toast temp = Toast.makeText(MainActivity.this,"Username and Password don't match",Toast.LENGTH_SHORT);
                temp.show();
            }

        }
        if(v.getId()==R.id.Bsignup)
        {
            Intent i =new Intent(MainActivity.this,SignUp.class);
            startActivity(i);
        }
    }
}
