package com.govin.wisata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void bukaLogin(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
    public void bukaSignup(View view){
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }
}