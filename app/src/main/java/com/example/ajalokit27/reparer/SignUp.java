package com.example.ajalokit27.reparer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmailSignUp;
    private EditText editTextPasswordSignUp;
    private EditText editTextConfirmPassword;

    private TextView textViewSignIn;
    private FirebaseAuth firebaseAuth;
    private Button buttonSignUp;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(), services.class));
        }

        editTextEmailSignUp = (EditText) findViewById(R.id.editTextEmailToSignUp);
        editTextPasswordSignUp = (EditText) findViewById(R.id.editTextPasswordToSignUp);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPasswordToSignUp);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);
        buttonSignUp.setOnClickListener(SignUp.this);
        textViewSignIn.setOnClickListener(SignUp.this);
        progressDialog = new ProgressDialog(SignUp.this);
    }
    private void registerUser(){
        String email = editTextEmailSignUp.getText().toString().trim();
        String password = editTextPasswordSignUp.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(SignUp.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(SignUp.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), services.class));


                        } else {
                            Toast.makeText(SignUp.this, "Fail to Register", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


        });
    }
    @Override
    public void onClick(View view) {
        if(view == buttonSignUp){
            registerUser();
        }
        if(view == textViewSignIn){
            //sign in activity
            finish();
            startActivity(new Intent(SignUp.this , login.class));
        }
    }
}
