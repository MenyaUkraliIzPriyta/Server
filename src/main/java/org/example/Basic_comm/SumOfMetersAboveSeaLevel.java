package org.example.Basic_comm;


import org.example.CitiesPackage.City;
import org.example.CitiesPackage.CityManager;

public class SumOfMetersAboveSeaLevel extends Command {
    protected String get(String element) {
        double sum = 0;
        for (City cities : CityManager.getCollection()) {
            sum += cities.getMetersAboveSeaLevel();
        }
        return "Сумма: " + sum;

    }
}

