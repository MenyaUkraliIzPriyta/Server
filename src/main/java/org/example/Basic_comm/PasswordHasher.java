package org.example.Basic_comm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class PasswordHasher {

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка при хешировании пароля", e);
        }
    }
}

