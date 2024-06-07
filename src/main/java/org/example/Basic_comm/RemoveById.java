package org.example.Basic_comm;


import org.example.CitiesPackage.City;
import org.example.CitiesPackage.CityManager;
import org.example.Examination.IsInt;

public class RemoveById extends Command { // RemoveById
    protected String get(String element){
        if (IsInt.isInt(element)) {
            int flag = 0;
            for (int i = 0; i != CityManager.getCollection().size(); i++) {
                if (CityManager.getCollection().get(i).getId() == Integer.parseInt(element)) {
                    if (CityManager.getCollection().get(i).getClinet_id()== Registration.getId()) {
                        flag = 1;
                        City delete = CityManager.getCollection().get(i);
                        CityManager.getCollection().remove(delete);
                    }
                }
            }
            if (flag == 1) {
                return "Элемент удален";
            } else {
                return "Элемент не найден";
            }
        }
        else {
            return "Введены неверные данные.";
        }
    }
}
