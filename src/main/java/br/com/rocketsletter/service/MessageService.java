package br.com.rocketsletter.service;

import br.com.rocketsletter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderAddress;


    public MessageService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessage(List<User> recipients, String htmlBody) throws MessagingException {

        for(User recipient : recipients) {
            sendHtmlMessage(recipient.getEmail(),
                    "Próximos Lançamentos de Veículos Espaciais | "
                            + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    htmlBody);

            System.out.format("Mensagem enviada para %s .", recipient.getEmail());
        }
    }


    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(getSenderAddress());
        helper.setTo(to);
        helper.setSubject(subject.strip());
        helper.setText(htmlBody, true);

        mailSender.send(message);
    }

    private String getSenderAddress() {
        return senderAddress;
    }
}
