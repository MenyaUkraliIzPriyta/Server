package org.example.Basic_comm;

import org.example.CitiesPackage.CityManager;

public class Save extends Command {
    protected String get(String element){
        new CityManager().saveCollection();
        return"Коллекция сохранена в файл";
    }
}