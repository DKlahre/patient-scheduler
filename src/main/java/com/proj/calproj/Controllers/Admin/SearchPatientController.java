package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
import com.proj.calproj.Models.Patient;
import com.proj.calproj.Views.PatientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SearchPatientController implements Initializable {
    public TextField username_fld;
    public TextField lastName_fld;
    public Button usernameSearch_btn;
    public Button lastNameSearch_btn;
    public Button birthDateSearch_btn;
    public ListView<Patient> patient_listview;
    public DatePicker birthDate_pkr;
    public Label error_msg_lbl;
    private Patient patient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//         ListView<Patient> patient_listview;
        usernameSearch_btn.setOnAction(event -> onUserNameSearch());
        lastNameSearch_btn.setOnAction(event -> onLastNameSearch());
        birthDateSearch_btn.setOnAction(event -> onBirthDateSearch());
        error_msg_lbl.setText("");
        username_fld.setOnMouseClicked(e -> {
            error_msg_lbl.setText("");
        });
        lastName_fld.setOnMouseClicked(e -> {
            error_msg_lbl.setText("");
        });
        birthDate_pkr.setOnMouseClicked(e -> {
            error_msg_lbl.setText("");
        });
    }

    private void onUserNameSearch() {
        try {
        ObservableList<Patient> searchResults = Model.getInstance().searchPatUsername(username_fld.getText());
        patient_listview.setItems(searchResults);
        patient_listview.setFocusTraversable(false);
        patient_listview.setCellFactory(e -> new PatientCellFactory());
        patient = searchResults.get(0);
        } catch (Exception e) {
            error_msg_lbl.setText("Patient not found");
        }
    }

    private void onLastNameSearch() {
        try {
            ObservableList<Patient> searchResults = Model.getInstance().searchPatLastName(lastName_fld.getText());
            patient_listview.setItems(searchResults);
            patient_listview.setFocusTraversable(false);
            patient_listview.setCellFactory(e -> new PatientCellFactory());
            patient = searchResults.get(0);
        } catch (Exception e) {
            error_msg_lbl.setText("Patient not found");
        }
    }

    private void onBirthDateSearch() {
        try {
            LocalDate myDate = birthDate_pkr.getValue();
            String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ObservableList<Patient> searchResults = Model.getInstance().searchPatBirthDate(myFormattedDate);
            patient_listview.setItems(searchResults);
            patient_listview.setFocusTraversable(false);
            patient_listview.setCellFactory(e -> new PatientCellFactory());
            patient = searchResults.get(0);
        } catch (Exception e) {
            error_msg_lbl.setText("Patient not found");
        }
    }
}
