package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case SEARCH_PATIENT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getSearchPatientView());
                case PATIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getPatientsView());
                case CALENDAR -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCalendarView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreatePatientView());
                // case CREATE_PATIENT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreatePatientView());
            }
        } );
    }

}
