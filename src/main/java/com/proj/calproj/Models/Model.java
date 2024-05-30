package com.proj.calproj.Models;

import com.proj.calproj.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private boolean physicianLoginSuccessFlag;
    private boolean adminLoginSuccessFlag;
    private final DatabaseDriver databaseDriver;
    private  ObservableList<Patient> patients;


    public Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.adminLoginSuccessFlag = false;
        this.patients = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance() {
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public static synchronized Model getNewInstance() {
        model = new Model();
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void evaluatePhysicianCred(String username, String password) {
        ResultSet resultSet = databaseDriver.getPhysicianData(username, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.physicianLoginSuccessFlag = true;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setPatients(){
        ResultSet resultSet = databaseDriver.getAllPatientsData();
        try {
            while (resultSet.next()) {
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                String gender = resultSet.getString("Gender");
                String[] birthDateParts = resultSet.getString("BirthDate").split("-");
                LocalDate birthDate = LocalDate.of(Integer.parseInt(birthDateParts[0]), Integer.parseInt(birthDateParts[1]), Integer.parseInt(birthDateParts[2]));
                String[] registerDateParts = resultSet.getString("RegisterDate").split("-");
                LocalDate registerDate = LocalDate.of(Integer.parseInt(registerDateParts[0]), Integer.parseInt(registerDateParts[1]), Integer.parseInt(registerDateParts[2]));
                String address = resultSet.getString("Address");
                String notes = resultSet.getString("Notes");
                String physician = resultSet.getString("Physician");
                patients.add(new Patient(fName, lName, username, password, gender, birthDate, registerDate, address, notes, physician));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearPatientsList(){
        ResultSet resultSet = null;

        try {
            patients.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Patient> getPatients() {
        return patients;
    }

    public void evaluateAdminCred(String username, String password) {
        ResultSet resultSet = databaseDriver.getAdminData(username, password);
        try {
            if (resultSet.isBeforeFirst()){
                this.adminLoginSuccessFlag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Patient> searchPatUsername(String username) {
        ObservableList<Patient> searchResult = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchPatUsername(username);
        try {
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String password = resultSet.getString("Password");
            String gender = resultSet.getString("Gender");
            String[] birthDateParts = resultSet.getString("BirthDate").split("-");
            LocalDate patBirthDate = LocalDate.of(Integer.parseInt(birthDateParts[0]), Integer.parseInt(birthDateParts[1]), Integer.parseInt(birthDateParts[2]));
            String[] registerDateParts = resultSet.getString("RegisterDate").split("-");
            LocalDate patRegisterDate = LocalDate.of(Integer.parseInt(registerDateParts[0]), Integer.parseInt(registerDateParts[1]), Integer.parseInt(registerDateParts[2]));
            String patAddress = resultSet.getString("Address");
            String patNotes = resultSet.getString("Notes");
            String physician = resultSet.getString("Physician");
            searchResult.add(new Patient(fName, lName, username, password, gender, patBirthDate, patRegisterDate, patAddress, patNotes, physician));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    public List<Patient> searchStrPatUsername(String username) {
        List<Patient> searchResult = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchPatUsername(username);
        try {
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String password = resultSet.getString("Password");
            String gender = resultSet.getString("Gender");
            String[] birthDateParts = resultSet.getString("BirthDate").split("-");
            LocalDate patBirthDate = LocalDate.of(Integer.parseInt(birthDateParts[0]), Integer.parseInt(birthDateParts[1]), Integer.parseInt(birthDateParts[2]));
            String[] registerDateParts = resultSet.getString("RegisterDate").split("-");
            LocalDate patRegisterDate = LocalDate.of(Integer.parseInt(registerDateParts[0]), Integer.parseInt(registerDateParts[1]), Integer.parseInt(registerDateParts[2]));
            String patAddress = resultSet.getString("Address");
            String patNotes = resultSet.getString("Notes");
            String physician = resultSet.getString("Physician");
            searchResult.add(new Patient(fName, lName, username, password, gender, patBirthDate, patRegisterDate, patAddress, patNotes, physician));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    public ObservableList<Patient> searchPatUsernameEdit(String username, String notes) {
        ObservableList<Patient> searchResult = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchPatUsernameEdit(username, notes);
        try {
            String fName = resultSet.getString("FirstName");

            String lName = resultSet.getString("LastName");
            String password = resultSet.getString("Password");
            String gender = resultSet.getString("Gender");
            String[] birthDateParts = resultSet.getString("BirthDate").split("-");
            LocalDate patBirthDate = LocalDate.of(Integer.parseInt(birthDateParts[0]), Integer.parseInt(birthDateParts[1]), Integer.parseInt(birthDateParts[2]));
            String[] registerDateParts = resultSet.getString("RegisterDate").split("-");
            LocalDate patRegisterDate = LocalDate.of(Integer.parseInt(registerDateParts[0]), Integer.parseInt(registerDateParts[1]), Integer.parseInt(registerDateParts[2]));
            String patAddress = resultSet.getString("Address");
            String patNotes = resultSet.getString("Notes");
            String physician = resultSet.getString("Physician");
            if (notes != null) {
                patNotes = notes;
            }
            searchResult.setAll(new Patient(fName, lName, username, password, gender, patBirthDate, patRegisterDate, patAddress, patNotes, physician));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    public ObservableList<Patient> searchPatLastName(String lastName) {
        ObservableList<Patient> searchResult = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchPatLastName(lastName);
        try {
            String fName = resultSet.getString("FirstName");
            String username = resultSet.getString("Username");
            String password = resultSet.getString("Password");
            String gender = resultSet.getString("Gender");
            String[] birthDateParts = resultSet.getString("BirthDate").split("-");
            LocalDate patBirthDate = LocalDate.of(Integer.parseInt(birthDateParts[0]), Integer.parseInt(birthDateParts[1]), Integer.parseInt(birthDateParts[2]));
            String[] registerDateParts = resultSet.getString("RegisterDate").split("-");
            LocalDate patRegisterDate = LocalDate.of(Integer.parseInt(registerDateParts[0]), Integer.parseInt(registerDateParts[1]), Integer.parseInt(registerDateParts[2]));
            String patAddress = resultSet.getString("Address");
            String patNotes = resultSet.getString("Notes");
            String physician = resultSet.getString("Physician");
            searchResult.add(new Patient(fName, lastName, username, password, gender, patBirthDate, patRegisterDate, patAddress, patNotes, physician));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    public ObservableList<Patient> searchPatBirthDate(String birthDate) {
        ObservableList<Patient> searchResult = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchPatBirthDate(birthDate);
        try {
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String username = resultSet.getString("Username");
            String password = resultSet.getString("Password");
            String gender = resultSet.getString("Gender");
            String[] birthDateParts = resultSet.getString("BirthDate").split("-");
            LocalDate patBirthDate = LocalDate.of(Integer.parseInt(birthDateParts[0]), Integer.parseInt(birthDateParts[1]), Integer.parseInt(birthDateParts[2]));
            String[] registerDateParts = resultSet.getString("RegisterDate").split("-");
            LocalDate patRegisterDate = LocalDate.of(Integer.parseInt(registerDateParts[0]), Integer.parseInt(registerDateParts[1]), Integer.parseInt(registerDateParts[2]));
            String patAddress = resultSet.getString("Address");
            String patNotes = resultSet.getString("Notes");
            String physician = resultSet.getString("Physician");
            searchResult.add(new Patient(fName, lName, username, password, gender, patBirthDate, patRegisterDate, patAddress, patNotes, physician));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public boolean getPhysicianLoginSuccessFlag() {
        return this.physicianLoginSuccessFlag;
    }

    public boolean getAdminLoginSuccessFlag() {
        return this.adminLoginSuccessFlag;
    }

    public List<Appointment> searchAppByMonthAndYear(String monthAndYear) {
     //   System.out.println("monthAdnYear(Model): " + monthAndYear);
        List<Appointment> searchResult = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchAppMonthYear(monthAndYear);
//        ResultSet resultSet2;
        try {
            while (resultSet.next()) {
                String physUsername = resultSet.getString("PhysUsername");
             //   System.out.println("physUsername: whooi " + physUsername);
                String patUsername = resultSet.getString("PatUsername");
             //   System.out.println("patUsername: whooi " + patUsername);
                String appDayOfMonth = resultSet.getString("AppDayOfMonth");
             //   System.out.println("appDayOfMonth: " + appDayOfMonth);
                String appMonthAndYear = resultSet.getString("AppMonthAndYear");
             //   System.out.println("appMonthAndYear: " + appMonthAndYear);
                String appTime = resultSet.getString("AppTime");
             //   System.out.println("appTime: " + appTime);
                searchResult.add(new Appointment(physUsername, patUsername, appDayOfMonth, appMonthAndYear, appTime));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
    }

}