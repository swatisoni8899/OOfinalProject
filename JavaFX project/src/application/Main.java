package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Load FXML from: resources/views/LoginView.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));

        Scene scene = new Scene(loader.load(), 900, 550);

        // Load CSS from: resources/views/login.css
        scene.getStylesheets().add(
                getClass().getResource("/views/login.css").toExternalForm()
        );

        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
