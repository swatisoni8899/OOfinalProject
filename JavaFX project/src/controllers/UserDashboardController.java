package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.InventoryDAO;
import models.InventoryItem;
import models.User;

public class UserDashboardController {

    private User user;

    @FXML private Label welcomeLabel;
    @FXML private Label statusLabel;

    @FXML private TextField itemNameField;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;

    @FXML private TableView<InventoryItem> tableView;
    @FXML private TableColumn<InventoryItem, Integer> idCol;
    @FXML private TableColumn<InventoryItem, String> nameCol;
    @FXML private TableColumn<InventoryItem, Integer> qtyCol;
    @FXML private TableColumn<InventoryItem, Double> priceCol;
    @FXML private TableColumn<InventoryItem, String> createdByCol;

    public void setUser(User user) {
        this.user = user;
        welcomeLabel.setText("Welcome, " + user.getUsername() + " (USER)");
        setupTable();
        refresh();
    }

    private void setupTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
    }

    @FXML
    private void handleAddItem() {
        try {
            String name = itemNameField.getText().trim();
            int qty = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (name.isEmpty()) {
                statusLabel.setText("Item name cannot be empty.");
                return;
            }

            boolean ok = InventoryDAO.addItem(name, qty, price, user.getUsername());
            statusLabel.setText(ok ? "Item added." : "Failed to add item.");
            refresh();
        } catch (Exception e) {
            statusLabel.setText("Enter valid quantity and price.");
        }
    }

    @FXML
    private void handleRefresh() {
        refresh();
    }

    private void refresh() {
        tableView.setItems(InventoryDAO.getAllItems());
    }
}
