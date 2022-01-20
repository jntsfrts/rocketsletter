package br.com.rocketsletter.launch;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public class Launch {

    private String title;
    private Status status;
    private Mission mission;

    @JsonAlias("window_start")
    LocalDateTime windowStart;

    @JsonAlias("window_end")
    LocalDateTime windowEnd;

    public static class Status {
        String statusDescription;

        public String getStatusDescription() {
            return statusDescription;
        }

        public void setStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
        }
    }

    public static class Mission {
        String missionDescription;

        public String getMissionDescription() {
            return missionDescription;
        }

        public void setMissionDescription(String missionDescription) {
            this.missionDescription = missionDescription;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public LocalDateTime getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(LocalDateTime windowStart) {
        this.windowStart = windowStart;
    }

    public LocalDateTime getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(LocalDateTime windowEnd) {
        this.windowEnd = windowEnd;
    }
}
