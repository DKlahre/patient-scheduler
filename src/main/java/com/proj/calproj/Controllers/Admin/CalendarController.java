package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.DatabaseDriver;
import com.proj.calproj.Models.Model;
import com.proj.calproj.Models.Patient;
import com.proj.calproj.Models.Person;
import com.proj.calproj.Views.PatientCellFactory;
import com.proj.calproj.Views.RawPatientCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
  private  Patient patient;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    initPatientsList();

    patientTableView.setEditable(true);
    patientTableView.setItems(Model.getInstance().getPatients());
    firstName_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName_col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    gender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
    birthDate_col.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    notes_col.setCellValueFactory(new PropertyValueFactory<>("notes"));
    notes_col.setCellFactory(TextFieldTableCell.forTableColumn());

      notes_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Patient, String>>() {
        @Override
        public void handle(TableColumn.CellEditEvent<Patient, String> cellEditEvent) {
          Patient patient = cellEditEvent.getRowValue();
          patient.notesProperty().setValue(cellEditEvent.getNewValue());
          Model.getInstance().searchPatUsernameEdit(patient.usernameProperty().get(), patient.notesProperty().get());

        }
      });
  }

  private void initPatientsList() {
     if (Model.getInstance().getPatients().isEmpty()) {
      Model.getInstance().setPatients();
    }
  }

}
