package com.proj.calproj.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Date;

public class Patient {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty gender;
    private final ObjectProperty<LocalDate> birthDate;
    private final ObjectProperty<LocalDate> registerDate;
    private final StringProperty address;
    private final StringProperty notes;

    public Patient(String patFirstName, String patLastName, String patUsername, String patPassword, String patGender, LocalDate patBirthDate, LocalDate patRegisterDate, String patAddress, String patNotes) {
        this.firstName = new SimpleStringProperty(this, "FirstName", patFirstName);
        this.lastName = new SimpleStringProperty(this, "LastName", patLastName);
        this.username = new SimpleStringProperty(this, "Username", patUsername);
        this.password = new SimpleStringProperty(this, "Password", patPassword);
        this.gender = new SimpleStringProperty(this, "Gender", patGender);
        this.birthDate = new SimpleObjectProperty<>(this, "BirthDate", patBirthDate);
        this.registerDate = new SimpleObjectProperty<>(this, "RegisterDate", patRegisterDate);
        this.address = new SimpleStringProperty(this, "Address", patAddress);
        this.notes = new SimpleStringProperty(this, "Notes", patNotes);
    }

    public StringProperty firstNameProperty() {return firstName;}
    public StringProperty lastNameProperty() {return lastName;}
    public StringProperty usernameProperty() {return username;}
    public StringProperty passwordProperty() {return password;}
    public StringProperty genderProperty() {return gender;}
    public ObjectProperty<LocalDate> birthDateProperty() {return birthDate;};
    public ObjectProperty<LocalDate> registerDateProperty() {return registerDate;}
    public StringProperty addressProperty() {return address;}
    public StringProperty notesProperty() {return notes;}





}
