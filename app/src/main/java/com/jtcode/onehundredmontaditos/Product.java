package com.jtcode.onehundredmontaditos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Product implements Comparable<Product>, Parcelable{

    private String name;
    private int cant;
    private String type;

    public Product(String name, int cant, String type) {
        this.name = name;
        this.cant = cant;
        this.type = type;
    }

    protected Product(Parcel in) {
        name = in.readString();
        cant = in.readInt();
        type = in.readString();
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

    public void incCant(int cant){
        this.cant+=cant;
    }
    public void setCant(int cant) {
        this.cant = cant;
    }

    @Override
    public int compareTo(Product item) {
        return this.getName().compareToIgnoreCase(item.getName());
    }

    public static Comparator<Product> sortNameAsc=new Comparator<Product>() {
        @Override
        public int compare(Product item, Product t1) {
            return item.getName().compareToIgnoreCase(t1.getName());
        }
    };
    public static Comparator<Product> sortNameDes=new Comparator<Product>() {
        @Override
        public int compare(Product item, Product t1) {
            return t1.getName().compareToIgnoreCase(item.getName());
        }
    };

    public static Comparator<Product> sortTypeAsc=new Comparator<Product>() {
        @Override
        public int compare(Product item, Product t1) {
            if(t1.getType()==item.getType()) {
                return item.getName().compareToIgnoreCase(t1.getName());
            }else{
                return item.getType().compareToIgnoreCase(t1.getType());
            }
        }
    };
    public static Comparator<Product> sortTypeDes=new Comparator<Product>() {
        @Override
        public int compare(Product item, Product t1) {
            if(t1.getType()==item.getType()) {
                return t1.getName().compareToIgnoreCase(item.getName());
            }else{
                return t1.getType().compareToIgnoreCase(item.getType());
            }
        }
    };

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(this.cant);
        parcel.writeString(this.type);
    }
}
