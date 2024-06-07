package org.example.Basic_comm;


import org.example.CitiesPackage.City;
import org.example.CitiesPackage.CityManager;

import java.util.Random;

public class UpdateId extends Command{
    protected String get(String element){
        int flag = 0;
        for (City cities : CityManager.getCollection()) {
            if (cities.getName().equals(element)) {
                if (cities.getClinet_id()== Registration.getId()) {
                    flag = 1;
                    Random random_ = new Random();
                    int id_ = random_.nextInt(10000000);
                    cities.setId(id_);
                }
            }
        }
        if (flag == 1) {
            return "id изменен";
        } else {
            return "Элемент не найден";
        }

    }
}