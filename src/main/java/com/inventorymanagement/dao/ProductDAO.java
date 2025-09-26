package com.inventorymanagement.dao;

import com.inventorymanagement.exception.DatabaseException;
import com.inventorymanagement.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final Connection conn;

    public ProductDAO(Connection conn) { this.conn = conn; }

    public void addProduct(Product product) throws DatabaseException {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO products(id, name, quantity, price) VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setInt(3, product.getQuantity());
            ps.setDouble(4, product.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) { throw new DatabaseException(e.getMessage()); }
    }

    public boolean removeProduct(int id) throws DatabaseException {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { throw new DatabaseException(e.getMessage()); }
    }

    public Product getProductById(int id) throws DatabaseException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("quantity"), rs.getDouble("price"));
            }
            return null;
        } catch (SQLException e) { throw new DatabaseException(e.getMessage()); }
    }

    public List<Product> getAllProducts() throws DatabaseException {
        List<Product> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("quantity"), rs.getDouble("price")));
            }
        } catch (SQLException e) { throw new DatabaseException(e.getMessage()); }
        return list;
    }
}
