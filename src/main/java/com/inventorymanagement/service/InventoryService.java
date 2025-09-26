package com.inventorymanagement.service;

import com.inventorymanagement.dao.ProductDAO;
import com.inventorymanagement.exception.DatabaseException;
import com.inventorymanagement.exception.InvalidInputException;
import com.inventorymanagement.exception.ProductNotFoundException;
import com.inventorymanagement.product.Product;

import java.sql.Connection;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryService {

    private final ProductDAO dao;

    public InventoryService(Connection conn) { this.dao = new ProductDAO(conn); }

    public void addProduct(Scanner sc) throws InvalidInputException, DatabaseException {
        int id, qty;
        double price;
        String name;

        try {
            System.out.print("Enter ID: "); id = sc.nextInt(); sc.nextLine();
            System.out.print("Enter Name: "); name = sc.nextLine();
            System.out.print("Enter Quantity: "); qty = sc.nextInt();
            System.out.print("Enter Price: "); price = sc.nextDouble();
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new InvalidInputException("Invalid input type!");
        }

        if (id <= 0 || qty < 0 || price < 0)
            throw new InvalidInputException("ID, Quantity, and Price must be positive!");

        Product p = new Product(id, name, qty, price);
        dao.addProduct(p);
        System.out.println("Product Added!");
    }

    public void removeProduct(Scanner sc) throws ProductNotFoundException, DatabaseException {
        System.out.print("Enter ID to remove: ");
        int id = sc.nextInt();
        boolean removed = dao.removeProduct(id);
        if (!removed) throw new ProductNotFoundException("Product not found!");
        System.out.println("Product Removed!");
    }

    public void searchProduct(Scanner sc) throws ProductNotFoundException, DatabaseException {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();
        Product p = dao.getProductById(id);
        if (p == null) throw new ProductNotFoundException("Product not found!");
        System.out.println("Product Found: " + p);
    }

    public void viewAllProducts() throws DatabaseException {
        List<Product> products = dao.getAllProducts();
        System.out.println("\nAll Products:");
        for (Product p : products) System.out.println(p);
    }
}
