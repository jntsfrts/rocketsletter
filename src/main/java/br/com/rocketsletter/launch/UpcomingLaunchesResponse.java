package br.com.rocketsletter.launch;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

class UpcomingLaunchesResponse {

    private Integer count;
    @JsonAlias("results")
    private List<Launch> launches;

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
