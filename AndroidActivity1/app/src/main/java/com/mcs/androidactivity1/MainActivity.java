package com.mcs.androidactivity1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createAccount = findViewById(R.id.btn_img_splash);
        createAccount.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, AccountCreationActivity.class);
        startActivity(intent);
    }
}