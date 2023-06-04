package lv.javaguru.java2.tasksScheduler.utils;

import java.time.LocalDateTime;

public class ValueChecking {

    public static boolean stringIsEmpty(String input) {
        return input == null || input.isBlank();
    }

    public static boolean stringIsInteger(String input) {
        if(input == null || input.isBlank())
            return false;

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static Long stringToLong(String input) {
        Long result;

        try {
            result = Long.parseLong(input);
        } catch (NumberFormatException e) {
            return null;
        }
        return result;
    }

    public static boolean dateIsInRange(LocalDateTime date, LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        if (date == null || rangeStart == null || rangeEnd == null)
            return false;

        if (date.isEqual(rangeStart) ||
                date.isEqual(rangeEnd) ||
                date.isAfter(rangeStart) ||
                date.isBefore(rangeEnd)) {
            return true;
        }
        return false;
    }

    public static LocalDateTime checkAdjustMySqlDateRange(LocalDateTime dateTime) {
        // The supported range is '1000-01-01 00:00:00' to '9999-12-31 23:59:59'.
        LocalDateTime pEndDate;
        if (dateTime.isAfter(LocalDateTime.of(9999,12,
                31,23,59,59))) {
            pEndDate = LocalDateTime.of(9999,12,
                    31,23,59,59);
            return pEndDate;
        }
        else if (dateTime.isBefore(LocalDateTime.of(1000,1,
                1,0,0,0))) {
            pEndDate = LocalDateTime.of(1000,1,
                    1,0,0,0);
            return pEndDate;
        }
        return dateTime;
    }
}
