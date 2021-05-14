package com.example.chaayas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
public class RegisterActivity extends AppCompatActivity {
    EditText username,email,password1,password2;
    Button btnregister,btnloginup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username= findViewById(R.id.username);
        email=findViewById(R.id.email);
        password1=findViewById(R.id.password1);
        password2= findViewById(R.id.password2);
        btnregister = findViewById(R.id.btnlogin);
        btnloginup=findViewById(R.id.btnLoginup);
        dbhelpher myDB =  new dbhelpher(this);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String emailx = email.getText().toString();
                String pass = password1.getText().toString();
                String repass = password2.getText().toString();
                if(username.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password1.getText().toString().isEmpty() || password2.getText().toString().isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Please enter all Detials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password1.getText().toString().equals(password2.getText().toString()))
                    {
                        Boolean usercheckResult = myDB.checkusername(user);
                        if(usercheckResult==false)
                        {
                            Boolean regResult = myDB.insertData(emailx,user,pass);
                        if(regResult==true)
                        {
                            Toast.makeText(RegisterActivity.this,"Registration Successful.",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                                Toast.makeText(RegisterActivity.this,"Registration Failed.",Toast.LENGTH_SHORT).show();
                        }
                        }
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"Please make sure your password are same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnloginup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}