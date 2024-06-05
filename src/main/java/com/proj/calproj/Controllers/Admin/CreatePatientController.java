package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
import com.proj.calproj.Views.AccountType;
import com.proj.calproj.Views.PhysicianOptions;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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
    public ChoiceBox physician_selector;
    private String username;
    private String assignedPhysician;
    private LocalDate myDate;
    public DatePicker birthDate_pkr;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ReentrantLock lock = new ReentrantLock();
        Condition waitCondition = lock.newCondition();
        create_patient_btn.setOnAction(event -> {
                createPatient();
        });
        fName_fld.setOnMouseClicked(e -> {
            error_lbl.setText("");
        });
        lName_fld.setOnMouseClicked(e -> {
            error_lbl.setText("");
        });
        gender_fld.setOnMouseClicked(e -> {
            error_lbl.setText("");
        });

        physician_selector.setItems(FXCollections.observableArrayList(PhysicianOptions.Dr_Milton_Johnson, PhysicianOptions.Dr_Chris_Blake, PhysicianOptions.Dr_Darren_Knight, PhysicianOptions.Dr_Michael_Schlenger, PhysicianOptions.Dr_Sheldon_Redstone, PhysicianOptions.Dr_Janet_Mellon));
        physician_selector.valueProperty().addListener(observable -> setPhysician_selector());
        username_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                username = createPatientUsername();
                onCreatePatientUsername();
            }
        });
        physician_selector.valueProperty().set(null);

    }

    private void setPhysician_selector() {
        if (physician_selector.getValue() == PhysicianOptions.Dr_Milton_Johnson) {
            assignedPhysician = "MJohnson";
        } else if (physician_selector.getValue() == PhysicianOptions.Dr_Janet_Mellon) {
            assignedPhysician = "JMellon";
        } else if (physician_selector.getValue() == PhysicianOptions.Dr_Darren_Knight) {
            assignedPhysician = "DKnight";
        } else if (physician_selector.getValue() == PhysicianOptions.Dr_Michael_Schlenger) {
            assignedPhysician = "MSchlenger";
        } else if (physician_selector.getValue() == PhysicianOptions.Dr_Chris_Blake) {
            assignedPhysician = "CBlake";
        } else if (physician_selector.getValue() == PhysicianOptions.Dr_Sheldon_Redstone) {
            assignedPhysician = "SRedstone";
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

    private void createPatient()  {

        String fName = fName_fld.getText();
        String lName = lName_fld.getText();
        String password = password_fld.getText();
        String gender = gender_fld.getText();
        myDate = birthDate_pkr.getValue();
        String birthDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String address = address_fld.getText();
        String notes = notes_ta.getText();

        Model.getInstance().getDatabaseDriver().createPatient(fName, lName, username, password, gender, birthDate, LocalDate.now(), address, notes, assignedPhysician);
        Model.getInstance().setPatients();
        Model.getInstance().getPatients();
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        emptyFields();
        error_lbl.setText("Patient Created Successfully");

    }

    private void emptyFields()  {
        fName_fld.setText("");
        lName_fld.setText("");
        password_fld.setText("");
        gender_fld.setText("");
        birthDate_pkr.setValue(null);
        address_fld.setText("");
        username_lbl.setText("");
        notes_ta.setText("");
        username_box.setSelected(false);
        physician_selector.valueProperty().set(null);

    }

}
