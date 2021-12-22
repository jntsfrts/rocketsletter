package br.com.rocketsletter.notification;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NotificationService {

    //@Scheduled(cron = "42 06 07 * * MON-FRI"
    @Scheduled(cron = "*/7 * * * * MON-FRI" )
    public void teste() {
        RestTemplate restTemplate = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("ll.thespacedevs.com")
                .path("2.2.0/launch/upcoming")
                .queryParam("hide_recent_previous", true)
                .queryParam("ordering", "windows_start")
                .build();

        ResponseEntity<UpcomingLaunches> response =
                restTemplate.getForEntity(uri.toUriString(), UpcomingLaunches.class);

        System.out.println("Nome: " + response.getBody().getLaunches().get(0).getName());
        System.out.println("Status: " + response.getBody().getLaunches().get(0).getStatus().getName());
        System.out.println("Window Start: " + response.getBody().getLaunches().get(0).getWindowStart().toString());

        //System.out.println("Mission: " + response.getBody().getLaunches().get(0).getMission().getDescription());
    }

}
