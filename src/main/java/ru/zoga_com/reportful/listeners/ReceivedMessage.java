package ru.zoga_com.reportful.listeners;

import java.util.HashMap;
import java.util.Map;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.zoga_com.reportful.TaskManager;
import ru.zoga_com.reportful.commands.History;
import ru.zoga_com.reportful.commands.Report;
import ru.zoga_com.reportful.misc.Database;
import ru.zoga_com.reportful.types.Command;

public class ReceivedMessage extends ListenerAdapter {
    private static Map<String, Command> hash = new HashMap<>(); // тут команды

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!event.isFromType(ChannelType.PRIVATE) && !event.getAuthor().isBot()) { // в этом ифе логгируются сообщения в бд
            if(event.getMessage().getContentRaw().startsWith("/")) { // в этом ифе обрабатываются команды
                String[] args = event.getMessage().getContentRaw().split(" ");
                if(hash.get(args[0]) != null) {
                    hash.get(args[0]).onCommand(args, event.getMessage());
                }
            }
            Runnable task = new Runnable() {
                public void run() {
                    Database.insertQuery("INSERT INTO messages(message,author,time,channel) VALUES('" + event.getMessage().getContentRaw().replaceAll("'", "''") + "','" + event.getMessage().getAuthor().getName().replaceAll("'", "''") + "#" + event.getMessage().getAuthor().getDiscriminator() + "','" + System.currentTimeMillis() + "','" + event.getMessage().getChannel().getName().replaceAll("'", "''") + "')");
                }
            };
            new TaskManager(task);
        }
    }

    public static void initCommands() { // добавление команд в хешмапу
        hash.put(new Report().getCommandName(), new Report());
        hash.put(new History().getCommandName(), new History());
    }
}
