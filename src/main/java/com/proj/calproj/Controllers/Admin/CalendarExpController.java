package com.proj.calproj.Controllers.Admin;

import com.proj.calproj.Models.Appointment;
import com.proj.calproj.Models.Model;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

public class CalendarExpController implements Initializable {

    public Text year;
    public Text month;
    public FlowPane calendar;
    public Button forward_btn;
    public Button backward_btn;
    ZonedDateTime dateFocus;
    ZonedDateTime today;


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

//    private void drawCalendar(){
//        year.setText(String.valueOf(dateFocus.getYear()));
//        month.setText(String.valueOf(dateFocus.getMonth()));
//
//        double calendarWidth = calendar.getPrefWidth();
//        double calendarHeight = calendar.getPrefHeight();
//        double strokeWidth = 1;
//        double spacingH = calendar.getHgap();
//        double spacingV = calendar.getVgap();
//
//        //List of activities for a given month
//       // Map<Integer, List<CalendarExpActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);
//        Map<Integer, List<Appointment>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);
//
//        int monthMaxDate = dateFocus.getMonth().maxLength();
//        //Check for leap year
//        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
//            monthMaxDate = 28;
//        }
//        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();
//
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 7; j++) {
//
//                StackPane stackPane = new StackPane();
//                Rectangle rectangle = new Rectangle();
//                rectangle.setFill(Color.TRANSPARENT);
//                rectangle.setStroke(Color.BLACK);
//                rectangle.setStrokeWidth(strokeWidth);
//                double rectangleWidth = (calendarWidth/7) - strokeWidth - spacingH;
//                rectangle.setWidth(rectangleWidth);
//                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
//                rectangle.setHeight(rectangleHeight);
//                stackPane.getChildren().add(rectangle);
//
//                int calculatedDate = (j+1)+(7*i);
//
//                if(calculatedDate > dateOffset){
//                    int currentDate = calculatedDate - dateOffset;
//                    if(currentDate <= monthMaxDate){
//                        Text date = new Text(String.valueOf(currentDate));
//                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
//                        date.setTranslateY(textTranslationY);
//                        stackPane.getChildren().add(date);
//                        List<CalendarExpActivity> calendarActivities = calendarActivityMap.get(currentDate);
//                        if(calendarActivities != null) {
//                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
//                        }
//                    }
//                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
//                        rectangle.setStroke(Color.BLUE);
//                    }
//                }
//                calendar.getChildren().add(stackPane);
//            }
//        }
//    }

    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();
        String strDayOfMonth = null;

        //List of activities for a given month
        // Map<Integer, List<CalendarExpActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);


        Map<String, List<Appointment>> calendarAppointmentMap = getCalendarActivitiesMonth(dateFocus);

        System.out.println("calendarAppointmentMap.values() - in drawCalendar() " + calendarAppointmentMap.values());
        System.out.println("calendarAppointmentMap.keySet() - in drawCalendar() " + calendarAppointmentMap.keySet());
        System.out.println("calendarAppointmentMap.get(03)" + calendarAppointmentMap.get("05"));

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }

        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        System.out.println("dateOffset: " + dateOffset);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {

                StackPane stackPane = new StackPane();
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/4.5) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);

                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        System.out.println("date inside if: " + date);
                        stackPane.getChildren().add(date);

                        if (String.valueOf(currentDate).length()<=1) {
                            strDayOfMonth = "0" + (String.valueOf(currentDate));
                        } else {
                            strDayOfMonth = String.valueOf(currentDate);
                        }

                        List<Appointment> calendarActivities = calendarAppointmentMap.get(strDayOfMonth);
                        System.out.println("calendarActivityMap.get(strDayOfMonth).get(strDayOfMonth): " + calendarAppointmentMap.get(strDayOfMonth));
                        System.out.println("calenderActivities: " + calendarActivities);
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
        for (int k = 0; k < calendarActivities.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
          //  Text text = new Text(calendarActivities.get(k).patUsernameProperty() + ", " + calendarActivities.get(k).appTimeProperty());
            Text text = new Text(calendarActivities.get(k).patUsernameProperty());
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
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }

//    private void createCalendarActivity(List<CalendarExpActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
//        VBox calendarActivityBox = new VBox();
//        for (int k = 0; k < calendarActivities.size(); k++) {
//            if(k >= 2) {
//                Text moreActivities = new Text("...");
//                calendarActivityBox.getChildren().add(moreActivities);
//                moreActivities.setOnMouseClicked(mouseEvent -> {
//                    //On ... click print all activities for given date
//                    System.out.println(calendarActivities);
//                });
//                break;
//            }
//            Text text = new Text(calendarActivities.get(k).getClientName() + ", " + calendarActivities.get(k).getDate().toLocalTime());
//            calendarActivityBox.getChildren().add(text);
//            text.setOnMouseClicked(mouseEvent -> {
//                //On Text clicked
//                System.out.println(text.getText());
//            });
//        }
//        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
//        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
//        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
//        calendarActivityBox.setStyle("-fx-background-color:GRAY");
//        stackPane.getChildren().add(calendarActivityBox);
//    }

    private Map<String, List<Appointment>> createCalendarMap(List<Appointment> appointmentList) {

        Map<String, List<Appointment>> calendarAppointmentMap = new HashMap<>();

        for (Appointment appointment: appointmentList) {

            //  int activityDate = activity.getDate().getDayOfMonth();
            // Integer.parseInt(appointment.appDayOfMonthProperty().toString())
            if(!calendarAppointmentMap.containsKey(appointment.appDayOfMonthProperty().getValue())){
//                System.out.println("createCalendarMap ");
//                System.out.println("calendarAppointmentMap.keySet() before put " + calendarAppointmentMap.keySet());
                calendarAppointmentMap.put((appointment.appDayOfMonthProperty().getValue()), List.of(appointment));
//                System.out.println("appointment.appDayOfMonthProperty().getValue vgybhu " + appointment.appDayOfMonthProperty().getValue());
//                System.out.println("calendarAppointmentMap.keySet() after put " + calendarAppointmentMap.keySet());
            } else {
                List<Appointment> OldListByDate = calendarAppointmentMap.get(appointment.appDayOfMonthProperty().getValue());
                List<Appointment> newList = new ArrayList<>(OldListByDate);
                newList.add(appointment);
                calendarAppointmentMap.put((appointment.appDayOfMonthProperty().getValue()), newList);
            }
        }

        return  calendarAppointmentMap;
    }

//    private Map<Integer, List<CalendarExpActivity>> createCalendarMap(List<CalendarExpActivity> calendarActivities) {
//
//        Map<Integer, List<CalendarExpActivity>> calendarActivityMap = new HashMap<>();
//        for (CalendarExpActivity activity: calendarActivities) {
//            int activityDate = activity.getDate().getDayOfMonth();
//            if(!calendarActivityMap.containsKey(activityDate)){
//                calendarActivityMap.put(activityDate, List.of(activity));
//            } else {
//                List<CalendarExpActivity> OldListByDate = calendarActivityMap.get(activityDate);
//                List<CalendarExpActivity> newList = new ArrayList<>(OldListByDate);
//                newList.add(activity);
//                calendarActivityMap.put(activityDate, newList);
//            }
//        }
//        return  calendarActivityMap;
//    }

//    private Map<Integer, List<CalendarExpActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
//        List<CalendarExpActivity> calendarActivities = new ArrayList<>();
//        int year = dateFocus.getYear();
//        int month = dateFocus.getMonth().getValue();
//        String strDateFocus = dateFocus.toString();
//        String strMonthYear = strDateFocus.substring(0, 7);
//        String strDayOfMonth = strDateFocus.substring(8, 10);
//        System.out.println("strDayOfMonth: " + strDayOfMonth);
//
//         List<Appointment> searchResults = Model.getInstance().searchAppByMonthAndYear(strMonthYear);

//        Random random = new Random();
//        for (int i = 0; i < 50; i++) {
//            ZonedDateTime time = ZonedDateTime.of(year, month, random.nextInt(27)+1, 16,0,0,0,dateFocus.getZone());
//            calendarActivities.add(new CalendarExpActivity(time, "Hans", 111111));
//            System.out.println(time);
//        }
//
//        return createCalendarMap(calendarActivities);
//        return createCalendarMap(searchResults, strDateFocus);
//    }


    private Map<String, List<Appointment>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        // List<Appointment> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();
        String strDateFocus = dateFocus.toString();
        String strMonthYear = strDateFocus.substring(0, 7);
        String strDayOfMonth = strDateFocus.substring(8, 10);


        List<Appointment> searchResults = Model.getInstance().searchAppByMonthAndYear(strMonthYear);
//        Appointment appointment = searchResults.get(1);
//        System.out.println("appointment.patUsernameProperty()" + appointment.patUsernameProperty());

        // System.out.println("searchResults:$$$ " + searchResults.get(8));

//        for (int i = 0; i < 15; i++) {
//
//        }
//        Random random = new Random();
//        for (int i = 0; i < 50; i++) {
//            ZonedDateTime time = ZonedDateTime.of(year, month, random.nextInt(27)+1, 16,0,0,0,dateFocus.getZone());
//            calendarActivities.add(new CalendarExpActivity(time, "Hans", 111111));
//            System.out.println(time);
//        }
//
//        return createCalendarMap(calendarActivities);
        return createCalendarMap(searchResults);
    }



}