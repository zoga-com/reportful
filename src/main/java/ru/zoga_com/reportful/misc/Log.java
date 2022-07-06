package ru.zoga_com.reportful.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.zoga_com.reportful.TaskManager;

public class Log {
    public static void printException(String ex, Class<?> initBy) { // лог ошибок
        Runnable task = new Runnable() {
            public void run() {
                Logger logger = LoggerFactory.getLogger(initBy);
                logger.error(ex);
            }
        };
        new TaskManager(task);
    }

    public static void printMessage(String msg, Class<?> initBy) { // лог просто информации
        Runnable task = new Runnable() {
            public void run() {
                Logger logger = LoggerFactory.getLogger(initBy);
                logger.info(msg);
            }
        };
        new TaskManager(task);
    }
}
