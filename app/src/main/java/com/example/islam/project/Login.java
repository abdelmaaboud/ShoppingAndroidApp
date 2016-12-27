package com.example.islam.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText emailtxt ,passwordtxt;
    Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailtxt = (EditText) findViewById(R.id.email_txt_login);
        passwordtxt=(EditText)findViewById(R.id.password_txt_login);
        loginbtn = (Button)findViewById(R.id.loginbtn_login);
        ////
        final UserDbHelper helper = new UserDbHelper(this);

        /*
        helper.addProduct("samsung galaxy",2000 ,20 ,1);
        helper.addProduct("nokia x", 800,18 ,1);
        helper.addProduct("sony z3", 3000,15 ,1);
        helper.addProduct("iphone 6", 7000,22 ,1);
        helper.addProduct("toshiba", 15000, 14,2);
        helper.addProduct("mac pro", 14000, 16,2);
        helper.addProduct("dell", 16000, 17,2);
        helper.addProduct("lenovo", 16000, 13,2);
        helper.addProduct("canon", 19000, 18,3);
        helper.addProduct("nikon", 13000, 12,3);
        helper.addProduct("sony",12500 , 19,3);




        helper.addCategory("phones");
        helper.addCategory("laptops");
        helper.addCategory("cameras");
        helper.CreateNewUser("islam","islam@yahoo.com","islam","1993-01-10");
        helper.CreateNewUser("ahmed","ahmed@yahoo.com","ahmed","1995-12-30");
        helper.CreateNewUser("mohamed","mohamed@yahoo.com","mohamed","1993-05-01");
        helper.CreateNewUser("omar","omar@yahoo.com","omar","1995-12-26");
        */

        ///

        Toast.makeText(getApplicationContext(),String.valueOf( helper.getcountuser()) ,Toast.LENGTH_LONG).show();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =  emailtxt.getText().toString();
                String pass= passwordtxt.getText().toString();

                if(  email.equals("") || pass.equals("")){
                    Toast.makeText( getApplicationContext(), "please enter Email and password" ,Toast.LENGTH_LONG).show();

                }
                else{
                    //code here
                    int userid=helper.Checkuser(email,pass);

                    if (userid!= 0){
                        Cart cart = new Cart(userid);
                        Intent intent = new Intent(Login.this,categories.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Wrong" ,Toast.LENGTH_LONG).show();

                    }


                }
            }
        });

    }
}
