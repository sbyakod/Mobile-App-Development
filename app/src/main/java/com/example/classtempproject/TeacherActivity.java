package com.example.classtempproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TeacherActivity extends AppCompatActivity {

    private TextView mTeacherTextView;

    private int mWantTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        mTeacherTextView = (TextView) findViewById(R.id.teacher_temp);

        mWantTemp = getIntent().getIntExtra("TEMP",0);

        mTeacherTextView.setText("The students want a temperature of " + mWantTemp + " degrees.");
    }
}
