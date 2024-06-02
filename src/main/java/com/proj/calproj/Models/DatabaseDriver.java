package com.proj.calproj.Models;


import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Date;

public class DatabaseDriver {

    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:medicaldb.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getPhysicianData(String username, String password ) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Physicians WHERE UserName='" + username + "' AND Password ='" + password +"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public  ResultSet getAdminData(String username, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Admin WHERE Username='" +username+"' AND Password='" +password+"';");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void createPatient(String fName, String lName, String uname, String pword, String gen, String bDate, LocalDate regDate, String add, String notes, String physician) {
        //  System.out.println("fName: " +fName + " lName: " + lName + " username: " +username + " password: " + password + " gender: " + gender + " birthdate: "+birthDate +  " address: "+address);
        Statement statement;
        try {
            statement = this.conn.createStatement();

            statement.executeUpdate("Insert INTO Patients (FirstName, LastName, Username, Password, Gender, Birthdate, RegisterDate, Address, Notes, Physician)" +
                    "VALUES ('"+fName +"', '"+ lName+"', '"+ uname+"', '"+ pword+"', '"+ gen+"', '"+ bDate+"', '"+ regDate.toString()+"', '"+ add+"', '"+ notes+"', '"+ physician+"');");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet searchPatUsername(String username) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Patients Where Username ='"+username+"';");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet searchPatLastName(String lastName) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Patients Where LastName = '"+lastName+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  resultSet;
    }

    public ResultSet searchPatBirthDate(String birthDate) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Patients Where BirthDate = '"+birthDate+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public int getLastPatientsId() {
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sqlite_sequence WHERE name='Patients';");
            id = resultSet.getInt("seq");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ResultSet getAllPatientsData() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Patients;");
        } catch (SQLException e) {
            System.out.println("We got a problem here - getAllPatientsData()");
            e.printStackTrace();
        }
        return resultSet;
    }


    public ResultSet getAllAppointmentsData() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Appointments;");
            System.out.println("Inside: getAllAppointmentsData()");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public String deletePatient(String username) {
        Statement statement;
        Statement statement2;
        Statement statement3;
        Statement statement4;

        ResultSet resultSet;
        ResultSet resultSet2;
        ResultSet resultSet4 = null;
        String deletedUsername = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Patients WHERE Username ='"+username+"';");
            deletedUsername = resultSet.getString("Username");
            statement4 = this.conn.createStatement();
            resultSet4 = statement4.executeQuery("SELECT * FROM Appointments WHERE PatUsername ='"+username+"';");

            if (resultSet4 != null) {
                int deletedRows4 = statement4.executeUpdate("DELETE FROM Appointments WHERE PatUsername ='"+ username+"';");
            }
            statement2 = this.conn.createStatement();
            int deletedRows = statement2.executeUpdate("DELETE FROM Patients WHERE Username ='"+ username+"';");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedUsername;
    }

    public ResultSet searchPatUsernameEdit(String username, String notes) {
        Statement statement;
        Statement statement2;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            int editedRow = statement.executeUpdate("UPDATE Patients SET Notes = '"+notes+"' WHERE Username ='"+ username+"';");
            statement2 = this.conn.createStatement();
            resultSet = statement2.executeQuery("SELECT * FROM Patients WHERE Username ='"+username+" ';");

            //    deletedUsername = resultSet.getString("Username");
        } catch (SQLException e) {
            System.out.println("We got a problem here - searchPatUsernameEdit()");
            e.printStackTrace();
        }
        return resultSet;

    }

    public ResultSet searchAppMonthYear(String monthAndYear){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Appointments WHERE AppMonthAndYear ='"+ monthAndYear +"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}