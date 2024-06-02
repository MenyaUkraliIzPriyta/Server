package org.example.Basic_comm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Execute {

    private Map<String, Command> commandMap = new HashMap<>();

    {
        commandMap.put("help", (new Help()));
        commandMap.put("info", (new Info()));
        commandMap.put("show", (new Show()));
        commandMap.put("update_id", new UpdateId());
        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("clear", new Clear());
        commandMap.put("exit", new Exit());
        commandMap.put("save", new Save());
        commandMap.put("remove_at", new RemoveAt());
        commandMap.put("sum_of_meters_above_sea_level", new SumOfMetersAboveSeaLevel());
        commandMap.put("count_greater_than_car_code", new CountGreaterThanCarCode());
    }

    public String executeCommand(String text) throws IOException {
        String[] parts = text.split(" ", 2);
        String command = parts[0];
        String element = parts.length > 1 ? parts[1] : "";
        Map<String, Command> map = new HashMap<>();

//        map.put("insert_at", new InsertAt());
//        map.put("execute_script", new Script());

        if (!commandMap.containsKey(command)) {
            return "Неизвестная команда. Введите 'help' для справки.";
        }
        return commandMap.get(command).get(element);
    }
}
