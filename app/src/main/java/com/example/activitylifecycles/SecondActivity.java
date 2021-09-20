package com.example.activitylifecycles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String SECOND_ACTIVITY_USER_INPUT = "SECOND_ACTIVITY_USER_INPUT";
    public static final int OPEN_THIRD_ACTIVITY = 3003;

    private TextView mResultTv;
    private EditText mSendBackEt;
    private Button mClickMeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mResultTv = (TextView) findViewById(R.id.result_tv);
        mSendBackEt = (EditText) findViewById(R.id.send_back_et);
        mClickMeBtn = (Button) findViewById(R.id.click_me_btn);

        //now we have to extract the data we get from our intent
        //so..how do?
        //we check if theres an existing intent
        Intent recievedIntent = getIntent();
        if (recievedIntent != null) {
            String userInput = recievedIntent.getStringExtra(MainActivity.USER_TEXT);
            mResultTv.setText(userInput);
        }

        mClickMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RETRIEVING DATA BACK FROM STARTED ACTIVITY STEP 3:
                //SEND data back to the original activity
                Intent replyIntent = new Intent();
                String userInput = mSendBackEt.getText().toString();
                replyIntent.putExtra(SECOND_ACTIVITY_USER_INPUT, userInput);

                //RETRIEVING DATA BACK FROM STARTED ACTIVITY STEP 4:
                //DECIDE WHICH RESULT YOU WANT THIS TO BE:
                setResult(MainActivity.EDIT_TEXT_CHANGED_RESULT, replyIntent);

                //RETRIEVING DATA BACK FROM STARTED ACTIVITY STEP 5:
                //MAKE SURE YOU CLOSE OUT YOUR STARTED ACTIVITY
                //the below method calls the activity's onDestroy() method
                finish();
            }
        });

        mResultTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thirdIntent = new Intent(SecondActivity.this, ThirdExampleActivity.class);
                startActivityForResult(thirdIntent, OPEN_THIRD_ACTIVITY);
            }
        });
    }

    //EXAMPLE OF CASCADING FROM THIRD ACTIVITY ALL THE WAY TO MAIN ACTIVITY
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_THIRD_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}

