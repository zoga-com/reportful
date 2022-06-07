package ru.zoga_com.reportful.types;

import net.dv8tion.jda.api.entities.Message;

public interface Command { // это чисто интерфейс под имплемент команд
    String getCommandName();
    void onCommand(String[] str, Message msg);
}
