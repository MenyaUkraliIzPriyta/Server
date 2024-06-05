package org.example.ProcessingRequests;


import org.example.CitiesPackage.City;


import java.io.Serializable;

public class Response implements Serializable {

    private String message = "";
    private City c = null;
    private int num;
    private String username = "";
    private String password;

    public Response(String message) {
        this.message = message;
    }
    public Response(City c, int num, String message) {
        this.c = c;
        this.num = num;
        this.message = message;
    }
    public Response(String username, String password, String message) {
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
}