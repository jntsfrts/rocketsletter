package br.com.rocketsletter.job;

import br.com.rocketsletter.model.EmailTemplate;
import br.com.rocketsletter.model.Launch;
import br.com.rocketsletter.model.User;
import br.com.rocketsletter.service.LaunchService;
import br.com.rocketsletter.service.MessageService;
import br.com.rocketsletter.service.UserService;
import br.com.rocketsletter.service.exception.NoLaunchTodayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
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


    //@Scheduled(cron = "42 06 07 * * MON-FRI")
    //@Scheduled(cron = "*/4 * * * * MON-FRI" )
    public void sendDailyMessage() {

        List<Launch> launches;

        try {
            launches = launchService.getLaunchesOfTheDay();
        } catch (NoLaunchTodayException e) {
            e.printStackTrace();
            return;
        }
        //List<User> recipients = userService.findAll();

        List<User> recipients = new ArrayList<>();
        //recipients.add(new User(1, new Email("jonatasfreitas20@gmail.com")));

        if(recipients.isEmpty() || recipients.equals(null))
            return;

        // TODO Template não está sendo gerado.
        String htmlBody = template.getUpcomingLaunchesTemplateModel(launches);
        System.out.println(htmlBody);

        try {
            messageService.sendMessage(recipients, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Mensagem enviada para " + recipients.get(0).getEmail() + ".");
    }
}
