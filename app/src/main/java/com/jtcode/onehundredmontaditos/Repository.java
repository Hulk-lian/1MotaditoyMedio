package com.jtcode.onehundredmontaditos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hulk-lián on 08/12/2016.
 */
public class Repository {
    static String B="bebida";
    static String M="Montadito";
    private static  List<Item> allItems= new ArrayList<Item>();
    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
        addAllItems();
    }
    private void addAllItems() {
        allItems.add(new Item("Chorizo", 0, M));
        allItems.add(new Item("Vegetal", 0, M));
        allItems.add(new Item("Serranito", 0, M));
        allItems.add(new Item("Chopitos", 0, M));
        allItems.add(new Item("Kebab", 0, M));
        allItems.add(new Item("Pollo", 0, M));
        allItems.add(new Item("Chocolate", 0, M));
        allItems.add(new Item("Ternera", 0, M));
        allItems.add(new Item("Picante", 0, M));
        allItems.add(new Item("Panceta", 0, M));
        allItems.add(new Item("Agua", 0, B));
        allItems.add(new Item("Cerveza", 0, B));
        allItems.add(new Item("Tinto de verano", 0, B));
        allItems.add(new Item("Tinto de invierno", 0, B));
    }
    public static List<Item> getAll(){
        return allItems;
    }

}
