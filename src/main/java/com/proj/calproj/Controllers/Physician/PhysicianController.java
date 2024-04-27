package com.proj.calproj.Controllers.Physician;

import com.proj.calproj.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.proj.calproj.Views.PhysicianMenuOptions.*;

public class PhysicianController implements Initializable {

    public AnchorPane physician_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Model.getInstance().getViewFactory().getPhysicianSelectedMenuItem().addListener(((observableValue, oldVal, newVal) -> {
            Model.getInstance().getViewFactory().getScheduleView();

            switch (SCHEDULE) {
                case SCHEDULE -> Model.getInstance().getViewFactory().getScheduleView();
            }

        }));
    }

}
