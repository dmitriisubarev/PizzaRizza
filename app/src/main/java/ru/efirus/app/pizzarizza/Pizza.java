package ru.efirus.app.pizzarizza;

/**
 * Created by efirus on 08.03.18.
 */

public class Pizza {

    private String name;
    private String description;
    private String size;
    private int price;
    private String image;

    public Pizza() {
    }

    public Pizza(String name, String description, String size, int price, String image) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
