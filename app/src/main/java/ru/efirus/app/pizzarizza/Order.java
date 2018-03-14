package ru.efirus.app.pizzarizza;

/**
 * Created by efirus on 13.03.18.
 */

public class Order {

    private String name;
    private String quentity;

    public Order() {
    }

    public Order(String name, String quentity) {
        this.name = name;
        this.quentity = quentity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuentity() {
        return quentity;
    }

    public void setQuentity(String quentity) {
        this.quentity = quentity;
    }
}
