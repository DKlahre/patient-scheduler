package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
import com.proj.calproj.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {

//    public Button set_appointment_btn;
    public Button create_patient_btn;
    public Button logout_btn;
    public Button search_patient_btn;
    public Button patients_btn;
    public Button calendar_btn;
    public Button calendarExp_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        create_patient_btn.setOnAction(event -> onCreatePatient());
        search_patient_btn.setOnAction(event -> onSearchPatient());
        calendar_btn.setOnAction(event -> onCalendar());
        calendarExp_btn.setOnAction(event -> onCalendarExp());
        patients_btn.setOnAction(event -> onPatients());
    }

    private void onCreatePatient() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_PATIENT);
        }

    private void onSearchPatient() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.SEARCH_PATIENT);
        }

    private void onPatients() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.PATIENTS);
    }

    private void onCalendar() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CALENDAR);
    }

    private void onCalendarExp() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CALENDAREXP);
    }

    }

