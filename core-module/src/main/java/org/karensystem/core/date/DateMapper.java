package org.karensystem.core.date;


import java.time.LocalDate;

public class DateMapper {

    static DateConverter dateConverter = new DateConverter();

    public static String toString(LocalDate date) {
        if(date==null)
            return null;
        JalaliDate jalaliDate = dateConverter.gregorianToJalali(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
        return jalaliDate.format(new JalaliDateFormatter("yyyy/mm/dd"));
    }

    public static LocalDate toLocalDate(String date) {
        try {
            if(date==null)
                return null;
            String[] substring = date.split("/");
            LocalDate localdate1 = dateConverter.jalaliToGregorian(Integer.parseInt(substring[0]), Integer.parseInt(substring[1]), Integer.parseInt(substring[2]));
            return localdate1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
