package lk.ijse.bankSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class DashBoardFormController {
    public AnchorPane root;
    public Label lblBalance;
    public Label lblFullName;
    public Label lblUser;


    public void btnCreditOnAction(ActionEvent actionEvent) {
    }

    public void btnSettingOnAction(ActionEvent actionEvent) {
    }

    public void btnFundOnAction(ActionEvent actionEvent) {
    }
    public void setAccountHolderDetails( String fullName, String balance, String user){
        lblFullName.setText(fullName);
        lblUser.setText(user);
        lblBalance.setText(balance);
    }
}
