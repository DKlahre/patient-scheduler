package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Model;
import com.proj.calproj.Models.Patient;
import com.proj.calproj.Views.AdminMenuOptions;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RawPatientCellController implements Initializable {

    public Label fName_lbl;
    public Label lName_lbl;
    public Label gender_lbl;
    public Label bDate_lbl;
    public Label address_lbl;
    public Label notes_lbl;
    public Button delete_btn;
    private final Patient patient;
    public TableView pat_table_view;
    public TableColumn firstName_col;
    public TableColumn lastName_col;
    public TableColumn username_col;
    public TableColumn password_col;
    public TableColumn gender_col;
    public TableColumn birthDate_col;
    public TableColumn registerDate_col;
    public TableColumn address_col;
    public TableColumn notes_col;

    public RawPatientCellController(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName_col.textProperty().bind(patient.firstNameProperty());
        lastName_col.textProperty().bind(patient.lastNameProperty());
        username_col.textProperty().bind(patient.genderProperty());
        password_col.textProperty().bind(patient.passwordProperty());
        gender_col.textProperty().bind(patient.addressProperty());
        birthDate_col.textProperty().bind(patient.birthDateProperty().asString());
        registerDate_col.textProperty().bind(patient.registerDateProperty().asString());
        address_col.textProperty().bind(patient.addressProperty());
        notes_col.textProperty().bind(patient.notesProperty());


        TableView<Patient> pat_table_view = new TableView<>();
        LocalDate bDate = LocalDate.of(Integer.parseInt("2012"), Integer.parseInt("03"), Integer.parseInt("10"));
        //   LocalDate bDate = LocalDate.of(year, day, month);
        final ObservableList<Patient> data = FXCollections.observableArrayList(
                new Patient("Dan", "Claire", "DClaire", "fjag73$df", "male", bDate, LocalDate.now(), "131 Old St. Kuberville, MS 12323", "Growth around nostril. Previously removed through salicylic acid. Requires surgery for permanent removal.", "Patricia Murfy"),
                new Patient("Bob", "Claire", "BClaire", "fjag7$df", "male", bDate, LocalDate.now(), "131 Old St. Kubeille, MS 12323", "Growth around tril. Previously removed through salicylic acid. Requires surgery for permanent removal.", "Bella Pegosi"),
                new Patient("Dn", "Clare", "DClaie", "fjg73$df", "male", bDate, LocalDate.now(), "131 Old St. Kuerville, MS 12323", "Growth arond nostril. Previously removed through salicylic acid. Requires surgery for permanent removal.", "Benedict Sideswacker")
        );

        pat_table_view.setItems(data);
        pat_table_view.setEditable(true);

        Label label = new Label("Patient Info");
        label.setFont(new Font("Arial", 20));

        TableColumn column1 = new TableColumn("First Name");
        column1.setMinWidth(20);
        column1.setCellValueFactory(new PropertyValueFactory<>("patFirstName"));

        TableColumn column2 = new TableColumn("First Name");
        column2.setMinWidth(20);
        column2.setCellValueFactory(new PropertyValueFactory<>("patLastName"));

        TableColumn column3 = new TableColumn("Username");
        column3.setMinWidth(20);
        column3.setCellValueFactory(new PropertyValueFactory<>("patUserName"));

        TableColumn column4 = new TableColumn("Password");
        column4.setMinWidth(20);
        column4.setCellValueFactory(new PropertyValueFactory<>("patPassword"));

        TableColumn column5 = new TableColumn("Gender");
        column5.setMinWidth(20);
        column5.setCellValueFactory(new PropertyValueFactory<>("patGender"));

        TableColumn column6 = new TableColumn("Birth Date");
        column6.setMinWidth(20);
        column6.setCellValueFactory(new PropertyValueFactory<>("patBirthDate"));

        TableColumn column7 = new TableColumn("Register Date");
        column7.setMinWidth(20);
        column7.setCellValueFactory(new PropertyValueFactory<>("patRegisterDate"));

        TableColumn column8 = new TableColumn("Address");
        column8.setMinWidth(20);
        column8.setCellValueFactory(new PropertyValueFactory<>("patAddress"));

        TableColumn column9 = new TableColumn("Notes");
        column9.setMinWidth(20);
        column9.setCellValueFactory(new PropertyValueFactory<>("patNotes"));

        TableColumn column10 = new TableColumn("Physician");
        column10.setMinWidth(20);
        column10.setCellValueFactory(new PropertyValueFactory<>("patAssignedPhysician"));

        pat_table_view.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10);

        TextField firstName = new TextField();
        firstName.setMaxWidth(column1.getPrefWidth());
        firstName.setPromptText("First Name");

        TextField lastName = new TextField();
        lastName.setMaxWidth(column2.getPrefWidth());
        lastName.setPromptText("Last Name");

        TextField username = new TextField();
        username.setMaxWidth(column3.getPrefWidth());
        firstName.setPromptText("Username");

        TextField password = new TextField();
        password.setMaxWidth(column4.getPrefWidth());
        password.setPromptText("Password");

        TextField gender = new TextField();
        gender.setMaxWidth(column4.getPrefWidth());
        gender.setPromptText("Gender");

        TextField birthDate = new TextField();
        birthDate.setMaxWidth(column5.getPrefWidth());
        birthDate.setPromptText("Birth Date");

        TextField registerDate = new TextField();
        registerDate.setMaxWidth(column5.getPrefWidth());
        registerDate.setPromptText("Register Date");

        TextField address = new TextField();
        address.setMaxWidth(column5.getPrefWidth());
        address.setPromptText("Address");

        TextField notes = new TextField();
        notes.setMaxWidth(column9.getPrefWidth());
        notes.setPromptText("Notes");

        TextField physician = new TextField();
        physician.setMaxWidth(column10.getPrefWidth());
        physician.setPromptText("Physician");




    }


}