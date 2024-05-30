package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
import com.proj.calproj.Models.Patient;
import com.proj.calproj.Views.PatientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientsController implements Initializable {
    public ListView<Patient> patients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initPatientsList();
        patients_listview.setItems(Model.getInstance().getPatients());
        patients_listview.setCellFactory(e -> new PatientCellFactory());
    }

    private void initPatientsList() {
        if (Model.getInstance().getPatients().isEmpty()){
            Model.getInstance().setPatients();
        }
    }

}
