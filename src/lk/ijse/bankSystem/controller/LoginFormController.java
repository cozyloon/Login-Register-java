package lk.ijse.bankSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bankSystem.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane root;
    public TextField txtName;
    public TextField txtPass;

    private void navigation(String path) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource(path))));
        stage.centerOnScreen();
    }

    public void txtOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        loginViaPstm();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        loginViaPstm();
    }

    private void loginViaPstm() throws  IOException {

        try {
            Connection con= DBConnection.getInstance().getConnection();
            PreparedStatement stm=con.prepareStatement("SELECT full_name, balance, user_name FROM bank.accountholder where user_name=? AND password=?");
            stm.setObject(1,txtName.getText().trim());
            stm.setObject(2,txtPass.getText().trim());

            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()){

                Stage primaryStage = (Stage) txtName.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/ijse/bankSystem/view/DashBoardForm.fxml"));
                Parent root = fxmlLoader.load();
                DashBoardFormController  controller = fxmlLoader.getController();
                controller.setAccountHolderDetails(resultSet.getString("full_name"),resultSet.getString("balance"),resultSet.getString("user_name"));
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
                primaryStage.centerOnScreen();
            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid Credentials !").show();
                txtName.requestFocus();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void lblOnAction(MouseEvent mouseEvent) throws IOException {
        navigation("/lk/ijse/bankSystem/view/RegisterForm.fxml");

    }
}
