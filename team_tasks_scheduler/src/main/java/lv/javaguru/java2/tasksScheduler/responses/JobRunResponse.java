package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.JobRunResult;

import java.util.List;

public class JobRunResponse extends CoreResponse {
    JobRunResult runResult;

    public JobRunResponse(List<CoreError> errors) {
        super(errors);
    }

    public JobRunResponse(JobRunResult runResult) {
        this.runResult = runResult;
    }

    public JobRunResult getRunResult() {
        return runResult;
    }
}
