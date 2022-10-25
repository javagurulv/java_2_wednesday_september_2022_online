package lv.javaguru.java2.tasksScheduler.utils;

import java.time.LocalDateTime;

public class ValueChecking {

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
}
