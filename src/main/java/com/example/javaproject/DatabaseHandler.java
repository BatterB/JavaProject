package com.example.javaproject;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseHandler extends Configs{
    Connection dbConnetion;

    public Connection getDbConnetion() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnetion = DriverManager.getConnection(connectionString, dbUser,dbPass);

        return dbConnetion;
    }

    public void SignUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_ID + "," +
                Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + "," + Const.USERS_TYPE + ")" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnetion().prepareStatement(insert);
            prSt.setInt(1,user.getId());
            prSt.setString(2,user.getUsername());
            prSt.setString(3,user.getPassword());
            prSt.setString(4,user.getAccountType());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " +Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND "+ Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnetion().prepareStatement(select);
            prSt.setString(1,user.getUsername());
            prSt.setString(2,user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
    public int getLastId(String table) {
        ResultSet resSet;
        int id=0;
        try {
            String select ="SELECT MAX(id) AS max_id FROM "+ table;
            PreparedStatement prSt = null;
            prSt = getDbConnetion().prepareStatement(select);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                id = resSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return id;
    }
    public void newAppointment(LocalDate date,String doctorType){
        String insert = "INSERT INTO " + Const.APPOINT_TABLE + "(" + Const.USERS_ID + "," +
                Const.DOCTOR_NAME + "," + Const.DOCTOR_TYPE + "," + Const.DATE_APPOINT + ")" +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnetion().prepareStatement(insert);
            prSt.setInt(1,getLastId("users"));
            prSt.setString(2,doctorType);
            prSt.setString(3,doctorType);
            prSt.setDate(4, Date.valueOf(date));

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
