package com.zenergy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zenergy.config.DbConfig;
import com.zenergy.model.DrinkModel;

public class ProductService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    public ProductService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    // Get all drinks/products
    public List<DrinkModel> getAllProducts() {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT drink_id, brand_name, drink_name, price, flavor, calorie, username, image_path FROM energy_drink";
        List<DrinkModel> productList = new ArrayList<>();

        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                DrinkModel product = new DrinkModel(
                    result.getInt("drink_id"),
                    result.getString("brand_name"),
                    result.getString("drink_name"),
                    result.getDouble("price"),
                    result.getString("flavor"),
                    result.getInt("calorie"),
                    result.getString("username"),
                    result.getString("image_path")
                );
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return productList;
    }

    // Get a product by ID
    public DrinkModel getProductById(int id) {
        if (isConnectionError) return null;

        String query = "SELECT * FROM energy_drink WHERE drink_id = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new DrinkModel(
                    result.getInt("drink_id"),
                    result.getString("brand_name"),
                    result.getString("drink_name"),
                    result.getDouble("price"),
                    result.getString("flavor"),
                    result.getInt("calorie"),
                    result.getString("username"),
                    result.getString("image_path")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add a new product
    public boolean addProduct(DrinkModel product) {
        if (isConnectionError) return false;

        // Updated SQL query to include image_path
        String query = "INSERT INTO energy_drink (brand_name, drink_name, price, flavor, calorie, username, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, product.getBrandName());
            stmt.setString(2, product.getDrinkName());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getFlavor());
            stmt.setInt(5, product.getCalorie());
            stmt.setString(6, product.getUsername());
            
            // Include the imagePath value in the SQL insert statement
            stmt.setString(7, product.getImagePath());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a product
    public boolean deleteProduct(int productId) {
        if (isConnectionError) return false;

        String query = "DELETE FROM energy_drink WHERE drink_id = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
