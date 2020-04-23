package com.mezons.matrixapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatrixAdapter extends RecyclerView.Adapter<MatrixAdapter.MatrixHolder> {
    private int COLUMN_COUNT=4;
    private int ROW_COUNT=4;
    private EditText[][] editTexts=new EditText[COLUMN_COUNT][ROW_COUNT];
    void setRowColumn(int a, int b){
        ROW_COUNT=a;
        Log.d("BA","inside setRowColumn row: "+ ROW_COUNT);
        COLUMN_COUNT=b;
        Log.d("BA","inside setRowColumn column: "+ COLUMN_COUNT);
    }
    @NonNull
    @Override
    public MatrixHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycerview_item,parent,false);
        return new MatrixHolder(view,ROW_COUNT,COLUMN_COUNT);
    }

    @Override
    public void onBindViewHolder(@NonNull MatrixHolder holder, int position) {
        editTexts[position / COLUMN_COUNT][position % COLUMN_COUNT]=holder.editText;
    }

    @Override
    public int getItemCount() {
        return COLUMN_COUNT*ROW_COUNT;
    }
    double[][] getValues(){
        double[][] values =new double[ROW_COUNT][COLUMN_COUNT];
        for (int i=0;i<ROW_COUNT;i++){
            for (int j=0;j<COLUMN_COUNT;j++){
                if(!editTexts[i][j].getText().toString().equals("")){
                    values[i][j]=Double.parseDouble(editTexts[i][j].getText().toString());
                }else {
                    values[i][j]=0;
                }
            }
        }
        return values;
    }

    static class MatrixHolder extends RecyclerView.ViewHolder{
        EditText editText;
        int COLUMN_COUNT;
        int ROW_COUNT;
        MatrixHolder(@NonNull View itemView, int row_count, int column_count) {
            super(itemView);
            this.editText=itemView.findViewById(R.id.gridEditText);
            ROW_COUNT=row_count;
            COLUMN_COUNT=column_count;
        }
        }
}
