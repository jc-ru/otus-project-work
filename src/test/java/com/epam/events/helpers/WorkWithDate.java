package com.epam.events.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        String date = changeRangeDate(dateStr);
        try {
            DateFormat format = new SimpleDateFormat("d MMMMM yyyy", Locale.ENGLISH);
            logger.debug(format.parse(date));
            return format.parse(date);
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

    // если дата имеет диапазон чисел, возвращать начальное число диапазона
    private static String changeRangeDate(String dateStr) {
        int indexToDel = 0;
        try {
            String replaceVal= dateStr.substring(dateStr.indexOf("-"), dateStr.length() -1);
            for (int i = 0; i < replaceVal.length(); i++) {
                String a = String.valueOf(replaceVal.charAt(i));
                if (a.matches("\\D") && a.matches("\\S") && a.matches("\\w")) {
                    replaceVal = replaceVal.substring(0, indexToDel);
                    break;
                }
                else {
                    indexToDel++;
                }
            }
            return dateStr.replace(replaceVal, "");
        } catch (StringIndexOutOfBoundsException ex) {
            logger.debug("Дата не имеет диапазона чисел, фоматирование чисел не требуется");
            return dateStr;
        }

    }
}
