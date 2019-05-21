package com.example.classtempproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class StudentActivity extends AppCompatActivity {

    private Button mColderButton;
    private Button mWarmerButton;

    private int mTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mColderButton = (Button) findViewById(R.id.colder_button);
        mWarmerButton = (Button) findViewById(R.id.warmer_button);

        mTemp = getIntent().getIntExtra("TEMP",0);

    }
}
