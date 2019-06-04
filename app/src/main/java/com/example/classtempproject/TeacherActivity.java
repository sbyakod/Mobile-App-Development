package com.example.classtempproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class TeacherActivity extends AppCompatActivity {

    private Button mAcceptButton;
    private Button mRejectButton;

    private TextView mTeacherTextView;

    private int mWantTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        mAcceptButton = (Button) findViewById(R.id.accept_button);
        mRejectButton = (Button) findViewById(R.id.reject_button);

        mTeacherTextView = (TextView) findViewById(R.id.teacher_temp);

        mWantTemp = getIntent().getIntExtra("TEMP",0);

        mTeacherTextView.setText("The students want a temperature of " + mWantTemp + " degrees.");

        mAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                returnIntent.putExtra("result", mWantTemp);
                setResult(2, returnIntent);
                finish();
            }
        });

        mRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
