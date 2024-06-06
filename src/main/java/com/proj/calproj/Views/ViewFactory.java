package com.proj.calproj.Views;

import com.proj.calproj.Controllers.Admin.AdminController;
import com.proj.calproj.Controllers.Physician.PhysicianController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {

    private AccountType loginType;
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private final ObjectProperty<PhysicianMenuOptions> physicianSelectedMenuItem;
    private AnchorPane scheduleView;
    private AnchorPane searchPatientView;
    private AnchorPane createPatientView;
    private AnchorPane patientsView;
    private AnchorPane calendarView;
    private AnchorPane calendarExpView;


    public ViewFactory(){
        this.loginType = AccountType.ADMIN;
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
        this.physicianSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public AccountType getLoginType(){
        return loginType;
    }

    public void setLoginType(AccountType loginType) {
        this.loginType = loginType;

    }

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public ObjectProperty<PhysicianMenuOptions> getPhysicianSelectedMenuItem(){
        return physicianSelectedMenuItem;
    }


    public AnchorPane getScheduleView() {
        if(scheduleView == null) {
            try {
                scheduleView = new FXMLLoader(getClass().getResource("/Fxml/Physician/Physician.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return scheduleView;
    }

    public AnchorPane getSearchPatientView(){
        if (searchPatientView == null) {
            try {
                searchPatientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/SearchPatient.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return searchPatientView;
    }

    public AnchorPane getCreatePatientView() {
        if (createPatientView == null) {
            try {
                createPatientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreatePatient.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createPatientView;
    }

    public AnchorPane getPatientsView() {
        if (patientsView == null){
            try {
                patientsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Patients.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patientsView;
    }

    public AnchorPane getCalendarView() {
//        if (calendarView == null){
            try {
                calendarView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Calendar.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
        return calendarView;
    }

    public AnchorPane getCalendarExpView() {
        if (calendarExpView == null){
            try {
                calendarExpView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CalendarExp.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return calendarExpView;
    }

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }

    public void showPhysicianWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Physician/Physician.fxml"));
        PhysicianController physicianController = new PhysicianController();
        System.out.println("physicianController: " + physicianController);
        loader.setController(physicianController);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/cross-logo.png"))));
        stage.setResizable(false);
        stage.setTitle("Patient Scheduler");
        stage.show();

    }

    public void closeStage (Stage stage) {
        stage.close();
    }
}
