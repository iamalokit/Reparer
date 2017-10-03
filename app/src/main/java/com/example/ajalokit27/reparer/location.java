package com.example.ajalokit27.reparer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class location extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextHouseNo;
    private EditText editTextLandmark;
    private EditText editTextLocality;
    private Button buttonSaveAddress;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        firebaseAuth = FirebaseAuth.getInstance();
        editTextHouseNo = (EditText) findViewById(R.id.editTextHouseNo);
        editTextLandmark = (EditText) findViewById(R.id.editTextLandmark);
        editTextLocality = (EditText) findViewById(R.id.editTextLocality);
        buttonSaveAddress = (Button) findViewById(R.id.buttonSaveAddress);
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(location.this, login.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        buttonSaveAddress.setOnClickListener(location.this);
    }

    private void saveUserAddress(){
        String houseno = editTextHouseNo.getText().toString().trim();
        String landmark = editTextLandmark.getText().toString().trim();
        String locality = editTextLocality.getText().toString().trim();
        UserAddress userAddress= new UserAddress(houseno, landmark, locality);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userAddress);
        Toast.makeText(this, "Address Saved", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        if(view == buttonSaveAddress){
            saveUserAddress();
            finish();
            startActivity(new Intent(location.this, ScheduleDateTimeAddress.class));
        }
    }
}
