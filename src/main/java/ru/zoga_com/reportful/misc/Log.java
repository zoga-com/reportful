package ru.zoga_com.reportful.misc;

public class Log {
    public static void printException(String ex) { // лог ошибок
        System.out.println("[Ошибка] " + ex);
    }

    public static void printMessage(String msg) { // лог просто информации
        System.out.println("[Информация] " + msg);
    }
}
