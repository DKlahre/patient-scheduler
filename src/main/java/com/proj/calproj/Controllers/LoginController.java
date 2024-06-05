package com.proj.calproj.Controllers;

import com.proj.calproj.Models.Model;
import com.proj.calproj.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public ChoiceBox<AccountType> login_type_selector;
    public Label username_lbl;
    public TextField username_fld;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_type_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN, AccountType.PHYSICIAN));
        login_type_selector.setValue(Model.getInstance().getViewFactory().getLoginType());
        login_type_selector.valueProperty().addListener(observable -> setLogin_selector());
        login_btn.setOnAction(event -> onLogin());
    }

    private void setLogin_selector() {
        Model.getInstance().getViewFactory().setLoginType(login_type_selector.getValue());
        if (login_type_selector.getValue() == AccountType.ADMIN) {
            username_lbl.setText("Admin Username: ");
        } else {
            username_lbl.setText("Physician Username: ");
        }
    }


    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        if (Model.getInstance().getViewFactory().getLoginType() == AccountType.PHYSICIAN) {
            Model.getInstance().evaluatePhysicianCred(username_fld.getText(), password_fld.getText());
            if (Model.getInstance().getPhysicianLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                username_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No such login credentials");
            }
        } else {
            Model.getInstance().evaluateAdminCred(username_fld.getText(), password_fld.getText());
            if (Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                username_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No such login credentials");
            }




        }

    }
}

