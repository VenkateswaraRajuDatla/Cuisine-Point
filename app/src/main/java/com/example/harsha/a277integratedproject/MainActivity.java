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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements JsonListResponse{
    int length=0;
    String namearr[]=new String[15];
    String passarr[]=new String[15];
    String usertyp[]= new String[15];
    GetUserForAuth getUserForAuth = new GetUserForAuth();
    String item, Sitem;
    String unamestr,passstr;
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
                if(item.equals("User"))
                {
                    Sitem="N";

                }
                else if(item.equals("Admin"))
                {
                    Sitem="Y";
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
            unamestr = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.TFpassword);
            passstr = b.getText().toString();
            String req[] = new String[2];
            req[0] = "http://cuisine-point-mysql.herokuapp.com/users";
            req[1] = "GET";
            getUserForAuth.delegate=MainActivity.this;
            getUserForAuth.execute(req);
            /*
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

            */
        }
        if(v.getId()==R.id.Bsignup)
        {
            Intent i =new Intent(MainActivity.this,SignUp.class);
            startActivity(i);
        }
    }

    @Override
    public void processFinish(String response) {
        JSONArray jsonArray = null;
        int fres=0;
        try {
            jsonArray = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        length = jsonArray.length();
        JSONObject jsonObject;
        for(int i=0;i<length;i++)
        {
            try {
                jsonObject=jsonArray.getJSONObject(i);
                namearr[i]=jsonObject.getString("username");
                Log.i("username","usernames "+namearr[i]);
                passarr[i]=jsonObject.getString("password");
                Log.i("pass","passwords "+passarr[i]);
                usertyp[i]=jsonObject.getString("is_admin");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<length;i++)
        {
            if(unamestr.equals(namearr[i]) && passstr.equals(passarr[i]) && Sitem.equals(usertyp[i]))
            {
                if(item.equals("User"))
                {
                    fres=1;
                    break;
                }
                else
                {
                    fres=2;
                    break;
                }
            }
        }
        if(fres==1)
        {
            Log.i("Inside","Inside new GoogleRecommendations activity");
            Intent iu =new Intent(MainActivity.this,LoggedInUser.class);
            startActivity(iu);
        }
        else if(fres==2){
            Log.i("Inside","Inside new Admin activity");
            Intent ia =new Intent(MainActivity.this,Admin.class);
            startActivity(ia);
        }
        else {
            Toast temp = Toast.makeText(MainActivity.this,"Username,Password, and user type don't match",Toast.LENGTH_SHORT);
            temp.show();
            Intent sp = new Intent(MainActivity.this,MainActivity.class);
            startActivity(sp);
        }
    }
}
