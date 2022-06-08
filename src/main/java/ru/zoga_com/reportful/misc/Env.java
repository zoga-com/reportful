package ru.zoga_com.reportful.misc;

public class Env { // класс с хранилищем констант
    public static String TOKEN = Config.getProp("token");
    public static String RECEIVE_CHANNEL = Config.getProp("receiveChannel");
    public static String REPORT_COLOR = Config.getProp("reportColor");
}
