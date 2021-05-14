package com.example.chaayas;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import  android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

import  javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import  javax.crypto.spec.SecretKeySpec;
public class chat_activity extends AppCompatActivity {
    private EditText editText;
    private ListView listView;
    private DatabaseReference databaseReference;
    private  String stringMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity);
        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);

            try{
                databaseReference = FirebaseDatabase.getInstance().getReference("Message");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        stringMessage = dataSnapshot.getValue().toString();
                        stringMessage = stringMessage.substring(1,stringMessage.length()-1);

                        String[] stringMessageArray = stringMessage.split(", ");
                        Arrays.sort(stringMessageArray);
                        String[] stringFinal = new String[stringMessageArray.length];

                        try {
                            for (int i = 0; i<stringMessageArray.length; i++) {
                                String[] stringKeyValue = stringMessageArray[i].split("=", 2);
                                stringFinal[i] = (String) android.text.format.DateFormat.format("dd-MM-yyyy hh:mm:ss", Long.parseLong(stringKeyValue[0]));
                                stringFinal[i] = stringFinal[i]+"\n\n"+stringKeyValue[1]+"\n";

                            }


                            listView.setAdapter(new ArrayAdapter<String>(chat_activity.this, android.R.layout.simple_list_item_1, stringFinal));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        public void sendButton(View view) {
            if(editText.getText().toString().equals(""))
            {
                Toast.makeText(chat_activity.this,"Text is empty",Toast.LENGTH_SHORT).show();
            }
            else
            {
            Date date = new Date();
            databaseReference.child(Long.toString(date.getTime())).setValue(editText.getText().toString());
            editText.setText("");

        }}
    }