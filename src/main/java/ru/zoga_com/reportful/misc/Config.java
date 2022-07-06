package ru.zoga_com.reportful.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties props = new Properties();

    public static void initConfig() { // генерация и пожгрузка конфига в программу
        try {
            createConfigIfNotExists();
            FileInputStream fStream = new FileInputStream("bot.config");
            props.load(fStream);
        } catch(IOException e) { Log.printException(e.getMessage(), Config.class); }
    }

    public static String getProp(String key) { // 
        return props.getProperty(key);
    }

    private static void createConfigIfNotExists() throws IOException { // сгенерировать конфиг
        File config = new File("bot.config");
        if(!config.exists()) {
            config.createNewFile();
            fillConfig();
        }
    }

    private static void fillConfig() throws IOException {
        FileWriter fw = new FileWriter("bot.config");
        fw.write("token=CHANGEME\n");
        fw.write("receiveChannel=0\n");
        fw.write("reportColor=#ffffff\n");
        fw.close();
    }
}
