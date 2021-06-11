package com.shoaibqadeer.smartreceipt;

import android.os.StrictMode;
import android.widget.Toast;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class sendemail {

    public static void sendemail(String cust_eml,String msg){
        final String username="ebn9812@gmail.com";
        final String password="@javaappcode";
        String messagetosend=msg;
        Properties props=new Properties();
        props.put("mail.smtp.auth","ture");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smptp.gmail.com");
        props.put("mail.smtp.port","587");

        Session session=Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cust_eml));
            message.setSubject("Smart Receipt ");
            message.setText(messagetosend);
            Transport.send(message);


        }  catch (MessagingException e) {
            e.printStackTrace();
        }
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }




}
