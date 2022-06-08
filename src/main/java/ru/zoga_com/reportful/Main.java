package ru.zoga_com.reportful;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import ru.zoga_com.reportful.listeners.ReceivedMessage;
import ru.zoga_com.reportful.misc.Config;
import ru.zoga_com.reportful.misc.Database;
import ru.zoga_com.reportful.misc.Env;
import ru.zoga_com.reportful.misc.Log;

public class Main {
    private static JDA jda;

    public static void main(String[] args) {
        try {
            Config.initConfig();; // просто инициализация конфига
            ReceivedMessage.initCommands(); // заполнение хешмапы с командами
            Database.startDB(); // запуск подключения к бд
            JDABuilder botBuilder = JDABuilder.createDefault(Env.TOKEN); // сборка и запуск бота
            botBuilder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE, CacheFlag.ONLINE_STATUS);
            botBuilder.setBulkDeleteSplittingEnabled(false);
            botBuilder.setCompression(Compression.NONE);
            botBuilder.addEventListeners(new ReceivedMessage());
            botBuilder.setActivity(Activity.competing("количестве репортов."));
            jda = botBuilder.build();
            jda.awaitReady();
        } catch(LoginException | InterruptedException e) { Log.printException("Бот не смог авторизоваться. Проверьте правильность токена бота в bot.config\n"); e.printStackTrace(); }
    }

    public static JDA getJDA() {
        return jda; // тут просто передача инстанса jda 
    }
}