//package org.example;
//
//import org.example.Basic_comm.CheckRegistration;
//import org.example.Basic_comm.Registration;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class App {
//    public static void main(String[] args) {
//        String url = "jdbc:postgresql://localhost:5432/studs";
//        String user = "s408936";
//        String pass = "ahY8Ti0pPYkLJ84R";
//
//        try {
//            Class.forName("org.postgresql.Driver");
//            Connection conn = DriverManager.getConnection(url, user, pass);
//
//            String insertSQL = "INSERT INTO users_client (username, password_hash) VALUES (?, ?)";
//            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
//
//            String username = "rEER3";
//            String passwordHash = "234242";
//
//            // Установка значений для подстановки в запрос
//            pstmt.setString(1, username);
//            pstmt.setString(2, passwordHash);
//
//            int rows = pstmt.executeUpdate();
//            System.out.printf("Added %d rows", rows);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
