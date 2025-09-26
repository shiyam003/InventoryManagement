package com.inventorymanagement;

import com.inventorymanagement.exception.DatabaseException;
import com.inventorymanagement.exception.InvalidInputException;
import com.inventorymanagement.exception.ProductNotFoundException;
import com.inventorymanagement.service.InventoryService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManagement {

    private static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Shiyam@2205";

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            InventoryService service = new InventoryService(conn);
            int choice = 0;

            do {
                System.out.println("\n--- Inventory Management ---");
                System.out.println("1. Add Product");
                System.out.println("2. Remove Product");
                System.out.println("3. Search Product");
                System.out.println("4. View All Products");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                try { choice = sc.nextInt(); sc.nextLine(); }
                catch (InputMismatchException e) { sc.nextLine(); System.out.println("Invalid input!"); continue; }

                switch (choice) {
                    case 1 -> { try { service.addProduct(sc); } catch (InvalidInputException | DatabaseException e) { System.out.println(e.getMessage()); } }
                    case 2 -> { try { service.removeProduct(sc); } catch (ProductNotFoundException | DatabaseException e) { System.out.println(e.getMessage()); } }
                    case 3 -> { try { service.searchProduct(sc); } catch (ProductNotFoundException | DatabaseException e) { System.out.println(e.getMessage()); } }
                    case 4 -> { try { service.viewAllProducts(); } catch (DatabaseException e) { System.out.println(e.getMessage()); } }
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice! Enter 1-5.");
                }

            } while (choice != 5);

        } catch (SQLException e) { System.out.println("Database connection failed: " + e.getMessage()); }
    }
}
