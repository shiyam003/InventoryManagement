package com.inventory;

import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManagement {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Search Product");
            System.out.println("4. View All Products");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    products.add(new Product(id, name, qty, price));
                    System.out.println("Product Added Successfully!");
                    break;

                case 2:
                    System.out.print("Enter Product ID to Remove: ");
                    int removeId = sc.nextInt();
                    boolean removed = products.removeIf(p -> p.getId() == removeId);
                    System.out.println(removed ? "Product Removed!" : "Product Not Found!");
                    break;

                case 3:
                    System.out.print("Enter Product ID to Search: ");
                    int searchId = sc.nextInt();
                    boolean found = false;
                    for(Product p : products){
                        if(p.getId() == searchId){
                            System.out.println("Product Found: " + p);
                            found = true;
                            break;
                        }
                    }
                    if(!found) System.out.println("Product Not Found!");
                    break;

                case 4:
                    System.out.println("\nAll Products:");
                    for(Product p : products){
                        System.out.println(p);
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while(choice != 5);

        sc.close();
    }
}

