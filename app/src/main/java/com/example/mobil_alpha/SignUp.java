package com.example.mobil_alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private Button btnloginsignup,btnRegister;
    private EditText UserName,emailsignup,PhoneNo,NIDNo,passwordsignup,ConfirmPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnloginsignup = (Button) findViewById(R.id.btnloginsignup);
        btnloginsignup.setOnClickListener(this);

        UserName = (EditText) findViewById(R.id.UserName);
        emailsignup = (EditText) findViewById(R.id.emailsignup);
        PhoneNo = (EditText) findViewById(R.id.PhoneNo);
        NIDNo = (EditText) findViewById(R.id.NIDNo);
        passwordsignup = (EditText) findViewById(R.id.passwordsignup);
        ConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnloginsignup:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnRegister:
                userRegister();
                break;
        }
    }

    private void userRegister() {
        String username= UserName.getText().toString().trim();
        String email= emailsignup.getText().toString().trim();
        String phoneno= PhoneNo.getText().toString().trim();
        String nidno= NIDNo.getText().toString().trim();
        String password= passwordsignup.getText().toString().trim();
        String confirmpassword= ConfirmPassword.getText().toString().trim();

        if(username.isEmpty()){
            UserName.setError("User Name is Empty!");
            UserName.requestFocus();
            return;
        }
        if(email.isEmpty()) {
            emailsignup.setError("Email is Empty!");
            emailsignup.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailsignup.setError("Email is Not Valid!");
            emailsignup.requestFocus();
            return;
        }
        if(phoneno.isEmpty()) {
            PhoneNo.setError("Phone Number is Empty!");
            PhoneNo.requestFocus();
            return;
        }
        if(nidno.isEmpty()) {
            NIDNo.setError("NID Number is Empty!");
            NIDNo.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            passwordsignup.setError("Password is Empty!");
            passwordsignup.requestFocus();
            return;
        }
        if(password.length()<6) {
            passwordsignup.setError("Minimum Password Length Must be 6 Character!");
            passwordsignup.requestFocus();
            return;
        }
        if(confirmpassword.isEmpty()) {
            ConfirmPassword.setError("Confirm Password is Empty!");
            ConfirmPassword.requestFocus();
            return;
        }
        if(!password.equals(confirmpassword)) {
            ConfirmPassword.setError("Password Not Matched!");
            ConfirmPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Register is Successfull!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),"User is Already Registered!",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"Error! : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });

    }
}