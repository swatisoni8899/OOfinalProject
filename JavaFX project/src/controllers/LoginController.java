package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.User;
import models.UserDAO;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void handleLogin() {
        String u = usernameField.getText().trim();
        String p = passwordField.getText().trim();

        User user = UserDAO.login(u, p);
        if (user == null) {
            messageLabel.setText("Invalid username/password.");
            return;
        }

        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();

            if (user.isAdmin()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdminDashboard.fxml"));
                Scene scene = new Scene(loader.load());
                AdminDashboardController controller = loader.getController();
                controller.setUser(user);
                stage.setScene(scene);
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserDashboard.fxml"));
                Scene scene = new Scene(loader.load());
                UserDashboardController controller = loader.getController();
                controller.setUser(user);
                stage.setScene(scene);
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Error loading dashboard.");
        }
    }
    
    @FXML
    private void handleClear() {
        usernameField.clear();
        passwordField.clear();
        messageLabel.setText("");
        usernameField.requestFocus();
    }
}
