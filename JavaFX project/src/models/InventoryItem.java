package models;

import java.time.LocalDateTime;

public class InventoryItem {
    private int id;
    private String itemName;
    private int quantity;
    private double price;
    private String createdBy;

    public InventoryItem(int id, String itemName, int quantity, double price, String createdBy) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.createdBy = createdBy;
    }

    public int getId() { return id; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getCreatedBy() { return createdBy; }

    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
}
