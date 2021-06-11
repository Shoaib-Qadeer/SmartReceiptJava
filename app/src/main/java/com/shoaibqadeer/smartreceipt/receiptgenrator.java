package com.shoaibqadeer.smartreceipt;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class receiptgenrator extends AppCompatActivity {
    Button button;
    EditText rec_id;
    EditText cust_id;
    EditText Product1_id;
    EditText Product2_id;
    EditText Product3_id;
    EditText Product1_qty;
    EditText Product2_qty;
    EditText Product3_qty;
    TextView total_bill;
    TextView heading;
    TextView lower_note;
    ArrayList<Customer> cust_arr=new ArrayList<>();
    ArrayList<Product> prod_arr=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiptgenrator);

        Log.i("OnCreate", "OnCreate method has been executed");
        button=findViewById(R.id.createpdf);
        rec_id=findViewById(R.id.rec_id);
        cust_id=findViewById(R.id.customer_id);

        Product1_id=findViewById(R.id.product_1);
        Product2_id=findViewById(R.id.product_2);
        Product3_id=findViewById(R.id.product3);
        Product1_qty=findViewById(R.id.prod_quantity1);
        Product2_qty=findViewById(R.id.product_quantity2);
        Product3_qty=findViewById(R.id.prod_qty3);
        total_bill=findViewById(R.id.totalbill);
        heading=findViewById(R.id.heading);

        ActivityCompat.requestPermissions(receiptgenrator.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        Product prd_1=new Product(1,"Science Book","Stationary","Book of Science",200,290);
        Product prd_2=new Product(2,"Book Cover","Stationary","Book Cover",100,30);
        Product prd_3=new Product(3,"Blue Pen","Stationary","Blue Pen by Dollar",500,10);

        Customer c1=new Customer(1,"Shoaib",20,"Home 4,Street 2 ","LHR","923045198375","customer1@gmail.com","abc890");
        Customer c2=new Customer(2,"Shayan",22,"Home 1,Street 9,F8","ISB","923045198375","customer2@gmail.com","a90jsk");
        Customer c3=new Customer(3,"Shoaib",32,"Home 34,Street 22 ","KHI","923045198375","customer3@gmail.com","abc890");
        Customer c4=new Customer(4,"Shayan",19,"Home 21,Street 9,F8","QTA","923045198375","customer4@gmail.com","rc42jsk");



        cust_arr.add(c1);cust_arr.add(c2);cust_arr.add(c3);cust_arr.add(c4);

        prod_arr.add(prd_1);prod_arr.add(prd_2);prod_arr.add(prd_3);



        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                int first_product_id=Integer.parseInt(Product1_id.getText().toString());
                int second_product_id=Integer.parseInt(Product2_id.getText().toString());
                int third_product_id=Integer.parseInt(Product3_id.getText().toString());

                int f_prd_qty=Integer.parseInt(Product1_qty.getText().toString());
                int s_prd_qty=Integer.parseInt(Product2_qty.getText().toString());
                int t_prd_qty=Integer.parseInt(Product3_qty.getText().toString());

                int cust_id_to=Integer.parseInt(cust_id.getText().toString());

                if(cust_id_to==c1.getId() || cust_id_to==c2.getId() || cust_id_to==c3.getId() || cust_id_to==c4.getId()){
                    if(first_product_id==prd_1.getProduct_id() && second_product_id==prd_2.getProduct_id() && third_product_id==prd_3.getProduct_id()){
                        if(f_prd_qty<=prd_1.getProduct_stock() && s_prd_qty<=prd_2.getProduct_stock() && t_prd_qty<=prd_3.getProduct_stock()){
                            prd_1.decbyvalue_stock(f_prd_qty);
                            prd_2.decbyvalue_stock(s_prd_qty);
                            prd_3.decbyvalue_stock(t_prd_qty);
                            double bill=(prd_1.getProduct_price()*f_prd_qty)+(prd_2.getProduct_price()*s_prd_qty)+(prd_3.getProduct_price()*t_prd_qty);
                            total_bill.setText("Total Bill: "+bill);
                            Customer customerRecp=new Customer();
                            for (int i=0;i<=2;i++){
                                if(cust_arr.get(i).getId()==cust_id_to){
                                    customerRecp=cust_arr.get(i);
                                    break;
                                }
                            }


                            createPDF(bill,rec_id.getText().toString(),Product1_id.getText().toString(),Product2_id.getText().toString(),Product3_id.getText().toString(),prd_1.getProduct_name(),prd_2.getProduct_name(),prd_3.getProduct_name(),Product1_qty.getText().toString(),Product2_qty.getText().toString(),Product3_qty.getText().toString(),cust_id.getText().toString(),customerRecp.getName(),customerRecp.getAddress(),customerRecp.email_address,customerRecp.getPhone_num());
                            String send_messg="Dear Mr!"+customerRecp.getName()+"You have checkedout of total PKR "+total_bill+"with Recep#"+rec_id.getText().toString();
                            sendSMS.sendreceiptasSms(customerRecp.getPhone_num(),send_messg);
                            Toast.makeText(receiptgenrator.this, "PDF Created and Details Sent to Customer Phone No", Toast.LENGTH_SHORT).show();


                        }
                        else{
                            Toast.makeText(receiptgenrator.this, "Sorry- Product Stock Not Available", Toast.LENGTH_SHORT).show();   }

                    }
                    else{
                        Toast.makeText(receiptgenrator.this, "Invalid Product Id ", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(receiptgenrator.this, "Invalid Customer Id", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }


    public void createPDF(double p_bill,String p_receipt_id,String f_pid, String s_pid,String t_pid,String f_p_name, String s_p_name, String t_p_name, String f_qty,String s_qty,String t_qty,String rec_custid,String rec_cust_name,String rec_custadd,String cust_email,String cust_num){
        PdfDocument myPdfDocument=new PdfDocument();
        PdfDocument.PageInfo mypageinfo=new PdfDocument.PageInfo.Builder(500,700,1).create();
        PdfDocument.Page myPage=myPdfDocument.startPage(mypageinfo);

        Paint mypaint=new Paint();
        myPage.getCanvas().drawText("Smart Receipt -Receipt#"+p_receipt_id,200,100,mypaint);
        myPage.getCanvas().drawText("Project By: Shayan , Shoaib",200,120,mypaint);
        myPage.getCanvas().drawText("Product Id",100,200,mypaint);
        myPage.getCanvas().drawText("Product Name",200,200,mypaint);
        myPage.getCanvas().drawText("Quantity",350,200,mypaint);
        myPage.getCanvas().drawText("------------------------------------",220,210,mypaint);
        myPage.getCanvas().drawText(f_pid,100,280,mypaint);
        myPage.getCanvas().drawText(f_p_name,200,280,mypaint);
        myPage.getCanvas().drawText(f_qty,350,280,mypaint);
        myPage.getCanvas().drawText(s_pid,100,360,mypaint);
        myPage.getCanvas().drawText(s_p_name,200,360,mypaint);
        myPage.getCanvas().drawText(s_qty,350,360,mypaint);
        myPage.getCanvas().drawText(t_pid,100,440,mypaint);
        myPage.getCanvas().drawText(t_p_name,200,440,mypaint);
        myPage.getCanvas().drawText(t_qty,350,440,mypaint);
        myPage.getCanvas().drawText("------------------------------------",220,460,mypaint);
        myPage.getCanvas().drawText("To",100,480,mypaint);
        myPage.getCanvas().drawText("Cust#"+rec_custid,100,500,mypaint);
        myPage.getCanvas().drawText(rec_cust_name,100,520,mypaint);
        myPage.getCanvas().drawText(rec_custadd,100,540,mypaint);
        myPage.getCanvas().drawText("Email: "+cust_email,100,560,mypaint);
        myPage.getCanvas().drawText("Phone No: "+cust_num,100,580,mypaint);
        myPage.getCanvas().drawText("Total Bill :"+String.valueOf(p_bill),320,600,mypaint);
        myPdfDocument.finishPage(myPage);

        String myfilepath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/Mypdffile"+p_receipt_id+".pdf";
        File myfile=new File(myfilepath);

        try {
            myPdfDocument.writeTo(new FileOutputStream(myfile));
        }  catch (Exception e) {
            e.printStackTrace();
            heading.setText("Error-PDF not created");
        }

        myPdfDocument.close();
    }


}