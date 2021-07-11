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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText emaillogin, passwordlogin;
    private Button btnloginlogin, btnsignuplogin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        emaillogin = findViewById(R.id.emaillogin);
        passwordlogin = findViewById(R.id.passwordlogin);
        btnloginlogin= findViewById(R.id.btnloginlogin);
        btnsignuplogin = findViewById(R.id.btnsignuplogin);

        btnsignuplogin.setOnClickListener(this);
        btnloginlogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnloginlogin:
                    userLogin();
                break;
            case R.id.btnsignuplogin:
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                break;
            }
        }

    private void userLogin() {
        String email= emaillogin.getText().toString().trim();
        String password= passwordlogin.getText().toString().trim();
        if(email.isEmpty()) {
            emaillogin.setError("Email is Empty!");
            emaillogin.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emaillogin.setError("Email is Not Valid!");
            emaillogin.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            passwordlogin.setError("Password is Empty!");
            passwordlogin.requestFocus();
            return;
        }
        if(password.length()<6) {
            passwordlogin.setError("Minimum Password Length Must be 6 Character!");
            passwordlogin.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(),Feature.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                        Toast.makeText(getApplicationContext(),"Login Unsuccessful!",Toast.LENGTH_SHORT).show();

                    }
                }
        });
    }
}