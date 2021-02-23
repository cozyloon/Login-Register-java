package lk.ijse.bankSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.bankSystem.db.DBConnection;

import java.io.IOException;
import java.sql.SQLException;

public class AppInit extends Application {

    public static void main(String[] args) {
        launch(args);

        try {
           DBConnection.getInstance().getConnection().close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/bankSystem/view/LoginForm.fxml"))));
        primaryStage.show();

    }
}
