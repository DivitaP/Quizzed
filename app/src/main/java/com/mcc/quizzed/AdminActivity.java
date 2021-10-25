package com.mcc.quizzed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.*;


public class AdminActivity extends AppCompatActivity {
    public EditText text1;
    public EditText text2;
    public EditText text3;
    public EditText text4;
    public EditText text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button button= findViewById(R.id.button);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        text4=findViewById(R.id.text4);
        text5=findViewById(R.id.text5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startadding();
                startActivity(new Intent(AdminActivity.this, StartingScreenActivity.class));
            }
        });
    }
    public void startadding()
    {
        String txt1=text1.getText().toString();
        String txt2=text2.getText().toString();
        String txt3=text3.getText().toString();
        String txt4=text4.getText().toString();
        String txt5 = text5.getText().toString();

        QuizDbHelper dbHelper = new QuizDbHelper(this);

        dbHelper.insertQuestion(txt1,txt2,txt3,txt4,Integer.parseInt(txt5));

    }
}