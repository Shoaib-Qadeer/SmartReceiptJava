package com.shoaibqadeer.smartreceipt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Retailermain extends AppCompatActivity {
    Button generaterecipt;
    Button sendnotifications1;
    Button sendnotifications2;
    Button viewcustomers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailermain);
        generaterecipt=findViewById(R.id.generaterec);
        sendnotifications1=findViewById(R.id.sendnotfi1);
        sendnotifications2=findViewById(R.id.sendnotfi2);
        viewcustomers=findViewById(R.id.trackitem);

        generaterecipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Retailermain.this,receiptgenrator.class);
                startActivity(intent);
            }
        });

      sendnotifications1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categoryintent1 = new Intent(Retailermain.this, notify_main.class);
                startActivity(categoryintent1);
            }
        });

      sendnotifications2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categoryintent2 = new Intent(Retailermain.this, notification_firebase.class);
                startActivity(categoryintent2);
            }
        });

        viewcustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categoryintent2 = new Intent(Retailermain.this, trackitem.class);
                startActivity(categoryintent2);
            }
        });
    }


}