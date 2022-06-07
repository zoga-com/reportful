package ru.zoga_com.reportful.misc;

import java.util.Calendar;

public class Time {
    public static String getDate(Calendar calendar) { // переделка в нормальную дату
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf((calendar.get(Calendar.MONTH) + 1));
        int year = calendar.get(Calendar.YEAR);
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        if(Integer.parseInt(minute) < 10) {
            minute = "0" + minute;
        }
        if(Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        if(Integer.parseInt(hour) < 10) {
            hour = "0" + hour;
        }
        if(Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        return day + "." + month + "." + year + " в " + hour + ":" + minute;
    }
}
