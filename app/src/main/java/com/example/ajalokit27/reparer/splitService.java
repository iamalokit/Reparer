package com.example.ajalokit27.reparer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class splitService extends AppCompatActivity implements View.OnClickListener {
    Button bt;
    CheckBox checkBoxWet, checkBoxRepair, checkBoxNewAC, checkBoxUninstall;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_service);
        bt = (Button) findViewById(R.id.next);
        bt.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(splitService.this, login.class));
        }
        checkBoxWet = (CheckBox) findViewById(R.id.wet);
        checkBoxRepair = (CheckBox) findViewById(R.id.repair);
        checkBoxNewAC = (CheckBox) findViewById(R.id.newAC);
        checkBoxUninstall = (CheckBox) findViewById(R.id.uninst);
        databaseReference = FirebaseDatabase.getInstance().getReference("typeofService");
    }

    private void saveTypeOfService(){
        String typeOfService = null;
        if (checkBoxWet.isChecked()) {
            typeOfService = checkBoxWet.getText().toString().trim();
            finish();
            startActivity(new Intent(splitService.this, wetServicing.class));
        }
        if (checkBoxRepair.isChecked()) {
            finish();
            typeOfService = checkBoxRepair.getText().toString().trim();
            startActivity(new Intent(splitService.this, location.class));
        }
        if (checkBoxNewAC.isChecked()) {
            finish();
            typeOfService = checkBoxNewAC.getText().toString().trim();
            startActivity(new Intent(splitService.this, spacinstall.class));
        }
        if (checkBoxUninstall.isChecked()) {
            finish();
            typeOfService = checkBoxUninstall.getText().toString().trim();
            startActivity(new Intent(splitService.this, spacuninstall.class));
        }
        if (!TextUtils.isEmpty(typeOfService)) {
            finish();
            DataTypeService dataTypeService = new DataTypeService(typeOfService);
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).setValue(dataTypeService);
        }
    }

    @Override
    public void onClick(View view) {
            if(view == bt){
                saveTypeOfService();
            }
    }

}
