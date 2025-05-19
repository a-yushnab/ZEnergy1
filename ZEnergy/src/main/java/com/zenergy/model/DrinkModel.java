package com.zenergy.model;

public class DrinkModel {
    private int drinkId;
    private String brandName;
    private String drinkName;
    private double price;
    private String flavor;
    private int calorie;
    private String username;
    private String imagePath;

    public DrinkModel() {}

    public DrinkModel(int drinkId, String brandName, String drinkName, double price, String flavor, int calorie, String username, String imagePath) {
        this.drinkId = drinkId;
        this.brandName = brandName;
        this.drinkName = drinkName;
        this.price = price;
        this.flavor = flavor;
        this.calorie = calorie;
        this.username = username;
        this.imagePath = imagePath;
    }

    // Constructor without drinkId for insertion
    public DrinkModel(String brandName, String drinkName, double price, String flavor, int calorie, String username, String imagePath) {
        this.brandName = brandName;
        this.drinkName = drinkName;
        this.price = price;
        this.flavor = flavor;
        this.calorie = calorie;
        this.username = username;
        this.imagePath = imagePath;
    }

    // Getters and setters
    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
