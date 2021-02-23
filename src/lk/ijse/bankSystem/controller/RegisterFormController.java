package lk.ijse.bankSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bankSystem.db.DBConnection;

import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.sql.*;

public class RegisterFormController {
    public AnchorPane root;
    public TextField txtFullName;
    public TextField txtAccountNumber;
    public TextField txtUserName;
    public TextField txtPass;
    public TextField txtConfPass;
    public Button btnRegister;
    public Label lblUserName;
    public Button btnLogin;

    public void initialize(){
        lblUserName.setVisible(false);
        btnLogin.setDisable(true);

        txtUserName.textProperty().addListener((observable, oldValue, newValue) ->{
            Connection con=DBConnection.getInstance().getConnection();
            try {
                PreparedStatement stm=con.prepareStatement("SELECT user_name FROM bank.accountholder WHERE user_name=?");
                stm.setObject(1,newValue);
                ResultSet resultSet = stm.executeQuery();
                if (resultSet.next()){
                    lblUserName.setVisible(true);
                }else {
                    lblUserName.setVisible(false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } );
    }


    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        String fullName = txtFullName.getText();
        String username = txtUserName.getText();
        //String acc = txtAccountNumber.getText();
        String password = txtPass.getText();
        String repeatPassword = txtConfPass.getText();

        if (fullName.trim().isEmpty()) {
            txtFullName.requestFocus();
            return;
        }

        if (username.trim().isEmpty()) {
            txtUserName.requestFocus();
            return;
        }

        if (password.trim().isEmpty() || repeatPassword.trim().isEmpty() || !password.equals(repeatPassword)) {
            txtPass.requestFocus();
            return;
        }



        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO bank.accountholder (full_name, user_name, password) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setObject(1, fullName);
            stm.setObject(2, username);
            stm.setObject(3, password);
            int affectedRows = stm.executeUpdate();
            if (affectedRows > 0) {
               // navigation("/lk/ijse/bankSystem/view/LoginForm.fxml");
                txtFullName.setDisable(true);
                txtUserName.setDisable(true);
                txtPass.setDisable(true);
                txtConfPass.setDisable(true);

                btnLogin.setDisable(false);
                btnRegister.setDisable(true );
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                txtAccountNumber.setText(generateAccountNumber(generatedKeys.getInt(1)));
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact DEPPO", ButtonType.OK).show();
            }
        }else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, ", ButtonType.OK).show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong,", ButtonType.OK).show();
            e.printStackTrace();
        }
    }

    private String generateAccountNumber(int accountNumber) {
        if (accountNumber < 10) {
            return "AC - 000" + accountNumber;
        } else if (accountNumber < 100) {
            return "AC - 00" + accountNumber;
        } else if (accountNumber < 1000) {
            return "AC - 0" + accountNumber;
        }else {
            return "AC - " + accountNumber;
        }
    }

    public void lblOnAction(MouseEvent mouseEvent) throws IOException {
        navigation("/lk/ijse/bankSystem/view/LoginForm.fxml");
    }

    private void navigation(String path) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource(path))));
        stage.centerOnScreen();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        navigation("/lk/ijse/bankSystem/view/LoginForm.fxml/");
    }
/*
    private void userDuplicate() throws SQLException, IOException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * from bank.accountholder where user_name=?");

        ResultSet resultSet = stm.executeQuery();
        boolean isExist = txtUserName.getText().equals(resultSet);
        if (resultSet.next()) {
            new Alert(Alert.AlertType.ERROR, "UserName Already Exist!").show();
        } else {
            navigation("/lk/ijse/bankSystem/view/LoginForm.fxml");
        }
    }*/
}

