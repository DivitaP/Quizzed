package com.mcc.quizzed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button loginBtn;
    private TextView SignUpNow;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);
        SignUpNow = findViewById(R.id.signUpbtn);
        loginBtn = findViewById(R.id.login_btn);

        mAuth = FirebaseAuth.getInstance();

        SignUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String emailAdd = email.getText().toString();
        String pass = password.getText().toString();

        if (!emailAdd.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()){
            if (!pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(emailAdd , pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(LoginActivity.this, "Login Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this , MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                password.setError("Empty Fields Are not Allowed");
            }
        }else if(emailAdd.isEmpty()){
            email.setError("Empty Fields Are not Allowed");
        }else{
            email.setError("Please Enter Correct Email");
        }

    }
}