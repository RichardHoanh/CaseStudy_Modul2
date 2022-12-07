package model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int amount;
    private Double price;

    public Product() {
    }

    public Product(Product product) {
        this.id = product.id;;
        this.name = product.name;
        this.price = product.price;
        this.amount = product.amount;
    }

    public Product(int id, String name, int amount, Double price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "model.Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
