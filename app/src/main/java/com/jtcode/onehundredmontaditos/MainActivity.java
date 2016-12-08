package com.jtcode.onehundredmontaditos;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProductAdapter productAdapter;
    ListView lvItems;
    FloatingActionButton fabSend;
    boolean alph=false;
    boolean type=false;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared=PreferenceManager.getDefaultSharedPreferences(this);
        init();
    }
    private void init(){
        productAdapter= new ProductAdapter(MainActivity.this);
        fabSend=(FloatingActionButton)findViewById(R.id.fabOrder);
        lvItems=(ListView)findViewById(R.id.list_items);
        lvItems.setAdapter(productAdapter);
        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        lvItems.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Tipos elementos").setCancelable(true)
                        .setItems(R.array.optionsTypes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showTypes(i);
                            }
                        });
                builder.create();
                return false;
            }
        });

    }
    private void showTypes(int i){
        switch (i){
            case 0://bebidas
                productAdapter.showOnly(true);
                break;

            case 1:///montaditos
                productAdapter.showOnly(false);
                break;

            case 2://ambos
                productAdapter.showAll();
                break;
        }
    }

    private void safePrefs(){
        SharedPreferences.Editor editor=shared.edit();
        for(int i=0;i<lvItems.getCount();i++){
            if(productAdapter.getItem(i).getCant()>0){
                editor.putInt(productAdapter.getItem(i).getName(),productAdapter.getItem(i).getCant());
            }
            else
                editor.remove(productAdapter.getItem(i).getName());
        }
        editor.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int option = -1;
        switch (item.getItemId()){
            case R.id.action_alpOrder:
                if(alph){
                    option= ConstantsNames.NAMEASC;
                }
                else{
                    option= ConstantsNames.NAMEDES;
                }
                alph=!alph;
                break;
            case R.id.action_typeOrder:
                if(type){
                    option= ConstantsNames.TYPEASC;
                }
                else{
                    option= ConstantsNames.TYPEDES;
                }
                type=!type;
                break;
        }
        productAdapter.sortBy(option);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //safe the items with cant!=0
        safePrefs();
        Toast.makeText(this,"guardado",Toast.LENGTH_SHORT).show();
    }
}
