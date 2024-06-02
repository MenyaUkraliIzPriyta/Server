package org.example.ProcessingRequests;



import org.example.CitiesPackage.City;

import java.io.Serializable;

public class Response implements Serializable {

    private String message;
    private City c = null;
    private int num;

    public Response(String message) {
        this.message = message;
    }
    public Response(City c, int num, String message) {
        this.c = c;
        this.num = num;
        this.message = message;
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