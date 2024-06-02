package org.example.Basic_comm;


import org.example.CitiesPackage.City;
import org.example.CitiesPackage.CityManager;

public class CountGreaterThanCarCode extends Command{
    protected String get(String element) {
        int sum = 0;
        for (City cities : CityManager.getCollection()) {
            if (cities.getCarCode() > Long.parseLong(element)) {
                sum++;
            }
        }
        return "Количество элементов: " + sum;
    }
}
