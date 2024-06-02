package org.example.Basic_comm;


import org.example.CitiesPackage.City;
import org.example.CitiesPackage.CityManager;

import java.util.ArrayList;

public class Clear extends Command{
    protected String get(String element){
        while (CityManager.getCollection().size() != 0) {
            City delete = CityManager.getCollection().get(0);
            CityManager.getCollection().remove(delete);
        }
        return "Коллекция отчищена";
//        }
//        if (flag == 1) {
//            return "Коллекция очищена";
//
//        } else {
//            return "Ошибка отчистки коллекции";
//        }
    }
}
