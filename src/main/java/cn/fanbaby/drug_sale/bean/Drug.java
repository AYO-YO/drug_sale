package cn.fanbaby.drug_sale.bean;

import java.sql.Date;

public class Drug {
    private int _id;
    private String name;
    private double price;
    private int stock;
    private Date product_date;
    private int shelf_life;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getProduct_date() {
        return product_date;
    }

    public void setProduct_date(Date product_date) {
        this.product_date = product_date;
    }

    public int getShelf_life() {
        return shelf_life;
    }

    public void setShelf_life(int shelf_life) {
        this.shelf_life = shelf_life;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", product_date=" + product_date +
                ", shelf_life=" + shelf_life +
                '}';
    }
}
