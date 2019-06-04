package com.example.classtempproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.Button;

public class StudentPreActivity extends AppCompatActivity {

    private Spinner school_spinner;
    private Spinner teacher_spinner;
    private Spinner period_spinner;
    private Spinner student_spinner;

    private Button mNextButton;
    private int mTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pre);

        mTemp = getIntent().getIntExtra("TEMP", 0);

        school_spinner = (Spinner) findViewById(R.id.schools_view_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> school_adapter = ArrayAdapter.createFromResource(this, R.array.schools_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        school_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        school_spinner.setAdapter(school_adapter);

        teacher_spinner = (Spinner) findViewById(R.id.teachers_view_spinner);
        ArrayAdapter<CharSequence> teacher_adapter = ArrayAdapter.createFromResource(this, R.array.teachers_array, android.R.layout.simple_spinner_item);
        teacher_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teacher_spinner.setAdapter(teacher_adapter);

        period_spinner = (Spinner) findViewById(R.id.periods_view_spinner);
        ArrayAdapter<CharSequence> period_adapter = ArrayAdapter.createFromResource(this, R.array.periods_array, android.R.layout.simple_spinner_item);
        period_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        period_spinner.setAdapter(period_adapter);

        student_spinner = (Spinner) findViewById(R.id.students_view_spinner);
        ArrayAdapter<CharSequence> student_adapter = ArrayAdapter.createFromResource(this, R.array.students_array, android.R.layout.simple_spinner_item);
        student_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        student_spinner.setAdapter(student_adapter);

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                /*Intent i = new Intent(StudentPreActivity.this, StudentActivity.class);
                i.putExtra("TEMP", mTemp);
                startActivityForResult(i, 1);*/
            }
        });
    }
}