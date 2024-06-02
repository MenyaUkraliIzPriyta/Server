package org.example.Basic_comm;
public class Exit extends Command {
    protected String get(String element){
        return "Работа программы завершена без принудительного сохранения";
//        System.exit(0);
    }
}
