package com.example.ajalokit27.reparer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private EditText editTextEmailLogin;
    private EditText editTextPasswordLogin;
    private Button buttonSignIn;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(login.this, services.class));
        }
        editTextEmailLogin = (EditText) findViewById(R.id.editTextEmailToLogin);
        editTextPasswordLogin = (EditText) findViewById(R.id.editTextPasswordToLogin);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        textViewSignUp= (TextView) findViewById(R.id.textViewSignup);
        buttonSignIn.setOnClickListener(login.this);
        textViewSignUp.setOnClickListener(login.this);
        progressDialog = new ProgressDialog(login.this);

    }
    private void userLogin(){
        String email = editTextEmailLogin.getText().toString().trim();
        String password = editTextPasswordLogin.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Signing In");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), services.class));


                        } else {
                            Toast.makeText(login.this, "Fail to SignIn", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });

    }
    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            // enter the application
            userLogin();
        }
        if(view == textViewSignUp){
            // ask the user to sign up
            finish();
            startActivity(new Intent(login.this, SignUp.class));
        }
    }
}
