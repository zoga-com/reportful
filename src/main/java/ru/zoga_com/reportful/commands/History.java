package ru.zoga_com.reportful.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import ru.zoga_com.reportful.Main;
import ru.zoga_com.reportful.misc.Database;
import ru.zoga_com.reportful.misc.Time;
import ru.zoga_com.reportful.types.Command;

public class History implements Command {
    private static String commandName = "/history"; // имя команды (на его основе будут обновы ещё)

    public void onCommand(String[] args, Message msg) {
        try {
            if(args.length == 2) {
                if(args[1].toString().startsWith("<@")) { // проверка что после вызова команды именно тег
                    ResultSet userMessages = Database.getQuery("SELECT * FROM (SELECT * FROM messages WHERE author = '" + msg.getMentions().getUsers().get(0).getName().replaceAll("'", "''") + "#" + msg.getMentions().getUsers().get(0).getDiscriminator() + "' ORDER BY id DESC LIMIT 50)Var1 ORDER BY id ASC;");
                    String messages = "";
                    for(int i = 1; i <= 50; i++) {
                        if(!userMessages.isClosed()) {
                            Calendar date = Calendar.getInstance(); 
                            date.setTimeInMillis(userMessages.getLong("time"));
                            messages = messages + "#" + userMessages.getString("channel") + " [" + Time.getDate(date) + "] " + userMessages.getString("message") + "\n";
                            userMessages.next();
                        }
                    }
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.CYAN);
                    embed.setDescription("**Логи пользователя " + args[1] + "**\nКоличество выводимых строк: 50.\n\n" + messages);
                    Main.getJDA().getTextChannelById(msg.getChannel().getId()).sendMessageEmbeds(embed.build()).queue();
                } else {
                    msg.getChannel().sendMessage("Вы не указали тег.");
                }
            } else {
                msg.getChannel().sendMessage("Команда использована неверно.\n\nПравильное использование: /history @<пользователь>.");
            }
        } catch(SQLException e) { e.printStackTrace(); }
    }

    public String getCommandName() {
        return commandName;
    }
}
