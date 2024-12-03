package com.example.seu_is_18_ict_039;

public class Product {
    private String name;
    private String description;
    private int price;
    private int imageResource;
    private int quantity;

    // Constructor
    // Constructor
    public Product(String name, String description, int price, int imageResource) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = 1; // Default quantity is set to 1
        this.imageResource = imageResource; // Set the correct image resource
    }


    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to increment the quantity
    public void incrementQuantity() {
        this.quantity++;
    }

    // Method to decrement the quantity
    public void decrementQuantity() {
        if (this.quantity > 1) { // Ensure quantity doesn't go below 1
            this.quantity--;
        }
    }
}
