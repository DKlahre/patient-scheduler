package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Appointment;
import com.proj.calproj.Models.Model;
import com.proj.calproj.Models.Patient;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

import static java.lang.Math.random;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.color;


public class CalendarExpController implements Initializable {

    public Text year;
    public Text month;
    public FlowPane calendar;
    public Button forward_btn;
    public Button backward_btn;
    ZonedDateTime dateFocus;
    ZonedDateTime today;

    private Patient patient;

    private String patFirstAndLastName;
    JOptionPane JOptionPane = null;
    //List<String> newCalendarActivities = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        addListeners();
        drawCalendar();
    }

    @FXML
    void backOneMonth() {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth() {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void addListeners(){
        forward_btn.setOnAction(event -> forwardOneMonth());
        backward_btn.setOnAction(event -> backOneMonth());
    }


    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = 1.2;
        double spacingV = calendar.getVgap();
        String strDayOfMonth = null;

        Map<String, List<Appointment>> calendarAppointmentMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }

        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {

                StackPane stackPane = new StackPane();
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/5.2) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);

                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        if (String.valueOf(currentDate).length()<=1) {
                            strDayOfMonth = "0" + (String.valueOf(currentDate));
                        } else {
                            strDayOfMonth = String.valueOf(currentDate);
                        }

                        List<Appointment> calendarActivities = calendarAppointmentMap.get(strDayOfMonth);
                        if(calendarActivities != null) {
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }


    private void createCalendarActivity(List<Appointment> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        VBox calendarExpandBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("  Click for more...");
                moreActivities.setStyle("-fx-font-size: 10px");
                moreActivities.setFill(Color.BLUE);
                calendarExpandBox.getChildren().add(moreActivities);
                calendarActivityBox.getChildren().add(calendarExpandBox);
//                calendarExpandBox.setOnMouseClicked(mouseEvent -> {
//                    System.out.println(calendarActivities.get(0).patUsernameProperty());
//                });
                calendarExpandBox.setOnMouseClicked(mouseEvent -> expandActivities(calendarActivities));
                break;
            }

          //  Text text = new Text(calendarActivities.get(k).patUsernameProperty() + ", " + calendarActivities.get(k).appTimeProperty());
            Text text = new Text(getPatientName(calendarActivities.get(k).patUsernameProperty()));
            Text text2 = new Text( calendarActivities.get(k).appTimeProperty());

            calendarActivityBox.getChildren().add(text);
            calendarActivityBox.getChildren().add(text2);

            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY; -fx-font-size: 9px");
        calendarExpandBox.setStyle("-fx-background-color: #ED6918");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private void expandActivities (List<Appointment> calendarActivities) {
            String stuffString = "";
        for (int i = 0; i < calendarActivities.size(); i ++ ) {
            System.out.println(getPatientName(calendarActivities.get(i).patUsernameProperty()));
            System.out.println(calendarActivities.get(i).appTimeProperty());
            stuffString = stuffString + calendarActivities.get(i).appTimeProperty() + " - " + getPatientName(calendarActivities.get(i).patUsernameProperty()) + "\n" ;
        }
        JOptionPane.showMessageDialog(null, stuffString);
    }

    private Map<String, List<Appointment>> createCalendarMap(List<Appointment> appointmentList) {

        Map<String, List<Appointment>> calendarAppointmentMap = new HashMap<>();

        for (Appointment appointment: appointmentList) {

            if(!calendarAppointmentMap.containsKey(appointment.appDayOfMonthProperty().getValue())){
                calendarAppointmentMap.put((appointment.appDayOfMonthProperty().getValue()), List.of(appointment));
            } else {
                List<Appointment> OldListByDate = calendarAppointmentMap.get(appointment.appDayOfMonthProperty().getValue());
                List<Appointment> newList = new ArrayList<>(OldListByDate);
                newList.add(appointment);
                calendarAppointmentMap.put((appointment.appDayOfMonthProperty().getValue()), newList);
            }
        }

        return  calendarAppointmentMap;
    }

    private Map<String, List<Appointment>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {

        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();
        String strDateFocus = dateFocus.toString();
        String strMonthYear = strDateFocus.substring(0, 7);
        String strDayOfMonth = strDateFocus.substring(8, 10);
        List<Appointment> searchResults = Model.getInstance().searchAppByMonthAndYear(strMonthYear);

        return createCalendarMap(searchResults);
    }

    private String getPatientName(String username) {
        List<Patient> searchResult = Model.getInstance().searchStrPatUsername(username);
        patient = searchResult.get(0);
        String fName = patient.strFirstName();
        String lName = patient.strLastName();
        patFirstAndLastName = fName + " " + lName;

        return patFirstAndLastName;
    }

}