package ru.itis.utils;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

@RequiredArgsConstructor
@PropertySource("classpath:mail.properties")
@Component
public class EmailUtil {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private Environment environment;

    public void sendMail(String to, String subject, String templateName, Map<String, String> data){
        String text = getText(templateName, data);

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setSubject(subject);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            messageHelper.setText(text, true);
        };

        mailSender.send(preparator);
    }

    private String getText(String templateName, Map<String, String> data){
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
            configuration.setDefaultEncoding(environment.getProperty("configuration.encoding"));
            configuration.setTemplateLoader(new FileTemplateLoader(new ClassPathResource("mails").getFile()));


            Template template = configuration.getTemplate(templateName);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);

            return text;
        }catch (IOException | TemplateException e){
            throw new IllegalArgumentException("Wrong template for mail", e);
        }
    }
}
