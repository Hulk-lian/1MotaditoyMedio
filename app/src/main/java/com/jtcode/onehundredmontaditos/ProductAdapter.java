package com.jtcode.onehundredmontaditos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProductAdapter extends ArrayAdapter<Product> {

    Context context;
    private ArrayList<Product> localList;
    SharedPreferences sharedPreferences;

    public ProductAdapter(Context context) {
        super(context, R.layout.item_list);
        this.context=context;
        this.addAll(Repository.getAll());
        this.localList=new ArrayList<>();
        localList.addAll(Repository.getAll());
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        readPrefs();
    }
    //lectura de los elementos de la preferencia
    public void readPrefs(){
        Map<String,?> preorder=sharedPreferences.getAll();

        for(int i=0;i<getCount();i++){
            if(preorder.containsKey(localList.get(i).getName())){
                getItem(i).setCant(sharedPreferences.getInt(localList.get(i).getName(),0));
            }
        }
    }
    public void safePrefs(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        for(int i=0;i<localList.size();i++){
            if(localList.get(i).getCant()>0){
                editor.putInt(localList.get(i).getName(),localList.get(i).getCant());
            }
            else
                editor.remove(localList.get(i).getName());
        }
        editor.commit();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //view y el holder
        View viewItem=convertView;
        final ProductHolder ph;
//comprobacion delnulo
        if(viewItem==null){
            //memoria al hokder y nuevo inflater
            ph= new ProductHolder();
            LayoutInflater inflater= (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //inflar
            viewItem=inflater.inflate(R.layout.item_list,null);
            //elementos
            ph.btnadd=(Button)viewItem.findViewById(R.id.btnPlus);
            ph.btnrm=(Button)viewItem.findViewById(R.id.btnLess);
            ph.edtCant=(EditText)viewItem.findViewById(R.id.edtCant);
            ph.txvName=(TextView)viewItem.findViewById(R.id.txvName);
            ph.edtCant.setFocusable(false);
            //tag
            viewItem.setTag(ph);
        }
        else{
            ph=(ProductHolder)viewItem.getTag();
        }
    //contendo elementos
        ph.txvName.setText(getItem(position).getName());
        ph.edtCant.setText(String.format("%02d",getItem(position).getCant()));

        ph.btnrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(position).getCant()>0) {
                    getItem(position).incCant(-1);
                }
                else{
                    Toast.makeText(context,R.string.no_negcount,Toast.LENGTH_SHORT).show();
                }
                ph.edtCant.setText(String.format("%02d",getItem(position).getCant()));
            }
        });
        ph.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getItem(position).getCant()<10) {
                    getItem(position).incCant(+1);
                }
                else{
                    Toast.makeText(context,R.string.no_more,Toast.LENGTH_SHORT).show();
                }
                ph.edtCant.setText(String.format("%02d",getItem(position).getCant()));
            }
        });

        return viewItem;
    }

    public void sortBy(int sortType){
        switch (sortType){
            case ConstantsNames.NAMEASC:
                sort(Product.sortNameAsc);
                break;
            case ConstantsNames.NAMEDES:
                sort(Product.sortNameDes);
                break;
            case ConstantsNames.TYPEASC:
                sort(Product.sortTypeAsc);
                break;
            case ConstantsNames.TYPEDES:
                sort(Product.sortTypeDes);
                break;
        }
        //notifyDataSetChanged();
    }
    public void showOnly(boolean drink){
        clear();
        if(drink) {
            addAll(Repository.getDrinks());
        }
        else{
            addAll(Repository.getFood());
        }
        notifyDataSetChanged();
    }
    public void showAll(){
        clear();
        addAll(Repository.getAll());
        notifyDataSetChanged();
    }

    public List<Product> getAllProds(){
        return Repository.getAll();
    }
    class ProductHolder{
        TextView txvName;
        EditText edtCant;
        Button btnadd,btnrm;
    }
}
