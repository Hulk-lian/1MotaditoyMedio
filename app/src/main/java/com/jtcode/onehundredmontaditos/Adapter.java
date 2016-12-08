package com.jtcode.onehundredmontaditos;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Adapter extends ArrayAdapter<Item> {

    Context context;
    private ArrayList<Item> localList;
    SharedPreferences sharedPreferences;

    public Adapter(Context context) {
        super(context, R.layout.item_list);
        this.context=context;
        this.addAll(Repository.getAll());
        this.localList=new ArrayList<>();
        localList.addAll(Repository.getAll());
    }
    //lectura de los elementos de la preferencia
    private void readPrefs(){

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View viewItem=convertView;
        ProductHolder ph;
        if(viewItem==null){
            ph= new ProductHolder();

            ph.btnadd=(Button)viewItem.findViewById(R.id.btnPlus);
            ph.btnrm=(Button)viewItem.findViewById(R.id.btlLess);
            ph.edtCant=(EditText)viewItem.findViewById(R.id.edtCant);
            ph.txvName=(TextView)viewItem.findViewById(R.id.txvName);

            viewItem.setTag(ph);
        }
        else{
            ph=(ProductHolder)viewItem.getTag();
        }

        ph.txvName.setText(getItem(position).getName());
        ph.edtCant.setText(getItem(position).getCant());
        ph.btnrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(position).getCant()!=10) {
                    getItem(position).setCant(-1);
                }
                else{
                    Toast.makeText(context,R.string.no_more,Toast.LENGTH_SHORT).show();
                }
            }
        });
        ph.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return viewItem;
    }

    class ProductHolder{
        TextView txvName;
        EditText edtCant;
        Button btnadd,btnrm;
    }
}
