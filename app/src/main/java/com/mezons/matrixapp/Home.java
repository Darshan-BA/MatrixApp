package com.mezons.matrixapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class Home extends AppCompatActivity {
    MaterialButton singleMatBut,twoMatBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        singleMatBut=findViewById(R.id.singleMatBut);
        twoMatBut=findViewById(R.id.twoMatBut);
        singleMatBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Single_Matrix.class);
                startActivity(intent);
                finish();
            }
        });
        twoMatBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Two_Matrix.class);
                startActivity(intent);
                finish();
            }
        });


    }
}

