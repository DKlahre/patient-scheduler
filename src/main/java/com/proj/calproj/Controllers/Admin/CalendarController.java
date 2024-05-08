package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.DatabaseDriver;
import com.proj.calproj.Models.Model;
import com.proj.calproj.Models.Patient;
import com.proj.calproj.Models.Person;
import com.proj.calproj.Views.PatientCellFactory;
import com.proj.calproj.Views.RawPatientCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

  //  public ListView<Patient> patients_cal_listview;


  public TableColumn firstName_col;
  public TableColumn lastName_col;
  public TableColumn username_col;
  public TableColumn password_col;
  public TableColumn gender_col;
  public TableColumn birthDate_col;
  public TableColumn registerDate_col;
  public TableColumn address_col;
  public TableColumn notes_col;
  public TableView patientTableView;
  public TableView nTable;
  public TableColumn nFirstName_col;
  public TableColumn nLastName_col;
  public TableColumn nEmail_col;

  private  Patient patient;


  private ObservableList<Patient> data2;
  private static Connection con;
  private static Statement stat;
  private PreparedStatement prep;
  DatabaseDriver db;
  ResultSet rs = null;
  String[] birthDateParts;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

      initPatientsList();
   //   otherStuff();
      otherStuff2();

  }

  private void addListeners() {

  }

  private void initPatientsList() {
     if (Model.getInstance().getPatients().isEmpty()) {
      Model.getInstance().setPatients();
    }
  }

  private void otherStuff2(){

    patientTableView.setEditable(true);
    data2 = FXCollections.observableArrayList();

    try{
      patientTableView.setItems(Model.getInstance().getPatients());
      firstName_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
      lastName_col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
      username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
      password_col.setCellValueFactory(new PropertyValueFactory<>("password"));
      gender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
      birthDate_col.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
      registerDate_col.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
      address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
      notes_col.setCellValueFactory(new PropertyValueFactory<>("notes"));
      while(rs.next()){
        data2.add(new Patient(
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("Username"),
                rs.getString("Password"),
                rs.getString("Gender"),
                rs.getDate("BirthDate").toLocalDate(),
                rs.getDate("RegisterDate").toLocalDate(),
                rs.getString("Address"),
                rs.getString("Notes")
        ));
      }

     // patientTableView.setItems(data2);
      Model.getInstance().setPatients();


     // firstName_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));




    }catch (Exception e){
      e.printStackTrace();;
      System.out.println("error");
    }
  }

  private void otherStuff() {

    patientTableView.setEditable(true);

    LocalDate bDate = LocalDate.of(Integer.parseInt("2012"), Integer.parseInt("03"), Integer.parseInt("10"));
    final ObservableList<Patient> data = FXCollections.observableArrayList(
            new Patient("Dan", "Claire", "DClaire", "fjag73$df", "male", bDate,LocalDate.now(), "131 Old St. Kuberville, MS 12323", "Growth around nostril. Previously removed through salicylic acid. Requires surgery for permanent removal."),
            new Patient("Bob", "Claire", "BClaire", "fjag7$df", "male", bDate, LocalDate.now(), "131 Old St. Kubeille, MS 12323", "Growth around tril. Previously removed through salicylic acid. Requires surgery for permanent removal."),
            new Patient("Dn", "Clare", "DClaie", "fjg73$df", "male", bDate, LocalDate.now(), "131 Old St. Kuerville, MS 12323", "Growth arond nostril. Previously removed through salicylic acid. Requires surgery for permanent removal.")
    );
    patientTableView.setItems(data);

      firstName_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
      lastName_col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
      username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
      password_col.setCellValueFactory(new PropertyValueFactory<>("password"));
      gender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
      birthDate_col.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
      registerDate_col.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
      address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
      notes_col.setCellValueFactory(new PropertyValueFactory<>("notes"));
  }
}
