package org.example.Basic_comm;

import java.sql.*;

public class Registration {
    public Registration() {
    }

    public String get(String username, String passwordHash, String text) {
        String url = "jdbc:postgresql://localhost:5432/CityApp";
        String user = "postgres";
        String pass = "online";
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
                    if (storedPasswordHash.equals(passwordHash)) {
                        CheckRegistration.changeReg();
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
                    pstmt.setString(2, passwordHash);
                    int rows = pstmt.executeUpdate();
                    if (rows > 0) {
                        CheckRegistration.changeReg();
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
}
