package com.example.activitylifecycles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String USER_TEXT = "USER_TEXT";
    public static final int MAIN_ACTIVITY_REQUEST = 1001;
    public static final int EDIT_TEXT_CHANGED_RESULT = 2002;

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

                /**
                //Example of Implicit intents:
                Intent implicitIntent = new Intent();
                //What type of activity do you want to open/start?
                //set action
                implicitIntent.setAction(Intent.ACTION_CALL);
                 */

                Uri uri = Uri.parse("https://www.google.com");
                Intent exampleImplicitIntent = new Intent(Intent.ACTION_VIEW, uri);

                //How do the apps know which intents to respond to?
                //how does a phone app know to respond to that ^ action?

                String userText = mUserInput.getText().toString();
                //but we also wanna pass some data...how do?
                intent.putExtra(USER_TEXT, userText);

                //now we start our activity by passing the intent object
                //startActivity(intent);
                //startActivity(exampleImplicitIntent);

                //but how do we get a result back ffrom our activity that we started
                //RETRIEVING DATA BACK FROM STARTED ACTIVITY STEP 1:
                //use startActivityForResult
                //pass in the intent (like normal), and the RESULT CODE
                //REQUEST CODE = specifies which activity starts the second activity
                startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);
            }
        });
    }

    //RETRIEVING DATA BACK FROM STARTED ACTIVITY STEP 2:
    //override the method onActivityResult

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //result code specifies which kind of action we're responding to from the started
        //activity
        //Default results: RESULT_OK, RESULT_CANCELLED

        //first, indicate which RequestCode you're responding to
        if (requestCode == MAIN_ACTIVITY_REQUEST) {
            //Now indicate which RESULT you're responding to
            switch (resultCode) {
                case EDIT_TEXT_CHANGED_RESULT:
                    //here we'll get the intent back from our second activity
                    if (data != null) {
                        String secondActivityUserInput = data
                                .getStringExtra(SecondActivity.SECOND_ACTIVITY_USER_INPUT);
                        mHelloTV.setText(secondActivityUserInput);
                        Toast.makeText(this,
                                "Got data back from second activity!",
                                Toast.LENGTH_LONG).show();
                    }
                    break;
                case RESULT_OK:
                    //DO something here if we want
                    mHelloTV.setText(R.string.third_activity_returned_text);
                    break;
                case RESULT_CANCELED:
                    ////DO something here if we want
                    break;
                default:
                    Toast.makeText(this,
                            "No started activity found",
                            Toast.LENGTH_LONG).show();
            }
        }
    }
}
