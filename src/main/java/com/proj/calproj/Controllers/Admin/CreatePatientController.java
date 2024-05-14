package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
import com.proj.calproj.Views.AccountType;
import com.proj.calproj.Views.PhysicianOptions;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreatePatientController implements Initializable {

    public Button create_patient_btn;
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public TextField username_fld;
    public TextField gender_fld;
    public TextField birth_date_fld;
    public TextField address_fld;
    public Label error_lbl;
    public CheckBox username_box;
    public Label username_lbl;
    public TextArea notes_ta;
    public TextField assignedPhysician_fld;
    public ChoiceBox physician_selector;
    private String username;
    private boolean createPatientFlag = false;

    private String assignedPhysician;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        create_patient_btn.setOnAction(event -> createPatient());
        physician_selector.setItems(FXCollections.observableArrayList(PhysicianOptions.MJohnson, PhysicianOptions.CBlake, PhysicianOptions.DKnight, PhysicianOptions.MSchlenger, PhysicianOptions.SRedstone, PhysicianOptions.JMellon));

        username_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                username = createPatientUsername();
                onCreatePatientUsername();
            }
        });
    }

    private void setPhysician_selector() {
        if (physician_selector.getValue() == PhysicianOptions.MJohnson) {
            assignedPhysician = PhysicianOptions.MJohnson.toString();
        } else if (physician_selector.getValue() == PhysicianOptions.JMellon) {
            assignedPhysician = PhysicianOptions.JMellon.toString();
        } else if (physician_selector.getValue() == PhysicianOptions.DKnight) {
            assignedPhysician = PhysicianOptions.DKnight.toString();
        } else if (physician_selector.getValue() == PhysicianOptions.MSchlenger) {
            assignedPhysician = PhysicianOptions.MSchlenger.toString();
        } else if (physician_selector.getValue() == PhysicianOptions.CBlake) {
            assignedPhysician = PhysicianOptions.CBlake.toString();
        } else if (physician_selector.getValue() == PhysicianOptions.SRedstone) {
            assignedPhysician = PhysicianOptions.SRedstone.toString();
        }

    }

    private String createPatientUsername() {
        int id = Model.getInstance().getDatabaseDriver().getLastPatientsId() + 1;
        char fChar = Character.toLowerCase(fName_fld.getText().charAt(0));
        return fChar+lName_fld.getText()+id;
    }

    private void onCreatePatientUsername() {
        if(fName_fld.getText() != null & lName_fld.getText() != null){
            username_lbl.setText(username);
        }
    }

    private void createPatient() {
        String fName = fName_fld.getText();
        String lName = lName_fld.getText();
      //  String username = username_lbl.getText();
        String password = password_fld.getText();
        String gender = gender_fld.getText();
        String birthDate = birth_date_fld.getText();
        String address = address_fld.getText();
        String notes = notes_ta.getText();
        physician_selector.valueProperty().addListener(observable -> setPhysician_selector());

        Model.getInstance().getDatabaseDriver().createPatient(fName, lName, username, password, gender, birthDate, LocalDate.now(), address, notes, assignedPhysician);
        Model.getInstance().setPatients();
        Model.getInstance().getPatients();
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_lbl.setText("Patient Created Successfully");
        emptyFields();
    }

    private void emptyFields() {
        fName_fld.setText("");
        lName_fld.setText("");
        password_fld.setText("");
        gender_fld.setText("");
        birth_date_fld.setText("");
        address_fld.setText("");
        username_lbl.setText("");
        notes_ta.setText("");
        assignedPhysician_fld.setText("");
        username_box.setSelected(false);

    }

}
