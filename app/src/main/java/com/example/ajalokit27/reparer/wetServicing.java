package com.example.ajalokit27.reparer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class wetServicing extends AppCompatActivity implements View.OnClickListener {
    Button bt;
    RadioButton r1,r2, r3, r4, r5;
    TextView t1, t2, t3, t4, t5;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NumberAndPrice");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wet_servicing);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(wetServicing.this, login.class));
        }
        bt = (Button) findViewById(R.id.next);
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);
        r4 = (RadioButton) findViewById(R.id.r4);
        r5 = (RadioButton) findViewById(R.id.r5);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);
        bt.setOnClickListener(this);
    }
    private void saveNumberAndPrice(){
        String number = null;
        String price = null;
        if (r1.isChecked()) {
            number = r1.getText().toString().trim();
            price = t1.getText().toString().trim();
            finish();
            startActivity(new Intent(wetServicing.this, location.class));
        }
        if (r2.isChecked()) {
            number = r2.getText().toString().trim();
            price = t2.getText().toString().trim();
            finish();
            startActivity(new Intent(wetServicing.this, location.class));
        }
        if (r3.isChecked()) {
            number = r3.getText().toString().trim();
            price = t3.getText().toString().trim();
            finish();
            startActivity(new Intent(wetServicing.this, location.class));
        }
        if (r4.isChecked()) {
            number = r4.getText().toString().trim();
            price = t4.getText().toString();
            finish();
            startActivity(new Intent(wetServicing.this, location.class));
        }
        if (r5.isChecked()) {
            number = r5.getText().toString().trim();
            price = t5.getText().toString().trim();
            finish();
            startActivity(new Intent(wetServicing.this, location.class));
        }
        if (!TextUtils.isEmpty(number)) {
            SaveNumberAndPrice saveNumberAndPrice = new SaveNumberAndPrice(number, price);
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(saveNumberAndPrice);
        }
    }

    @Override
    public void onClick(View view) {
        finish();
        saveNumberAndPrice();
    }
}
