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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        //no funciona debido a los elementos de la lista
        lvItems.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
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
            case R.id.action_onlyFood:
                showAlertD();
                break;
            case R.id.action_onlyDrinks:
                showAlertD();
                break;
        }
        productAdapter.sortBy(option);

        return super.onOptionsItemSelected(item);
    }
    private void showAlertD(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Tipos elementos").setCancelable(true)
                .setItems(R.array.optionsTypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showTypes(i);
                    }
                });
        builder.create();
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        productAdapter.safePrefs();
        Toast.makeText(this,"guardado",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        productAdapter.readPrefs();
        Toast.makeText(this,"restaurado",Toast.LENGTH_SHORT).show();
    }
/* @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //safe the items with cant!=0
        safePrefs();
        Toast.makeText(this,"guardado",Toast.LENGTH_SHORT).show();
    }*/
}
