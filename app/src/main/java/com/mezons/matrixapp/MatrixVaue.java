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

public class MatrixVaue extends AppCompatActivity {

    RecyclerView recyclerView=null;
    MatrixAdapter matrixAdapter;
    double[][] values;
    Button result,close;
    TextView textView,textView1,note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_vaue);
        textView=findViewById(R.id.resulttitle);
        textView1=findViewById(R.id.resulttextview);
        note=findViewById(R.id.note);
        result=findViewById(R.id.twoMatrixResultBut);
        close=findViewById(R.id.twoMatrixCloseBut);
        Intent intent=getIntent();
        final int ROW_COUNT = intent.getIntExtra("row", 1);
        Log.d("OppList",String.valueOf(ROW_COUNT));
        final int COLUMN_COUNT = intent.getIntExtra("column", 1);
        Log.d("OppList",String.valueOf(COLUMN_COUNT));
        final int opp=intent.getIntExtra("opp",1);
        matrixAdapter=new MatrixAdapter();
        matrixAdapter.setRowColumn(ROW_COUNT,COLUMN_COUNT);
        recyclerView=findViewById(R.id.rvNumbers);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), COLUMN_COUNT, GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(matrixAdapter);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values=matrixAdapter.getValues();
                if(opp==1){
                    new DeterminantAsyncTask().execute(values);
                }
                if(opp==2){
                    new TransposeAsyncTask().execute(values);
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent closeIntent=new Intent(MatrixVaue.this,Home.class);
                startActivity(closeIntent);
                finish();
            }
        });

    }
    class TransposeAsyncTask extends AsyncTask<double[][],Void,double[][]>{
        private int rows,columns;
        private double[][] perform;

        @Override
        protected void onPostExecute(double[][] doubles) {
            super.onPostExecute(doubles);
            if(recyclerView!=null){
                recyclerView.getVisibility();
                textView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                note.setVisibility(View.GONE);
                textView1.setVisibility(View.VISIBLE);
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
            perform=doubles[0];
            Log.d("BA", "perform"+Arrays.deepToString(perform));
            rows=perform.length;
            columns=perform[0].length;
            double[][] result=new double[rows][columns];
            for (int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    result[i][j]=perform[j][i];
                }
            }
            Log.d("BA", "transposed"+Arrays.deepToString(result));
            return result;
        }
    }

    class DeterminantAsyncTask extends AsyncTask<double[][], Void, Double> {
        private double[][] arr;
        private double result = 0;

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);
            if(recyclerView!=null){
                recyclerView.getVisibility();
                textView1.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                note.setVisibility(View.GONE);
                textView1.setText(String.valueOf(aDouble));
            }
        }

        @Override
        protected Double doInBackground(double[][]... doubles) {
            arr = doubles[0];
            result = determinant(arr);
            Log.d("BA", "Determinant"+result);
            return result;
        }

        private double determinant(double[][] arr) {
            double r = 0;
            if (arr.length == 1) {
                result = arr[0][0];
                //Log.d("BA", "1 determinant" + result);
                return result;
            } else if (arr.length == 2) {
                result = arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
                //Log.d("BA", "2 determinant" + result);
                return result;
            } else {
                for (int i = 0; i < arr[0].length; i++) {
                    double[][] temp = new double[arr.length - 1][arr[0].length - 1];
                    for (int j = 1; j < arr.length; j++) {
                        for (int k = 0; k < arr[0].length; k++) {
                            if (k < i) {
                                temp[j - 1][k] = arr[j][k];
                            } else if (k > i) {
                                temp[j - 1][k - 1] = arr[j][k];
                            }
                        }
                    }
                    r += arr[0][i] * Math.pow(-1, (int) i) * determinant(temp);
                }
                return r;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent closeIntent=new Intent(MatrixVaue.this,Home.class);
        startActivity(closeIntent);
        finish();
    }

}