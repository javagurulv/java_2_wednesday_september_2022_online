package lv.javaguru.java2.tasksScheduler.utils;

public class WebUI {

    public static String getGreeting(String name) {
        String greeting;
        if (ValueChecking.stringIsEmpty(name)) {
            greeting = "Welcome to Your account!";
        } else {
            greeting = "Welcome, " + name + "!";
        }
        return greeting;
    }
}
