package com.example.harsha.a277integratedproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends Activity implements JsonListResponse{
    GetUserList getUserList = new GetUserList();
    DatabaseHelper helper = new DatabaseHelper(this);
    String selected_usertype;
    String Is_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Spinner sp = (Spinner) findViewById(R.id.signup_user_type);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_usertype = parent.getItemAtPosition(position).toString();
                Log.i("item", "onItemSelected is : " + selected_usertype);
                if (selected_usertype.equals("User")) {
                    Is_admin="N";

                } else if (selected_usertype.equals("Admin")) {
                    Is_admin="Y";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onSignUpClick(View v)
    {
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
            //String usertypestr = user_type.getText().toString();

            if(!namestr.isEmpty() && !emailstr.isEmpty() && !unamestr.isEmpty() && !pass1str.isEmpty() && !phonestr.isEmpty())
            {
                Log.i("fields","All fields are filled");
                if(!pass1str.equals(pass2str))
                {
                    Toast pass = Toast.makeText(SignUp.this,"Passwords don't match",Toast.LENGTH_SHORT);
                    pass.show();
                }
                else
                {
                    String req[] = new String[9];
                    req[0] = "http://cuisine-point-mysql.herokuapp.com/users/add";
                    req[1] = "POST";
                    req[2] = "user";
                    req[3] = namestr;
                    req[4] = emailstr;
                    req[5] = unamestr;
                    req[6] = pass1str;
                    req[7] = phonestr;
                    req[8] = Is_admin;
                    getUserList.delegate=SignUp.this;
                    getUserList.execute(req);

                    /* Contact c =new Contact();
                    c.setEmail(emailstr);
                    c.setName(namestr);
                    c.setPass(pass1str);
                    c.setUname(unamestr);
                    helper.insertContact(c);
                    Toast pass = Toast.makeText(SignUp.this,"Account created successfully",Toast.LENGTH_SHORT);
                    pass.show(); */
                }
            }
            else
            {
                Toast.makeText(SignUp.this, "All fields are required",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void processFinish(String response) {
        Toast pass = Toast.makeText(SignUp.this,"Account created successfully",Toast.LENGTH_SHORT);
        finish();

    }
}
