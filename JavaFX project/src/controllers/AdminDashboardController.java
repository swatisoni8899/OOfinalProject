package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.InventoryDAO;
import models.InventoryItem;
import models.User;

public class AdminDashboardController {

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
        welcomeLabel.setText("Welcome, " + user.getUsername() + " (ADMIN)");
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
    private void handleUpdateSelected() {
        InventoryItem selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Select a row to update.");
            return;
        }

        try {
            String name = itemNameField.getText().trim();
            int qty = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            boolean ok = InventoryDAO.updateItem(selected.getId(), name, qty, price);
            statusLabel.setText(ok ? "Updated item ID " + selected.getId() : "Update failed.");
            refresh();
        } catch (Exception e) {
            statusLabel.setText("Enter valid values to update.");
        }
    }

    @FXML
    private void handleDeleteSelected() {
        InventoryItem selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Select a row to delete.");
            return;
        }

        boolean ok = InventoryDAO.deleteItem(selected.getId());
        statusLabel.setText(ok ? "Deleted item ID " + selected.getId() : "Delete failed.");
        refresh();
    }

    @FXML
    private void handleRefresh() {
        refresh();
    }

    private void refresh() {
        tableView.setItems(InventoryDAO.getAllItems());
    }
}
