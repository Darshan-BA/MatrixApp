package com.mezons.matrixapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Two_Matrix extends AppCompatActivity {

    Button add,sub;
    EditText doubleRowEditText,doubleColumnEditText,doubleRowEditText1,doubleColumnEditText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two__matrix);
        add=findViewById(R.id.addBut);
        sub=findViewById(R.id.subBut);
        doubleColumnEditText=findViewById(R.id.doubleColumnEditText);
        doubleRowEditText=findViewById(R.id.doubleRowEditText);
        //doubleColumnEditText1=findViewById(R.id.doubleColumnEditText1);
        //doubleRowEditText1=findViewById(R.id.doubleRowEditText1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMethod(1);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMethod(2);
            }
        });

    }

    private void goToMethod(int i) {
        int row,column,row1,column1;
        Intent intent=new Intent(Two_Matrix.this,TwoMatrixValue.class);
        intent.putExtra("opp",i);
        if(doubleRowEditText.getText().toString().equals("")){
            doubleRowEditText.setError("Empty");
        }
        if(doubleColumnEditText.getText().toString().equals("")){
            doubleColumnEditText.setError("Empty");
        }
        if (!doubleRowEditText.getText().toString().equals("") && Integer.parseInt(doubleRowEditText.getText().toString()) > 4) {
            doubleRowEditText.setError("Size limit is 4");
        }
        if (!doubleColumnEditText.getText().toString().equals("") && Integer.parseInt(doubleColumnEditText.getText().toString()) > 4) {
            doubleColumnEditText.setError("Size limit is 4");
        }
        if(!doubleRowEditText.getText().toString().equals("") && !doubleColumnEditText.getText().toString().equals("") &&
                Integer.parseInt(doubleRowEditText.getText().toString()) <=4 && Integer.parseInt(doubleColumnEditText.getText().toString()) <=4){
            row = row1= Integer.parseInt(doubleRowEditText.getText().toString());
            intent.putExtra("row", row);
            intent.putExtra("row1", row1);
            column=column1=Integer.parseInt(doubleColumnEditText.getText().toString());
            intent.putExtra("column", column);
            intent.putExtra("column", column1);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Two_Matrix.this,Home.class);
        startActivity(intent);
        finish();
    }
}
