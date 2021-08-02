package com.example.sendemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText email , subject , message;
    Button send;
    String EMAIL , SUBJECT , MESSAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean isOnline = isOnline();

       if (isOnline){
           Toast.makeText(getApplicationContext() , "Online" , Toast.LENGTH_SHORT).show();
       }else {
           View parentLayout = findViewById(android.R.id.content);
           Snackbar.make(parentLayout, "No Internet Connection...", Snackbar.LENGTH_LONG)
                   .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                   .show();
       }



        email = findViewById(R.id.emailId);
        subject = findViewById(R.id.subjectId);
        message = findViewById(R.id.messageId);
        send = findViewById(R.id.sendId);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sendEmail();
            }
        });
//        clearEmail();
    }

    private void clearEmail() {
        email.setText("");
        subject.setText("");
        message.setText("");
    }


    private void sendEmail() {
        EMAIL = email.getText().toString();
        SUBJECT = subject.getText().toString();
        MESSAGE = message.getText().toString();

        Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
        selectorIntent.setData(Uri.parse("mailto:"));

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
        emailIntent.putExtra(Intent.EXTRA_TEXT, MESSAGE);
        emailIntent.setSelector( selectorIntent );
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

//        Intent sendMail = new Intent(Intent.ACTION_SEND);
//        sendMail.setType("*/*");
//        sendMail.setType("message/html");
//        sendMail.setPackage("com.google.android.gm");
//        sendMail.putExtra(Intent.EXTRA_EMAIL , email);
//        sendMail.putExtra(Intent.EXTRA_SUBJECT , subject);
//        sendMail.putExtra(Intent.EXTRA_TEXT , message);
//        startActivity(Intent.createChooser(sendMail , "Choose an Email Client"));
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


}

/*

I also face this problem be clear with those ```string``` value is not null.
you could use ```ACTION.SENDTO```. Use the below code its work for me

        Intent sendMail = new Intent(Intent.ACTION_SENDTO);
        sendMail.setData(Uri.parse("mailto:"));
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setSelector( sendMail );
        startActivity(Intent.createChooser(intent, "Choose an Email Client"));

 */