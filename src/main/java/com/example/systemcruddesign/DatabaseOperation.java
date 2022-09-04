package com.example.systemcruddesign;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseOperation {
    public static void writeToDatabase(String uloginId, String uname, String uemail, String umobile, String upassword) {

        System.out.println("Inside writeToDatabase function!");

        String url = "jdbc:postgresql://localhost:5432/usersystem";
        String user = "postgres";
        String password = "admin26";

        // query
        String query = "INSERT INTO userinfo (loginid, username, email, mobile, password) VALUES(?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, uloginId);
            pst.setString(2, uname);
            pst.setString(3, uemail);
            pst.setString(4, umobile);
            pst.setString(5, upassword);
            pst.executeUpdate();
            System.out.println("Sucessfully created.");

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DatabaseOperation.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public static boolean logInCheck(String uloginid, String upassword) {
        System.out.println("Inside logInCheck function!");
        String url = "jdbc:postgresql://localhost:5432/usersystem";
        String user = "postgres";
        String password = "admin26";

        boolean loginChecker = false;

        // login check query
        String query = "SELECT count(1) FROM userinfo WHERE loginid='" + uloginid + "' AND password='" + upassword + "'";
        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pst = con.prepareStatement(query)) {

            Statement statement = con.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            System.out.println("Working!");

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    System.out.println("Login Check Successful!");
                    loginChecker = true;
                } else {
                    System.out.println("Login Check Not Success!");
                    loginChecker = false;

                }
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DatabaseOperation.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return loginChecker;

    }

    public static ObservableList<Users> getUsersList() throws Exception {
        System.out.println("Inside getUsersList function!");
        ObservableList<Users> userlist = FXCollections.observableArrayList();
        String url = "jdbc:postgresql://localhost:5432/usersystem";
        String user = "postgres";
        String password = "admin26";
        String query = "SELECT loginid,username,email,mobile,password FROM userinfo";
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Users users;
            while (rs.next()) {
                users = new Users(rs.getString("loginid"), rs.getString("username"), rs.getString("email"), rs.getString("mobile"), rs.getString("password"));
                userlist.add(users);
                System.out.println("Inside database!");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userlist;
    }

    public static void deleteUserInfo(String loginid) throws Exception {
        System.out.println("Inside deleteUserInfo function!");
        String url = "jdbc:postgresql://localhost:5432/usersystem";
        String user = "postgres";
        String password = "admin26";
        String query = "DELETE FROM userinfo WHERE loginid='" + loginid + "';";

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updateuserInfo(String loginid, String username, String email) {
        System.out.println("Inside updateuserInfo function!");
        String url = "jdbc:postgresql://localhost:5432/usersystem";
        String user = "postgres";
        String password = "admin26";
        String query = "UPDATE userinfo SET username = '" + username + "', email='" + email + "' WHERE loginid = '" + loginid + "';";
        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
