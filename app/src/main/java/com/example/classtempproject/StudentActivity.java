package com.example.classtempproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class StudentActivity extends AppCompatActivity {

    private Button mColderButton;
    private Button mWarmerButton;
    private Button mSubmitButton;
    private TextView mStudentTempTextView;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private int mWantTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("want_temp");

        mColderButton = (Button) findViewById(R.id.colder_button);
        mWarmerButton = (Button) findViewById(R.id.warmer_button);
        mSubmitButton = (Button) findViewById(R.id.submit_button);

        mStudentTempTextView = (TextView) findViewById(R.id.temp_textview);

        mWantTemp = getIntent().getIntExtra("TEMP",0);
        myRef.setValue(mWantTemp);

        mStudentTempTextView.setText("We want a temperature of "+ mWantTemp + " degrees.");

        mColderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWantTemp = mWantTemp - 2;
                mStudentTempTextView.setText("We want a temperature of "+ mWantTemp + " degrees.");
                myRef.setValue(mWantTemp);
            }
        });

        mWarmerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWantTemp = mWantTemp + 2;
                mStudentTempTextView.setText("We want a temperature of "+ mWantTemp + " degrees.");
                myRef.setValue(mWantTemp);
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                returnIntent.putExtra("result", mWantTemp);
                setResult(1, returnIntent);
                finish();
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Integer value = dataSnapshot.getValue(Integer.class);
                mWantTemp = value;
                mStudentTempTextView.setText("We want a temperature of "+ mWantTemp + " degrees.");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = getIntent();
        returnIntent.putExtra("result", mWantTemp);
        setResult(1, returnIntent);
        finish();
    }
}
