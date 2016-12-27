package com.example.islam.project;

import android.app.DatePickerDialog;
import java.util.Calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {
EditText usernametxt,emailtxt,passtxt,birthdatetxt;
    Button registerbtn ;
    int mYear,mMonth,mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        usernametxt= (EditText)findViewById(R.id.username_txt_reg);
        emailtxt=(EditText)findViewById(R.id.emailtxt_reg);
        passtxt = (EditText)findViewById(R.id.passwordtxt_reg);
        birthdatetxt=(EditText)findViewById(R.id.birthdattxt_reg);
        registerbtn = (Button)findViewById(R.id.registerbtn_reg);
        final UserDbHelper helper = new UserDbHelper(this);



//date picker
        birthdatetxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(registration.this,
            new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
            int monthOfYear, int dayOfMonth) {
            // Display Selected date in textbox
            birthdatetxt.setText(year  + "-"
            + (monthOfYear +1) + "-" + dayOfMonth);
                }
                }, mYear, mMonth, mDay);
        dpd.show();
        }
        }
        }
        );


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,password,birthdate,email;
                username = usernametxt.getText().toString();
                password = passtxt.getText().toString();
                email=emailtxt.getText().toString();
                birthdate= birthdatetxt.getText().toString();
                if(username.equals("")){
                    Toast.makeText( getApplicationContext(), "please enter username " ,Toast.LENGTH_LONG).show();
                }
                else if(password.equals("")){
                    Toast.makeText( getApplicationContext(), "please enter password " ,Toast.LENGTH_LONG).show();
                }
                else if(email.equals("")){
                    Toast.makeText( getApplicationContext(), "please enter email " ,Toast.LENGTH_LONG).show();
                }
               else if(birthdate.equals("")){
                    Toast.makeText( getApplicationContext(), "please enter birthdate " ,Toast.LENGTH_LONG).show();
                }
                else{
                    //your code here
                    helper.CreateNewUser(username,email,password,birthdate);
                    Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(registration.this,Login.class);
                    startActivity(intent);





                }
            }
        });
    }
}
