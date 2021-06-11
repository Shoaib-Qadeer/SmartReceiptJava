package com.shoaibqadeer.smartreceipt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class trackitem extends AppCompatActivity {
    TextView low_stk;
    TextView total_categ;
    TextView total_prd;
    TextView out_of_stk;
    TextView in_hand;
    TextView to_be_rcv;
    Button track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackitem);

        low_stk=findViewById(R.id.lowstockitem);
        total_categ=findViewById(R.id.total_categ);
        total_prd=findViewById(R.id.total_prodcut);
        out_of_stk=findViewById(R.id.outofstock);
        in_hand=findViewById(R.id.quant_inhand);
        to_be_rcv=findViewById(R.id.quant_torecv);

        track=findViewById(R.id.trackallitems);

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                low_stk.setText("0");
                total_categ.setText("1");
                total_prd.setText("3");
                out_of_stk.setText("0");
                in_hand.setText("1500");
                to_be_rcv.setText("290");


            }
        });


    }
}