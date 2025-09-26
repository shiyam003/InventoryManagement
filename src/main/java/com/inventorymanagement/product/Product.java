package com.inventorymanagement.product;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    // Constructor
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Setters (optional, in case you want to update product)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // ToString method
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Quantity: " + quantity + " | Price: " + price;
    }
}
