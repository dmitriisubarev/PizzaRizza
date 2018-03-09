package ru.efirus.app.pizzarizza;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Product {

    String name;
    String describe;
    int price;
    int image;
    boolean box;


    Product(String _name, String _describe, int _price, int _image, boolean _box) {
        name = _name;
        describe = _describe;
        price = _price;
        image = _image;
        box = _box;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
