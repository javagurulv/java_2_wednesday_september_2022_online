package lv.javaguru.java2.tasksScheduler.requests;

public class JobRunRequest {
    boolean manual;

    public JobRunRequest(boolean manual) {
        this.manual = manual;
    }

    public boolean isManual() {
        return manual;
    }
}
