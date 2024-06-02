package org.example.Basic_comm;



import org.example.CitiesPackage.City;
import org.example.CitiesPackage.CityManager;

import java.util.ArrayList;

public class Show extends Command {
    protected String get(String element) {
        // Получаем коллекцию городов
        ArrayList<City> cities = CityManager.getCollection();

        // Формируем строку с информацией о коллекции
        StringBuilder result = new StringBuilder();
        result.append("Элементы коллекции:\n");
        for (City city : cities) {
            result.append(city).append("\n");
        }

        // Возвращаем строку с информацией о коллекции
        return result.toString();
    }
}
