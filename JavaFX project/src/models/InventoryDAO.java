package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InventoryDAO {

    public static ObservableList<InventoryItem> getAllItems() {
        ObservableList<InventoryItem> items = FXCollections.observableArrayList();
        String sql = "SELECT id, item_name, quantity, price, created_by FROM inventory ORDER BY id";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                items.add(new InventoryItem(
                    rs.getInt("id"),
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("created_by")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public static boolean addItem(String name, int qty, double price, String createdBy) {
        String sql = "INSERT INTO inventory (item_name, quantity, price, created_by) VALUES (?,?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, qty);
            ps.setDouble(3, price);
            ps.setString(4, createdBy);

            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateItem(int id, String name, int qty, double price) {
        String sql = "UPDATE inventory SET item_name=?, quantity=?, price=? WHERE id=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, qty);
            ps.setDouble(3, price);
            ps.setInt(4, id);

            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteItem(int id) {
        String sql = "DELETE FROM inventory WHERE id=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
