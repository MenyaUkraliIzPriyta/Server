package org.example.Basic_comm;


import org.example.CitiesPackage.CityManager;

public class Info extends Command{
    protected String get(String element){
        if (CityManager.getCollection().size() != 0) {
            String sb = "Тип: Cities" + "\n" +
                    "Дата инициализации: " + CityManager.getCollection().get(0).getCreationDate() + "\n" +
                    "Количество элементов: " + CityManager.getCollection().size() + "\n";
            return sb;
        }
        return "Коллекция пуста";
    }
}