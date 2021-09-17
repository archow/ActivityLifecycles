package com.example.activitylifecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String USER_TEXT = "USER_TEXT";

    private TextView mHelloTV;
    private EditText mUserInput;
    private Button mStartActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloTV = (TextView) findViewById(R.id.hello_tv);
        mUserInput = (EditText) findViewById(R.id.user_input);
        mStartActivityBtn = (Button) findViewById(R.id.start_activity_btn);

        mStartActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we want to pass some data from this activity to our second activity
                //so... how do?
                //First we'll need to create an Intent..
                //two types of Intents:
                //Explicit Intent - specifies the name of the Activity (or component) we want to start
                //Implicit Intent - does not specify the name... so how does the Android System know which
                //activity or component to open?

                //let's use explicit intent here
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                String userText = mUserInput.getText().toString();
                //but we also wanna pass some data...how do?
                intent.putExtra(USER_TEXT, userText);

                //now we start our activity by passing the intent object
                startActivity(intent);

                //but how do we get a result back ffrom our activity that we started
            }
        });
    }
}
