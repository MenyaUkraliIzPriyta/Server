package org.example.Basic_comm;


import org.example.CitiesPackage.City;
import org.example.CitiesPackage.CityManager;
import org.example.Examination.IsInt;

public class RemoveAt extends Command {
    protected String get(String element) {
        if (IsInt.isInt(element)) {
            if ((Integer.parseInt(element) >= CityManager.getCollection().size()) || (Integer.parseInt(element) < 0)) {
                return "Коллекция не содержит элемент с данным индексом";
            } else {
                for (int i = 0; i != CityManager.getCollection().size(); i++) {
                    if (i == Integer.parseInt(element)) {
                        City delete = CityManager.getCollection().get(i);
                        CityManager.getCollection().remove(delete);
                        return "Элемент успешно удален";
                    }
                }
            }
        }
        return "Введены неверные данные.";
    }
}