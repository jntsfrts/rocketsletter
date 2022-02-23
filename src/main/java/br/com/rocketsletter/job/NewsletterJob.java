package br.com.rocketsletter.job;

import br.com.rocketsletter.model.EmailTemplate;
import br.com.rocketsletter.model.Launch;
import br.com.rocketsletter.model.User;
import br.com.rocketsletter.service.LaunchService;
import br.com.rocketsletter.service.MessageService;
import br.com.rocketsletter.service.UserService;
import br.com.rocketsletter.service.exception.NoLaunchTodayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class NewsletterJob {

    @Autowired
    private LaunchService launchService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private EmailTemplate template;


    @Scheduled(cron = "42 06 07 * * MON-FRI")
    public void sendDailyMessage() {

        List<Launch> launches;

        try {
            launches = launchService.getLaunchesOfTheDay();
        } catch (NoLaunchTodayException e) {
            e.printStackTrace();
            return;
        }
        List<User> recipients = userService.findAll();

        if(recipients.isEmpty())
            return;

        String htmlBody = template.getUpcomingLaunchesTemplateModel(launches);

        try {
            messageService.sendMessage(recipients, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
