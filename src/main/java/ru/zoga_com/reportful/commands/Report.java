package ru.zoga_com.reportful.commands;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import ru.zoga_com.reportful.Main;
import ru.zoga_com.reportful.misc.Config;
import ru.zoga_com.reportful.misc.Database;
import ru.zoga_com.reportful.misc.Log;
import ru.zoga_com.reportful.misc.Time;
import ru.zoga_com.reportful.types.Command;

public class Report implements Command {
    private static String commandName = "/report"; // имя команды (на его основе будут обновы ещё)
    private Config config = new Config();

    public void onCommand(String[] args, Message msg) {
        try {
            if(args.length >= 3) {
                if(args[1].toString().startsWith("<@")) {
                    List<String> argsList = new ArrayList<>();
                    argsList.addAll(Arrays.asList(args));
                    argsList.remove(args[0]);
                    argsList.remove(args[1]);
                    String reason = argsList.toString().replace(",", "").replace("[", "").replace("]", "");
                    String message = "";
                    ResultSet userLastMessage = Database.getQuery("SELECT * FROM (SELECT * FROM messages WHERE author = '" + msg.getMentions().getUsers().get(0).getName().replaceAll("'", "''") + "#" + msg.getMentions().getUsers().get(0).getDiscriminator() + "' ORDER BY id DESC LIMIT 5)Var1 ORDER BY id ASC;");
                    for(int i = 1; i <= 5; i++) {
                        if(!userLastMessage.isClosed()) {
                            Calendar date = Calendar.getInstance(); 
                            date.setTimeInMillis(userLastMessage.getLong("time"));
                            message = message + "#" + userLastMessage.getString("channel") + " [" + Time.getDate(date) + "] " + userLastMessage.getString("message") + "\n";
                            userLastMessage.next();
                        }
                    }
                    userLastMessage.close();
                    Database.insertQuery("INSERT INTO reports(message,author,reported,time,channel) VALUES('" + reason.replaceAll("'", "''") + "','" + msg.getAuthor().getName().replaceAll("'", "''") + "#" + msg.getAuthor().getDiscriminator() + "','" + msg.getMentions().getUsers().get(0).getName().replaceAll("'", "''") + "#" + msg.getMentions().getUsers().get(0).getDiscriminator() + "','" + System.currentTimeMillis() + "','" + msg.getChannel().getName().replaceAll("'", "''") + "')");
                    msg.getChannel().sendMessage("Репорт зарегистрирован.").queue();
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.CYAN);
                    embed.setDescription("**Репорт на " + args[1] + "**\n\nПричина: " + reason + "\nТег подавшего репорт пользователя: " + msg.getAuthor().getName().replaceAll("'", "''") + "#" + msg.getAuthor().getDiscriminator() + "\n\n\nПоследние 5 сообщений нарушителя: \n" + message);
                    Main.getJDA().getTextChannelById(config.getReceiveChannel()).sendMessageEmbeds(embed.build()).queue();
                } else { 
                    msg.getChannel().sendMessage("Вы не упомянули нарушителя.").queue();
                }
            } else {
                msg.getChannel().sendMessage("Команда использована неверно.\n\nПравильное использование: /report @<нарушитель> <причина>.").queue();
            }
        } catch(Exception e) { e.printStackTrace(); }
    }

    public String getCommandName() {
        return commandName;
    } 
}
