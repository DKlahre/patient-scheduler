package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
import com.proj.calproj.Models.Patient;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchPatientController implements Initializable {
    public TextField username_fld;
    public TextField lastName_fld;
    public TextField birthDate_fld;
    public Button usernameSearch_btn;
    public Button lastNameSearch_btn;
    public Button birthDateSearch_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameSearch_btn.setOnAction(event -> onUserNameSearch());
        lastNameSearch_btn.setOnAction(event -> onLastNameSearch());
        birthDateSearch_btn.setOnAction(event -> onBirthDateSearch());
    }

    private void onUserNameSearch() {
        ObservableList<Patient> searchResults = Model.getInstance().searchPatUsername(username_fld.getText());
    }

    private void onLastNameSearch() {
        ObservableList<Patient> searchResults = Model.getInstance().searchPatLastName(lastName_fld.getText());
    }

    private void onBirthDateSearch() {
        ObservableList<Patient> searchResults = Model.getInstance().searchPatBirthDate(birthDate_fld.getText());
    }
}
