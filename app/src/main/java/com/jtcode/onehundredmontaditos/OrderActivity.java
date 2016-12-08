package com.jtcode.onehundredmontaditos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private ArrayList<Product> products;
    TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
        addData();
    }
    private void init(){
        table=(TableLayout)findViewById(R.id.tableOrder);
    }

    private void addData(){
        //recorrer en un for la cantidad de elementos que se han enviado en el pedido

        TextView txvName,txvCant;
        TableRow row;
        for (int i=0;i<products.size();i++){
            row=new TableRow(this);
            txvName= new TextView(this);
            txvName.setText(products.get(i).getName());
            row.addView(txvName);
            txvCant=new TextView(this);
            txvCant.setText(products.get(i).getCant());
            row.addView(txvCant);
            table.addView(row);
        }
    }

}
