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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText email, password, confirmPassword;
    private TextView loginNow;
    private Button SignUpBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = (EditText) findViewById(R.id.etEmailAddress);
        password = (EditText) findViewById(R.id.etPassword);
        confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        loginNow = (TextView) findViewById(R.id.LoginNow);
        SignUpBtn = (Button) findViewById(R.id.signup_btn);

        mAuth = FirebaseAuth.getInstance();

        loginNow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

    }

    private void createUser() {
        String emailAdd = email.getText().toString();
        String pass = password.getText().toString();
        String cpswd = confirmPassword.getText().toString();

        if (!emailAdd.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()){
            if (!pass.isEmpty() && !cpswd.isEmpty()){
                if(pass.equals(cpswd)) {
                    mAuth.createUserWithEmailAndPassword(emailAdd, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(SignUpActivity.this, "Registered Successfully !!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    confirmPassword.setError("Password in both field is not same");
            }
        }else if(emailAdd.isEmpty()){
            email.setError("Empty Fields Are not Allowed");
        }else{
            email.setError("Pleas Enter Correct Email");
        }
    }


    }
}