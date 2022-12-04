package lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs;

import java.time.LocalDateTime;

public class JobRunResult {
    private String jobName;
    private LocalDateTime timestampStart;
    private LocalDateTime  timestampEnd;
    private int actionsCount;
    private String runType;
    private String status;

    public JobRunResult() {
    }

    public JobRunResult(String jobName) {
        this.jobName = jobName;
        this.timestampStart = LocalDateTime.now();
        this.timestampEnd = LocalDateTime.MIN;
        this.actionsCount = 0;
        this.runType = "Manual";
        this.status = "Failed";
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public LocalDateTime getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(LocalDateTime timestampStart) {
        this.timestampStart = timestampStart;
    }

    public LocalDateTime getTimestampEnd() {
        return timestampEnd;
    }

    public void setTimestampEnd(LocalDateTime timestampEnd) {
        this.timestampEnd = timestampEnd;
    }

    public int getActionsCount() {
        return actionsCount;
    }

    public void setActionsCount(int actionsCount) {
        this.actionsCount = actionsCount;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecordInLogFormat() {
        return "JobName=" + this.jobName + "; " +
                "RunStart=" + this.timestampStart + "; " +
                "RunEnd=" + this.timestampEnd + "; " +
                "ActionsCount=" + this.actionsCount + "; " +
                "RunType=" + this.runType + "; " +
                "Status=" + this.status + ";";
    }
}
