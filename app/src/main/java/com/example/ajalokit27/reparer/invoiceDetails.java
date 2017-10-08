package com.example.ajalokit27.reparer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class invoiceDetails extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewDetails;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private Button buttonSaveAndMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);
        textViewDetails = (TextView) findViewById(R.id.textViewDetails);
        buttonSaveAndMail = (Button)findViewById(R.id.saveAndMail);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()== null){
            finish();
            startActivity(new Intent(getApplicationContext(), login.class));
        }
        buttonSaveAndMail.setOnClickListener(this);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot typeServiceSnapshot : dataSnapshot.getChildren()){
                    DataTypeService dataTypeService = typeServiceSnapshot.getValue(DataTypeService.class);
                    String typeOfService = dataTypeService.getTypeOfService();
                    textViewDetails.setText(typeOfService);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
