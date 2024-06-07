package org.example.Basic_comm;

import java.sql.*;

public class Registration {
    public static String url = "jdbc:postgresql://localhost:5432/CityApp";
    public static String user = "postgres";
    public static String pass = "online";
    public static int client_id;



    public static String get(String username, String passwordHash, String text) {

        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);

            if (text.equals("2")) {
                String checkSQL = "SELECT password_hash FROM users_client WHERE username = ?";
                checkStmt = conn.prepareStatement(checkSQL);
                checkStmt.setString(1, username);
                rs = checkStmt.executeQuery();

                if (rs.next()) {
                    String storedPasswordHash = rs.getString("password_hash");
                    if (storedPasswordHash.equals(new PasswordHasher().hashPassword(passwordHash))) {
                        CheckRegistration.changeRegtrue();
                        setID(username);
                        return "Вход в аккаунт успешно выполнен.";
                    } else {
                        return "Введен неверный пароль.";
                    }
                } else {
                    return "Пользователь с данным именем не зарегистрирован.";
                }
            }

            if (text.equals("1")) {
                String checkSQL = "SELECT COUNT(*) FROM users_client WHERE username = ?";
                checkStmt = conn.prepareStatement(checkSQL);
                checkStmt.setString(1, username);
                rs = checkStmt.executeQuery();

                rs.next();
                int count = rs.getInt(1);
                if (count > 0) {
                    return "Пользователь с таким именем уже зарегистрирован.";
                } else {
                    String insertSQL = "INSERT INTO users_client (username, password_hash) VALUES (?, ?)";
                    pstmt = conn.prepareStatement(insertSQL);
                    pstmt.setString(1, username);
                    pstmt.setString(2, new PasswordHasher().hashPassword(passwordHash));
                    int rows = pstmt.executeUpdate();
                    if (rows > 0) {
                        CheckRegistration.changeRegtrue();
                        setID(username);
                        return "Регистрация прошла успешно!";
                    } else {
                        return "Ошибка при регистрации.";
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (checkStmt != null) checkStmt.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "Введены некорректные данные";
    }

    public static void setID(String username) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            String query = "SELECT client_id FROM users_client WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                client_id = rs.getInt("client_id");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getId() {
        return client_id;
    }
}
