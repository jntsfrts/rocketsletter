package br.com.rocketsletter.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmailTemplate {

    private String name = "daily-email-template.html";
    private String htmlBody;
    private final SpringTemplateEngine thymeleafTemplateEngine;


    public EmailTemplate(SpringTemplateEngine thymeleafTemplateEngine) {
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }


    public String getUpcomingLaunchesTemplateModel(List<Launch> launchesOfTheDay) {
        Map<String, Object> templateModel = getTemplateFromLaunches(launchesOfTheDay);
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        this.htmlBody = thymeleafTemplateEngine.process(
                this.name, thymeleafContext);

        return htmlBody;
    }

    private Map<String, Object> getTemplateFromLaunches(List<Launch> upcomingLaunches) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("launches", upcomingLaunches);

        return templateModel;
    }


    public String getName() {
        return name;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }
}
