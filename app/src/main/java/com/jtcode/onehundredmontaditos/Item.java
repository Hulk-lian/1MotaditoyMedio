package com.jtcode.onehundredmontaditos;

import java.util.Comparator;

public class Item implements Comparable<Item>{

    private String name;
    private int cant;
    private String type;

    public Item(String name, int cant, String type) {
        this.name = name;
        this.cant = cant;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getCant() {
        return cant;
    }

    public String getType() {
        return type;
    }

    public void setCant(int cant) {
        this.cant += cant;
    }

    @Override
    public int compareTo(Item item) {
        return this.getName().compareToIgnoreCase(item.getName());
    }

    public static Comparator<Item> sortNameAsc=new Comparator<Item>() {
        @Override
        public int compare(Item item, Item t1) {
            return item.getName().compareToIgnoreCase(t1.getName());
        }
    };
    public static Comparator<Item> sortNameDes=new Comparator<Item>() {
        @Override
        public int compare(Item item, Item t1) {
            return t1.getName().compareToIgnoreCase(item.getName());
        }
    };

    public static Comparator<Item> sortTypeAsc=new Comparator<Item>() {
        @Override
        public int compare(Item item, Item t1) {
            if(t1.getType()==item.getType()) {
                return item.getName().compareToIgnoreCase(t1.getName());
            }else{
                return item.getType().compareToIgnoreCase(t1.getType());
            }
        }
    };
    public static Comparator<Item> sortTypeDes=new Comparator<Item>() {
        @Override
        public int compare(Item item, Item t1) {
            if(t1.getType()==item.getType()) {
                return t1.getName().compareToIgnoreCase(item.getName());
            }else{
                return t1.getType().compareToIgnoreCase(item.getType());
            }
        }
    };


}
