package com.proj.calproj.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

    private final StringProperty physUsername;
    private final StringProperty patUsername;
    private final StringProperty appDayOfMonth;
    private final StringProperty appMonthAndYear;
    private final StringProperty appTime;

    Appointment (String physUsername, String patUsername, String appDayOfMonth, String appMonthAndYear, String appTime) {

        this.physUsername = new SimpleStringProperty(this, "PhysUsername", physUsername);
        this.patUsername = new SimpleStringProperty(this, "PatUsername", patUsername);
        this.appDayOfMonth = new SimpleStringProperty(this, "AppDayOfTheMonth", appDayOfMonth);
        this.appMonthAndYear = new SimpleStringProperty(this, "AppMonthAndYear", appMonthAndYear);
        this.appTime = new SimpleStringProperty(this, "AppTime", appTime);

    }
    public StringProperty physUsernameProperty() {return physUsername;}
    public StringProperty patUsernameProperty() {return  patUsername;}
    public StringProperty appDayOfMonthProperty() {return appDayOfMonth;}
    public StringProperty appMonthAndYearProperty() {return appMonthAndYear;}
    public StringProperty appTimeProperty() {return appTime;}
}