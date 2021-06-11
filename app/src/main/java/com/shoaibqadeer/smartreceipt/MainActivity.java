package com.shoaibqadeer.smartreceipt;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText email;
    EditText passwd;
    TextView hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.inpemail);
        passwd=findViewById(R.id.inpass);
        login=findViewById(R.id.login);
        hint=findViewById(R.id.hint);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminemail="admin@gmail.com";
                String adminphone="03045198375";
                String password="admin123";
                if(email.getText().toString().equals(adminemail) || email.getText().toString().equals(adminphone)){
                    if(passwd.getText().toString().equals(password)){
                        Intent intent=new Intent(MainActivity.this,Retailermain.class);
                        startActivity(intent);
                    }
                    else{
                        hint.setText("Invalid Password");    }
                }
                else{
                    hint.setText("Invalid Admin Info");
                }


            }
        });

    }

}