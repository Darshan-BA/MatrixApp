package com.mezons.matrixapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class Single_Matrix extends AppCompatActivity {

    Button determinant, transpose;
    Spinner spinner;
    int row,column;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single__matrix);
        determinant=findViewById(R.id.determinantBut);
        transpose =findViewById(R.id.inverseBut);
        spinner=findViewById(R.id.singleMatspinner);
        String[] size={"1","2","3","4"};
        ArrayAdapter<String>sizes=new ArrayAdapter<String>(Single_Matrix.this,android.R.layout.simple_spinner_dropdown_item,size);
        spinner.setAdapter(sizes);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        row=column=1;
                        break;
                    case 1:
                        row=column=2;
                        break;
                    case 2:
                        row=column=3;
                        break;
                    case 3:
                        row=column=4;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    row=column=1;
            }
        });
        determinant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* int row,column;
                if(!singleRowEditText.getText().toString().equals("")){
                    row=Integer.parseInt(singleRowEditText.getText().toString());
                    Log.d("OppList",String.valueOf(row));
                }else {
                    row=1;
                }
                if(!singleColumnEditText.getText().toString().equals("")){
                    column=Integer.parseInt(singleColumnEditText.getText().toString());
                    Log.d("OppList",String.valueOf(row));
                }else {
                    column=1;
                }*/
                Intent intent=new Intent(Single_Matrix.this,MatrixVaue.class);
                intent.putExtra("opp",1);
                intent.putExtra("row",row);
                intent.putExtra("column",column);
                startActivity(intent);
                finish();
            }
        });
        transpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*int row,column;
                if(!singleRowEditText.getText().toString().equals("")){
                    row=Integer.parseInt(singleRowEditText.getText().toString());
                    Log.d("OppList",String.valueOf(row));
                }else {
                    row=1;
                }
                if(!singleColumnEditText.getText().toString().equals("")){
                    column=Integer.parseInt(singleColumnEditText.getText().toString());
                    Log.d("OppList",String.valueOf(row));
                }else {
                    column=1;
                }*/
                Intent intent=new Intent(Single_Matrix.this,MatrixVaue.class);
                intent.putExtra("opp",2);
                intent.putExtra("row",row);
                intent.putExtra("column",column);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Single_Matrix.this,Home.class);
        startActivity(intent);
        finish();
    }
}
