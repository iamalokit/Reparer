package com.example.ajalokit27.reparer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class services extends AppCompatActivity implements View.OnClickListener {
    private  ListView listView;
    private TextView textViewLogOut;
    private FirebaseAuth firebaseAuth;
    String[] servicesNames = {"AC Service and Repair",
            "Architect",
            "Bathroom Deep Cleaning",
            "Carpenter",
            "Carpet Cleaning",
            "Chimney and Hob repair",
            "Construction and Renovation",
            "Wall Painters",
            "Interior Designers",
            "Modular Kitchens",
            "Microwave Repair",
            "Pest Control",
            "Plumber",
            "Home Deep Cleaning",
            "Kitchen Deep Cleaning",
            "RO or Water Purifier Repair",
            "Washing Machine Repair",
            "Water Tank Cleaning"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        firebaseAuth  = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(services.this, login.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewLogOut = (TextView) findViewById(R.id.textViewLogOut);
        textViewLogOut.setOnClickListener(services.this);
        listView = (ListView) findViewById(R.id.list_services);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(services.this,android.R.layout.simple_list_item_1,servicesNames );
        listView.setAdapter(adp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case  0:
                        Intent i0 = new Intent(services.this, achome.class);
                        startActivity(i0);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == textViewLogOut){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(services.this, login.class));

        }
    }
}
