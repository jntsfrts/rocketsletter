package br.com.rocketsletter.service;

import br.com.rocketsletter.dto.UpcomingLaunchesResponseDTO;
import br.com.rocketsletter.model.Launch;
import br.com.rocketsletter.service.exception.NoLaunchTodayException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LaunchService {

    public List<Launch> getLaunchesOfTheDay() throws NoLaunchTodayException {

        List<Launch> todaysLaunches = filterLaunchesOfTheDay(getUpcomingLaunches());

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

        ResponseEntity<UpcomingLaunchesResponseDTO> response = restTemplate.getForEntity(uri.toUriString(), UpcomingLaunchesResponseDTO.class);

        return Objects.requireNonNull(response.getBody()).getLaunches();
    }

    //TODO Voltar a validar com os filtros reais após teste
    private List<Launch> filterLaunchesOfTheDay(List<Launch> launches) {

        List<Launch> todaysLaunches = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();

//        launches.forEach(launch -> {
//            if(launch.getWindowStart().toLocalDate().equals(today.toLocalDate())) {
//                todaysLaunches.add(launch);
//            }
//        });

        todaysLaunches.add(launches.get(0));
        todaysLaunches.add(launches.get(1));

        return todaysLaunches;
    }
}
