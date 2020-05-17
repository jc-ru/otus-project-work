package com.epam.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WorkWithDate {
    final private static Logger logger = LogManager.getLogger(WorkWithDate.class);
    public static Date dateNow = new Date();
    public static SimpleDateFormat formatForDateNow;
    public static Date dateBefore;

    // перевод строки в дату
    public static Date stringToDate(String dateStr) {
        try {
            DateFormat format = new SimpleDateFormat("d MMMMM yyyy", Locale.ENGLISH);
            logger.debug(format.parse(dateStr));
            return format.parse(dateStr);
        } catch (Exception ex) {
            logger.error("Ошибка парсинга даты, входящие значение {}, message: {}", dateStr, ex.getMessage());
            return null;
        }
    }

    // возвращает дату +6 дней от текущего числа
    public static String getDateBeforeOneWeek() {
        formatForDateNow = new SimpleDateFormat("d MMMMM yyyy", Locale.ENGLISH);
        dateBefore = new Date(dateNow.getTime() + 6 * 24 * 3600 * 1000);

        return formatForDateNow.format(dateBefore);

    }
}
