package com.example.ajalokit27.reparer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ScheduleDateTimeAddress extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextDate;
    private Button buttonSaveDateTime;
    private Spinner spinnerTime;
    private FirebaseAuth firebaseAuth;
    int year_x, month_x, day_x;
    static final int DILOG_ID = 0;
    private DatabaseReference databaseReferenceDateTime = FirebaseDatabase.getInstance().getReference("Date and Time");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_date_time_address);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(ScheduleDateTimeAddress.this, login.class));
        }

        buttonSaveDateTime = (Button) findViewById(R.id.buttonSaveDateTime);
        showDialogOnButtonClick();
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        spinnerTime = (Spinner) findViewById(R.id.spinnerTime);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ScheduleDateTimeAddress.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.timeRange));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(arrayAdapter);
        buttonSaveDateTime.setOnClickListener(ScheduleDateTimeAddress.this);
    }

    public void showDialogOnButtonClick() {
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DILOG_ID);
            }
        });

    }

    public Dialog onCreateDialog(int id) {
        if (id == DILOG_ID) {
            return new DatePickerDialog(this, datePickerListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year_x = i;
            month_x = i1 + 1;
            day_x = i2;
            editTextDate.setText(year_x + "/" + month_x + "/" + day_x);

        }
    };
    private void saveDateTime(){
        String dateOfService = editTextDate.getText().toString().trim();

        DateTime dateTime = new DateTime(dateOfService);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReferenceDateTime.child(user.getUid()).setValue(dateTime);
        Toast.makeText(this, "Date and Time Saved", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        if (view == buttonSaveDateTime) {
            saveDateTime();
            finish();

        }
    }
}