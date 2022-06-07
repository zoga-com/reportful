package ru.zoga_com.reportful.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private Properties props = new Properties();

    public Config() { // конструктор
        try {
            createConfigIfNotExists();
            FileInputStream fStream = new FileInputStream("bot.config");
            props.load(fStream);
        } catch(IOException e) { e.printStackTrace(); }
    }

    public String getBotToken() { // возвращает токен
        return props.getProperty("token");
    }

    public String getReceiveChannel() { // вовзвращает канал в который кидать репорты
        return props.getProperty("receiveChannel");
    }

    private void createConfigIfNotExists() throws IOException { // сгенерировать конфиг
        File config = new File("bot.config");
        if(!config.exists()) {
            config.createNewFile();
            fillConfig();
        }
    }

    private void fillConfig() throws IOException {
        FileWriter fw = new FileWriter("bot.config");
        fw.write("token=CHANGEME");
        fw.write("receiveChannel=0");
        fw.close();
    }
}
