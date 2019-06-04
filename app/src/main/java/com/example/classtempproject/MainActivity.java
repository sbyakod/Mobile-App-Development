package com.example.classtempproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button mStudentButton;
    private Button mTeacherButton;
    private TextView mCurrentTempTextView;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private int mCurrentMonth;
    private int mTemp;
    private int mWantTemp;
    private int mStudentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("current_temp");

        mStudentLogin = 0;
        mStudentButton = (Button) findViewById(R.id.student_button);
        mTeacherButton = (Button) findViewById(R.id.teacher_button);
        mCurrentTempTextView = (TextView) findViewById(R.id.current_temp);

        Calendar cal = Calendar.getInstance();
        mCurrentMonth = cal.get(Calendar.MONTH) + 1;

        if(mCurrentMonth >= 4 && mCurrentMonth < 10)
            mTemp = 70;
        else
            mTemp = 60;

        mWantTemp = mTemp;
        myRef.setValue(mTemp);

        mCurrentTempTextView.setText("The current temperature is: " + mTemp + " degrees and the students want it to be " + mWantTemp + " degrees.");

        mStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mStudentLogin > 0) {
                    Intent j = new Intent(MainActivity.this, StudentActivity.class);
                    j.putExtra("TEMP", mTemp);
                    startActivityForResult(j, 1);
                }
                else{
                    mStudentLogin = 1;
                    Intent j = new Intent(MainActivity.this, StudentPreActivity.class);
                    j.putExtra("TEMP", mTemp);
                    startActivityForResult(j, 1);
                }
            }
        });

        mTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MainActivity.this, TeacherActivity.class);
                j.putExtra("TEMP", mWantTemp);
                startActivityForResult(j, 2);
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);
                mTemp = value;
                mCurrentTempTextView.setText("The current temperature is: " + mTemp + " degrees and the students want it to be " + mWantTemp + " degrees.");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == 1){
                mWantTemp = data.getIntExtra("result", 0);
                mCurrentTempTextView.setText("The current temperature is: " + mTemp + " degrees and the students want it to be " + mWantTemp + " degrees.");
            }
        }
        if (requestCode == 2) {
            if(resultCode == 2){
                mTemp = data.getIntExtra("result", 0);
                mCurrentTempTextView.setText("The current temperature is: " + mTemp + " degrees and the students want it to be " + mWantTemp + " degrees.");
            }
        }
    }
}
