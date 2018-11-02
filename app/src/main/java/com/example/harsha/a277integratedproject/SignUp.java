package com.example.harsha.a277integratedproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }
    public void onSignUpClick(View v){
        if(v.getId()==R.id.Bsignupbutton)
        {
            EditText name =(EditText) findViewById(R.id.TFname);
            EditText email =(EditText) findViewById(R.id.TFemail);
            EditText uname =(EditText) findViewById(R.id.TFuname);
            EditText pass1 =(EditText) findViewById(R.id.TFpass1);
            EditText pass2 =(EditText) findViewById(R.id.TFpass2);
            EditText phone = (EditText)findViewById(R.id.TFPhone);
            EditText user_type = (EditText)findViewById(R.id.TFuser_type);




            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();
            String phonestr = phone.getText().toString();
            String usertypestr = user_type.getText().toString();

            if(!pass1str.equals(pass2str))
            {
                Toast pass = Toast.makeText(SignUp.this,"Passwords don't match",Toast.LENGTH_SHORT);
                pass.show();
            }
            else
            {
                Contact c =new Contact();
                c.setEmail(emailstr);
                c.setName(namestr);
                c.setPass(pass1str);
                c.setUname(unamestr);
                helper.insertContact(c);
                Toast pass = Toast.makeText(SignUp.this,"Account created successfully",Toast.LENGTH_SHORT);
                pass.show();
            }
        }
    }
}
