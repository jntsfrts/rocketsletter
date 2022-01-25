package br.com.rocketsletter.message;

import br.com.rocketsletter.launch.Launch;
import br.com.rocketsletter.launch.LaunchService;
import br.com.rocketsletter.launch.NoLaunchTodayException;
import br.com.rocketsletter.user.Email;
import br.com.rocketsletter.user.User;
import br.com.rocketsletter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private LaunchService launchService;

    @Autowired
    private UserService userService;

    @Autowired
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderAddress;

    private final SpringTemplateEngine thymeleafTemplateEngine;


    public MessageService(SpringTemplateEngine thymeleafTemplateEngine, JavaMailSender mailSender) {
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.mailSender = mailSender;
    }

    //@Scheduled(cron = "42 06 07 * * MON-FRI")
    public void sendDailyMessage() {

        List<Launch> launchesOfTheDay;
        try {
            launchesOfTheDay = launchService.getLaunchesOfTheDay();
        } catch(NoLaunchTodayException exception) {
            exception.printStackTrace();
            return;
        }

        List<User> recipients = userService.findAll();

        try {
            sendMessage(recipients, launchesOfTheDay);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(
            List<User> recipients, List<Launch> launchesOfTheDay) throws MessagingException {

        Map<String, Object> templateModel = getUpcomingLaunchesTemplateModel(launchesOfTheDay);
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process(
                "daily-email-template.html", thymeleafContext);


        for(User recipient : recipients) {
            sendHtmlMessage(recipient.getEmail().getAddress(),
                    "Próximos Lançamentos de Veículos Espaciais | "
                            + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    htmlBody);
        }
    }


    public void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(getSenderAddress());
        helper.setTo(to);
        helper.setSubject(subject.strip());
        helper.setText(htmlBody, true);

        mailSender.send(message);
    }


    private Map<String, Object> getUpcomingLaunchesTemplateModel(List<Launch> upcomingLaunches) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("launches", upcomingLaunches);

        return templateModel;
    }

    private String getSenderAddress() {
        return senderAddress;
    }



    @Scheduled(cron = "*/4 * * * * MON-FRI" )
    public void teste() throws MessagingException {

        List<Launch> launches = getFakeLaunches();
        //List<Launch> launches = launchService.getLaunchesOfTheDay();
        List<User> recipients = new ArrayList<>();
        //launches.add(new Launch("Teste Thymeleaf"));
        recipients.add(new User(1, new Email("jonatasfreitas20@gmail.com")));


        sendMessage(recipients, launches);
        System.out.println("Mensagem enviada para " + recipients.get(0).getEmail().getAddress() + ".");

    }

    private List<Launch> getFakeLaunches() {
        Launch l1 = new Launch();
        l1.setTitle("Falcon 9 - Starlink");
        l1.setWindowStart(LocalDateTime.now());
        l1.setWindowEnd(LocalDateTime.now().plusDays(1));
        l1.setStatus(new Launch.Status());
        l1.getStatus().setStatusDescription("Go for launch");

        Launch l2 = new Launch();
        l2.setTitle("Starship - Mars Mission");
        l2.setWindowStart(LocalDateTime.now().plusHours(1));
        l2.setWindowEnd(LocalDateTime.now().plusDays(2));
        l2.setStatus(new Launch.Status());
        l2.getStatus().setStatusDescription("Go for launch");

        Launch l3 = new Launch();
        l3.setTitle("Artemis - Moon Mission");
        l3.setWindowStart(LocalDateTime.now().plusHours(2));
        l3.setWindowEnd(LocalDateTime.now().plusDays(1));
        l3.setStatus(new Launch.Status());
        l3.getStatus().setStatusDescription("Go for launch");

        List<Launch> launches = new ArrayList<>();
        launches.add(l1);
        launches.add(l2);
        launches.add(l3);

        return launches;
    }

}
