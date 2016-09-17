package com.example.trannh08.ad014sendingemail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private final String DEBUG_TAG = "DEBUG TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_composeEmail = (Button) findViewById(R.id.button_composeEmail);
        button_composeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeEmail();
            }
        });
    }

    protected void composeEmail() {
        Log.d(DEBUG_TAG, "Begin composeEmail()");

        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        currentDate.setTimeZone(TimeZone.getTimeZone("GMT"));

        String[] mailTo = {""};
        String[] mailCc = {""};
        String mailSubject = "Test AD014SendingEmail";
        String mailContent = "This is the content. Email was sent at " + currentDate;
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, mailTo);
        intent.putExtra(Intent.EXTRA_CC, mailCc);
        intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, mailContent);

        try {
            startActivity(Intent.createChooser(intent, "Please choose an application to compose email..."));
            Toast.makeText(this, "Moving to email composer.", Toast.LENGTH_SHORT).show();
            finish();
            Log.d(DEBUG_TAG, "Finish action compose email.");
        } catch (Exception ex) {
            Toast.makeText(this, "Cannot perform this action right now.", Toast.LENGTH_SHORT).show();
            Log.d(DEBUG_TAG, ex.getMessage());
            Log.d(DEBUG_TAG, ex.getStackTrace().toString());
        }
    }
}
