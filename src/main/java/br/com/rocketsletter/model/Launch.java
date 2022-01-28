package br.com.rocketsletter.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Launch {

    @JsonAlias("name")
    private String title;
    private Status status;
    private Mission mission;

    @JsonAlias("window_start")
    private LocalDateTime windowStart;

    @JsonAlias("window_end")
    private LocalDateTime windowEnd;

    private LaunchServiceProvider launchServiceProvider;

    //TODO: Remover após testes
//    public Launch(String title) {
//        this.title = title;
//    }

    public static class Status {
        @JsonAlias("name")
        public String statusDescription;

        public String getStatusDescription() {
            return this.statusDescription;
        }

        public void setStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
        }
    }

    public static class LaunchServiceProvider {
        public String name;
        public String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Mission {

        @JsonAlias(value="description")
        public String missionDescription;

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

    public LocalDateTime getWindowStart() {
        return windowStart;
    }

    public String getWindowStartFormatted() {
        if(this.windowStart != null)
            return this.windowStart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        return "";
    }

    public String getWindowEndFormatted() {
        if(this.windowEnd != null)
            return this.windowEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        return "";
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

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public LaunchServiceProvider getLaunchServiceProvider() {
        return launchServiceProvider;
    }

    public void setLaunchServiceProvider(LaunchServiceProvider launchServiceProvider) {
        this.launchServiceProvider = launchServiceProvider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Launch)) return false;
        Launch launch = (Launch) o;
        return Objects.equals(getTitle(), launch.getTitle()) && Objects.equals(getStatus(), launch.getStatus()) && Objects.equals(getMission(), launch.getMission()) && Objects.equals(getWindowStart(), launch.getWindowStart()) && Objects.equals(getWindowEnd(), launch.getWindowEnd()) && Objects.equals(getLaunchServiceProvider(), launch.getLaunchServiceProvider());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getStatus(), getMission(), getWindowStart(), getWindowEnd(), getLaunchServiceProvider());
    }

    @Override
    public String toString() {
        return "Launch{" +
                "title='" + title + '\'' +
                ", status=" + status +
                ", mission=" + mission +
                ", windowStart=" + windowStart +
                ", windowEnd=" + windowEnd +
                ", launchServiceProvider=" + launchServiceProvider +
                '}';
    }
}
