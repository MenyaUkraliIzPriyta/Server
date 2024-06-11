package org.example.ProcessingRequests;


import org.example.CitiesPackage.City;


import java.io.Serializable;

public class InputData implements Serializable {

    private String message;
    private City c = null;
    private int num;
    private String username = "";
    private String password;
    private boolean checkregistation;

    public InputData(String message, boolean checkregistation) {
        this.message = message;
        this.checkregistation = checkregistation;
    }
    public InputData(City c, int num, String message, boolean checkregistation) {
        this.c = c;
        this.num = num;
        this.message = message;
        this.checkregistation = checkregistation;
    }
    public InputData(String username, String password, String message) {
        this.username = username;
        this.password = password;
        this.message = message;
    }


    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String getMessage() {
        return message;
    }

    public City getCity() {
        return c;
    }

    public int getnum() {
        return num;
    }
    public boolean getregistration() {
        return checkregistation;
    }
}