package com.govin.wisata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin() ){

            startActivity(new Intent(MainActivity.this, MainActivity2.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        else if (!sharedPrefManager.getSPSudahLogin()) {


        }
    }

    public void buka(View view){
        startActivity(new Intent(getApplicationContext(),Start.class));
    }
}