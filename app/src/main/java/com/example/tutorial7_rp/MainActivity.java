package com.example.tutorial7_rp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword,email;
    Button btnSignUP,btnSignIn;
    DBHelper sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.usrname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        btnSignIn=findViewById(R.id.btnSignIn);
        btnSignUP=findViewById(R.id.btnSignup);

        sqLiteDatabase= new DBHelper(this);

        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String e = email.getText().toString();
                if (user.equals("") || pass.equals("") || repass.equals("") || e.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Please enter value",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(repass)){
                        Boolean usercheckresult =sqLiteDatabase.checkusername(user);
                        if (usercheckresult==false){
                            Boolean regresult = sqLiteDatabase.insertData(user,pass,e);
                            if (regresult==true){
                                Toast.makeText(MainActivity.this,"Restration successfull",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),loginactivity.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Registration Faild",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this,"User Already Exist. \n Please SignIn",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Password Dosen't Match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),loginactivity.class);
                startActivity(i);
            }
        });
    }
}