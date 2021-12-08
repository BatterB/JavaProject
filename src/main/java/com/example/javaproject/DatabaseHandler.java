package com.example.javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERS_ID + "," + Const.USERS_PASSWORD + "," + Const.USERS_TYPE + ")" +
                "VALUES(?,?,?)";

        try {
            PreparedStatement prSt = getDbConnetion().prepareStatement(insert);
            prSt.setString(1,user.getUsername());
            prSt.setString(2,user.getPassword());
            prSt.setString(3,user.getAccountType());

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
                Const.USERS_ID + "=? AND "+ Const.USERS_PASSWORD + "=?";

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
}
