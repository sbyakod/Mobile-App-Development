package com.example.classtempproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mStudentButton;
    private Button mTeacherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStudentButton = (Button) findViewById(R.id.student_button);
        mTeacherButton = (Button) findViewById(R.id.teacher_button);

        mStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this, StudentActivity.class);
                // j.putExtra("SCORE", mScore);
                startActivity(j);
            }
        });

        mTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this, TeacherActivity.class);
               // j.putExtra("SCORE", mScore);
                startActivity(j);
            }
        });
    }
}
