package lv.javaguru.java2.tasksScheduler.utils;

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
}
