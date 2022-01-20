package br.com.rocketsletter.launch;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LaunchService {

    public List<Launch> getLaunchesOfTheDay() throws NoLaunchTodayException {

        List<Launch> todaysLaunches = filterLauchesOfTheDay(getUpcomingLaunches());

        if(todaysLaunches.isEmpty()) {
            throw new NoLaunchTodayException();
        }

        return todaysLaunches;
    }

    private List<Launch> getUpcomingLaunches() {

        RestTemplate restTemplate = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("ll.thespacedevs.com")
                .path("2.2.0/launch/upcoming")
                .queryParam("hide_recent_previous", true)
                .queryParam("ordering", "windows_start")
                .build();

        ResponseEntity<UpcomingLaunchesResponse> response = restTemplate.getForEntity(uri.toUriString(), UpcomingLaunchesResponse.class);

        return Objects.requireNonNull(response.getBody()).getLaunches();
    }

    private List<Launch> filterLauchesOfTheDay(List<Launch> launches) {

        List<Launch> todaysLaunches = new ArrayList<>();
        LocalDate today = LocalDate.now();

        LocalDate day = LocalDate.now();
        LocalDate anotherDayInTheDark = LocalDate.now();


        launches.forEach(launch -> {
            if(launch.getWindowStart().equals(today)) {
                todaysLaunches.add(launch);
            }
        });

        return todaysLaunches;
    }
}
