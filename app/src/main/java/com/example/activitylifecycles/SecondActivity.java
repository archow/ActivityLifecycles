package com.example.activitylifecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView mResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mResultTv = (TextView) findViewById(R.id.result_tv);

        //now we have to extract the data we get from our intent
        //so..how do?
        //we check if theres an existing intent
        Intent recievedIntent = getIntent();
        if (recievedIntent != null) {
            String userInput = recievedIntent.getStringExtra(MainActivity.USER_TEXT);
            mResultTv.setText(userInput);
        }
    }
}