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

    public void createPatient(String fName, String lName, String uname, String pword, String gen, String bDate, LocalDate regDate, String add, String notes) {
      //  System.out.println("fName: " +fName + " lName: " + lName + " username: " +username + " password: " + password + " gender: " + gender + " birthdate: "+birthDate +  " address: "+address);
        Statement statement;
        try {
            statement = this.conn.createStatement();
//            statement.executeUpdate("INSERT INTO Patients (FirstName, LastName, Username, Password, Gender, BirthDate, Address)" +
//                    "VALUES ('"+fName +"', '"+lName+"', '"+uname+"', '"+pword+"', '"+gen+"', "+bDate+"', '"+add+"');");

            statement.executeUpdate("Insert INTO Patients (FirstName, LastName, Username, Password, Gender, Birthdate, RegisterDate, Address, Notes)" +
                    "VALUES ('"+fName +"', '"+ lName+"', '"+ uname+"', '"+ pword+"', '"+ gen+"', '"+ bDate+"', '"+ regDate.toString()+"', '"+ add+"', '"+ notes+"');");

          //  String query = "INSERT INTO Patients '"+ id.getText() +"' , username='"+ username.getText() + "', password='"+ pass.getText() +"', firstname='"+ fname.getText() +"', lastname='"+ lname.getText() +"' WHERE ID='"+ id.getText() +"'  ";

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
            e.printStackTrace();
        }
        return resultSet;
    }

    public String deletePatient(String username) {
        Statement statement;
        Statement statement2;
        ResultSet resultSet;
        String deletedUsername = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Patients WHERE Username ='"+username+"';");
            statement2 = this.conn.createStatement();
            int deletedRows = statement2.executeUpdate("DELETE FROM Patients WHERE Username ='"+ username+"';");
            deletedUsername = resultSet.getString("Username");
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
            resultSet = statement2.executeQuery("SELECT * FROM Patients WHERE Username ='"+username+"';");
        //    deletedUsername = resultSet.getString("Username");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }
}
