package com.jtcode.onehundredmontaditos;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    static String B="bebida";
    static String M="Montadito";
    private static  List<Product> allItems= new ArrayList<Product>();
    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
        addAllItems();
    }
    private void addAllItems() {
        allItems.add(new Product("Chorizo", 0, M));
        allItems.add(new Product("Vegetal", 0, M));
        allItems.add(new Product("Serranito", 0, M));
        allItems.add(new Product("Chopitos", 0, M));
        allItems.add(new Product("Kebab", 0, M));
        allItems.add(new Product("Pollo", 0, M));
        allItems.add(new Product("Pan Chocolate", 0, M));
        allItems.add(new Product("Ternera", 0, M));
        allItems.add(new Product("Picante", 0, M));
        allItems.add(new Product("Panceta", 0, M));
        allItems.add(new Product("Agua", 0, B));
        allItems.add(new Product("Cerveza", 0, B));
        allItems.add(new Product("Tinto de verano", 0, B));
        allItems.add(new Product("Tinto de invierno", 0, B));
    }

    public static List<Product> getAll(){
        return allItems;
    }

    public static ArrayList<Product> getDrinks(){
        ArrayList<Product> ptemp=new ArrayList();
        for(Product p: allItems){
            if(p.getType()==B){
                ptemp.add(p);
            }
        }
        return ptemp;
    }
    public static ArrayList<Product> getFood(){
        ArrayList<Product> ptemp=new ArrayList();
        for(Product p: allItems){
            if(p.getType()==M){
                ptemp.add(p);
            }
        }
        return ptemp;
    }

}
