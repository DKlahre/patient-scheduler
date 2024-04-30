package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
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
    private String username;
    private boolean createPatientFlag = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        create_patient_btn.setOnAction(event -> createPatient());
        username_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                username = createPatientUsername();
                onCreatePatientUsername();
            }
        });
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
        String  address = address_fld.getText();
        String notes = notes_ta.getText();
        Model.getInstance().getDatabaseDriver().createPatient(fName, lName, username, password, gender, birthDate, LocalDate.now(), address, notes);

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
        username_box.setSelected(false);

    }

}
