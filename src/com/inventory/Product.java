package com.inventory;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Product(int id, String name, int quantity, double price){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    // Setter for quantity
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString(){
        return id + " | " + name + " | " + quantity + " | " + price;
    }
}
