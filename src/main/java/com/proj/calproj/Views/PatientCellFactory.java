package com.proj.calproj.Views;

import com.proj.calproj.Controllers.Admin.PatientCellController;
import com.proj.calproj.Models.Patient;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class PatientCellFactory extends ListCell<Patient> {
    @Override
    protected void updateItem(Patient patient, boolean empty) {

        super.updateItem(patient, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/PatientCell.fxml"));
            PatientCellController controller = new PatientCellController(patient);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
