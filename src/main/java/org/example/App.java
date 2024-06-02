package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main( String[] args ) {
        String url = "jdbc:postgresql://localhost:5432/CityApp";
        String user = "postgres";
        String pass = "online";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            Statement stat = conn.createStatement();

            ResultSet result = stat.executeQuery("select * from landlord");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
