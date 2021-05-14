 package com.example.chaayas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

 public class LoginActivity extends AppCompatActivity {
   EditText username,password1;
   Button btnlogin, btnsignup;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username= findViewById(R.id.username);
        password1= findViewById(R.id.password1);

        btnsignup= findViewById(R.id.btnsignup);
        btnlogin= findViewById(R.id.btnlogin);

        dbhelpher myDB =  new dbhelpher(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password1.getText().toString();
                if(user.equals("") || pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Please enter the credentials",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result =  myDB.checkusernamePassword(user,pass);
                    if(result==true)
                    {
                        Toast.makeText(LoginActivity.this,"Valid Credentials",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,Navigation.class);
                        intent.putExtra("name_key", user);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
         btnsignup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                 startActivity(intent);
                 finish();
             }
         });
    }
}