package com.epam.helpers;

import com.epam.events.steps.EventsSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WorkWithDate {
    final private static Logger logger = LogManager.getLogger(WorkWithDate.class);

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

}
