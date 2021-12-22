package br.com.rocketsletter.notification;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;
import java.util.List;

public class UpcomingLaunches {

    private Integer count;
    @JsonAlias("results")
    private List<Launch> launches;

    static class Launch {
        String name;
        Status status;
        Mission mission;

        @JsonAlias("window_start")
        LocalDateTime windowStart;

        @JsonAlias("window_end")
        LocalDateTime windowsEnd;

        static class Status {
            String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        static class Mission {
            String description;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public LocalDateTime getWindowsEnd() {
            return windowsEnd;
        }

        public void setWindowsEnd(LocalDateTime windowsEnd) {
            this.windowsEnd = windowsEnd;
        }
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Launch> getLaunches() {
        return launches;
    }

    public void setLaunches(List<Launch> launches) {
        this.launches = launches;
    }
}
