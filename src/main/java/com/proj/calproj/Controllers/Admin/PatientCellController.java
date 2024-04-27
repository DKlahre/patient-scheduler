package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
import com.proj.calproj.Models.Patient;
import com.proj.calproj.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PatientCellController implements Initializable {

    public Label fName_lbl;
    public Label lName_lbl;
    public Label gender_lbl;
    public Label bDate_lbl;
    public Label address_lbl;
    public Label notes_lbl;
    public Button delete_btn;
    private final Patient patient;

    public PatientCellController(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fName_lbl.textProperty().bind(patient.firstNameProperty());
        lName_lbl.textProperty().bind(patient.lastNameProperty());
        gender_lbl.textProperty().bind(patient.genderProperty());
        bDate_lbl.textProperty().bind(patient.birthDateProperty().asString());
        address_lbl.textProperty().bind(patient.addressProperty());
        notes_lbl.textProperty().bind(patient.notesProperty());
        delete_btn.setOnAction(event -> deleteCheck());
    }

    private void onDelete() {
        String username = patient.usernameProperty().getValue();
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchPatUsername(username);
        try {
            System.out.println("Inside onDelete()");
            System.out.println("username " + username);
            if (resultSet.isBeforeFirst()) {
                Model.getInstance().getDatabaseDriver().deletePatient(username);
                Model.getInstance().setPatientsToNull();
                Model.getInstance().setPatients();
                Model.getInstance().getViewFactory().showAdminWindow();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCheck() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("Do you want to delete this record?");
        alert.setContentText("This cannot be undone.");

        if(alert.showAndWait().get() == ButtonType.OK) {
            onDelete();
            System.out.println("Record successfully deleted");
        }
    }

}
