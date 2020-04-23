package com.mezons.matrixapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class TwoMatrixValue extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView1;
    MatrixAdapter matrixAdapter,matrixAdapter1;
    Button result,close;
    TextView matText1,matText2;
    TextView textView,textView1,note2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_matrix_value);
        matText1=findViewById(R.id.mattext1);
        matText2=findViewById(R.id.mattext2);
        note2=findViewById(R.id.note2);
        textView=findViewById(R.id.twoResultTitle);
        textView1=findViewById(R.id.twoResultTextView);
        result=findViewById(R.id.twoMatrixResultBut);
        close=findViewById(R.id.twoMatrixCloseBut);
        result=findViewById(R.id.twoMatrixResultBut);
        close=findViewById(R.id.twoMatrixCloseBut);
        Intent intent=getIntent();
        final int opp=intent.getIntExtra("opp",1);
        final int ROW_COUNT = intent.getIntExtra("row", 1);
        final int ROW_COUNT1 = intent.getIntExtra("row1", 1);
        final int COLUMN_COUNT = intent.getIntExtra("column", 1);
        final int COLUMN_COUNT1 = intent.getIntExtra("column1", 1);
        matrixAdapter=new MatrixAdapter();
        matrixAdapter.setRowColumn(ROW_COUNT,COLUMN_COUNT);
        recyclerView=findViewById(R.id.rvNumbers);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), COLUMN_COUNT, GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(matrixAdapter);
        matrixAdapter1=new MatrixAdapter();
        matrixAdapter1.setRowColumn(ROW_COUNT,COLUMN_COUNT);
        recyclerView1=findViewById(R.id.rvNumbers1);
        recyclerView1.setLayoutManager(new GridLayoutManager(getApplicationContext(), COLUMN_COUNT, GridLayoutManager.VERTICAL,false));
        recyclerView1.setAdapter(matrixAdapter1);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double[][]values=matrixAdapter.getValues();
                double[][]values1=matrixAdapter1.getValues();
                if(opp==1){
                    new AddAsyncTask().execute(values,values1);
                }
                if(opp==2){
                    new SubAsyncTask().execute(values,values1);
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent closeIntent=new Intent(TwoMatrixValue.this,Home.class);
                startActivity(closeIntent);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent closeIntent=new Intent(TwoMatrixValue.this,Home.class);
        startActivity(closeIntent);
        finish();
    }
    class AddAsyncTask extends AsyncTask<double[][],Void,double[][]>{
        private double[][] mat1,mat2;
        private int rows,columns;
        @Override
        protected double[][] doInBackground(double[][]... doubles) {
            mat1=doubles[0];
            Log.d("BA", "mat1"+Arrays.deepToString(mat1));
            mat2=doubles[1];
            Log.d("BA", "mat2"+Arrays.deepToString(mat2));
            rows=mat1.length;
            columns=mat1[0].length;
            double[][] result=new double[rows][columns];
            for(int i=0;i<mat1.length;i++){
                for (int j=0;j<mat1[0].length;j++){
                    result[i][j]=mat1[i][j]+mat2[i][j];
                }
            }
            Log.d("BA", "addtion"+Arrays.deepToString(result));
            return result;
        }

        @Override
        protected void onPostExecute(double[][] doubles) {
            super.onPostExecute(doubles);
            if(recyclerView!=null && recyclerView1!=null){
                matText1.setVisibility(View.GONE);
                matText2.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                recyclerView1.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);
                note2.setVisibility(View.GONE);
                for (double[] aDouble : doubles) {
                    for (int j = 0; j < doubles[0].length; j++) {
                        textView1.append(String.valueOf(aDouble[j]));
                        textView1.append("\b\b");
                    }
                    textView1.append("\n");
                }
            }
        }
    }

    class SubAsyncTask extends AsyncTask<double[][],Void,double[][]>{
        private double[][] mat1,mat2;
        private int rows,columns;

        @Override
        protected void onPostExecute(double[][] doubles) {
            super.onPostExecute(doubles);
            if(recyclerView!=null && recyclerView1!=null){
                matText1.setVisibility(View.GONE);
                matText2.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                recyclerView1.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.VISIBLE);
                note2.setVisibility(View.GONE);
                for (double[] aDouble : doubles) {
                    for (int j = 0; j < doubles[0].length; j++) {
                        textView1.append(String.valueOf(aDouble[j]));
                        textView1.append("\b\b");
                    }
                    textView1.append("\n");
                }
            }
        }

        @Override
        protected double[][] doInBackground(double[][]... doubles) {
            mat1=doubles[0];
            Log.d("BA", "mat1"+Arrays.deepToString(mat1));
            mat2=doubles[1];
            Log.d("BA", "mat2"+Arrays.deepToString(mat2));
            rows=mat1.length;
            columns=mat1[0].length;
            double[][] result=new double[rows][columns];
            for(int i=0;i<mat1.length;i++){
                for (int j=0;j<mat1[0].length;j++){
                    result[i][j]=mat1[i][j]-mat2[i][j];
                }
            }
            Log.d("BA", "Subtraction"+Arrays.deepToString(result));
            return result;
        }
    }


}

