package com.example.tutorial7_rp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {

    EditText username,passsword;
    Button btnLogin;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_LoginActivity);

        username=findViewById(R.id.usrname);
        passsword=findViewById(R.id.password);
        btnLogin=findViewById(R.id.btnSignIn);

        myDB=new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=passsword.getText().toString();

                if (user.equals("") || pass.equals("")){
                    Toast.makeText(loginactivity.this,"Please Enter The Credentials",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean result = myDB.checkusernamePassword(user,pass);
                    if (result==true){
                        Intent i = new Intent(loginactivity.this,homeactivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(loginactivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}