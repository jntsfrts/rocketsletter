package br.com.rocketsletter.message;

import br.com.rocketsletter.launch.Launch;
import br.com.rocketsletter.launch.LaunchService;
import br.com.rocketsletter.launch.NoLaunchTodayException;
import br.com.rocketsletter.user.User;
import br.com.rocketsletter.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
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
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderAddress;

    private final SpringTemplateEngine thymeleafTemplateEngine;


    public MessageService(SpringTemplateEngine thymeleafTemplateEngine, JavaMailSender mailSender) {
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.mailSender = mailSender;
    }

    //@Scheduled(cron = "42 06 07 * * MON-FRI")
    public void sendDailyMessage() {

        List<Launch> launchesOfTheDay = new ArrayList<>();
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
                "upcoming-launches-email.html", thymeleafContext);

        //TODO design do template
        //TODO lógica de envio.

//        for(User recipient : recipients) {
//            sendHtmlMessage(recipient, "blabla", htmlBody);
//        }

   //     sendHtmlMessage(to, subject, htmlBody);
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



    public void sendMessageOld(List<Message> messages) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("jonatasfreitas20@gmail.com");
        msg.setSubject("Teste de email com Spring");
        msg.setText("Hello World! \n From Springboot!!!");

        mailSender.send(msg);
    }

    public void sendMessageOld() {
        System.out.println("[INIT] Enviando mensagem de teste...");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("jonatasfreitas20@gmail.com");
        msg.setSubject("Teste de email com Spring");
        msg.setText("Hello World! \n From Springboot!!!");

        mailSender.send(msg);
        System.out.println("[END] Mensagem de teste enviada...");
    }





    
    private String getSenderAddress() {
        return senderAddress;
    }



    @Scheduled(cron = "*/7 * * * * MON-FRI" )
    public void teste() throws MessagingException {

        //sendMessage();
        //File file = ResourceUtils.getFile("src/main/resources/templates/simple-email-template.html");
        //InputStream in = new FileInputStream(file);
        String location = "/home/jonatasf/Code/projects/rocketsletter/src/main/resources/templates/simple-email-template.html";

        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(location))){
            String str;
            while((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = contentBuilder.toString();

        sendHtmlMessage("jonatasfreitas20@gmail.com", "testando html email", content);
        System.out.println("Email enviado.");
//        List<Launch> response = new ArrayList<>();
//        try {
//            response = launchService.getTodaysLaunches();
//        } catch (NoLaunchTodayException e) {
//            e.printStackTrace();
//        }


//
//        System.out.println("Nome: " + response.getBody().getLaunches().get(0).getName());
//        System.out.println("Status: " + response.getBody().getLaunches().get(0).getStatus().getName());
//        System.out.println("Window Start: " + response.getBody().getLaunches().get(0).getWindowStart().toString());

        //System.out.println("Mission: " + response.getBody().getLaunches().get(0).getMission().getDescription());
    }

}
