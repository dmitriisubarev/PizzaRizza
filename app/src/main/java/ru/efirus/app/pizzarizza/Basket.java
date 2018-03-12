package ru.efirus.app.pizzarizza;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by efirus on 12.03.18.
 */
@Entity
public class Basket {
    @PrimaryKey
    private int basketid;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "product_price")
    private int productPrice;

    @ColumnInfo(name = "product_quent")
    private int productQuent;

    public int getBasketid() {
        return basketid;
    }

    public void setBasketid(int basketid) {
        this.basketid = basketid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuent() {
        return productQuent;
    }

    public void setProductQuent(int productQuent) {
        this.productQuent = productQuent;
    }
}
