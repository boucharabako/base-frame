/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.mails;

import com.base.frame.socle.utils.properties.NextFramProperties;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author WKOUWONOU
 */
@Component
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private static final String USER = "user";
    private static final String BASE_URL = "baseUrl";
    private static final String APP_LOGO_URL = "appLogoUrl";
        public static final String UTF_8 = "UTF-8";
    private final MessageSource messageSource;

    public MailService(JavaMailSenderImpl javaMailSender, MessageSource messageSource, SpringTemplateEngine templateEngine, NextFramProperties nextFramProperties) {
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
        this.nextFramProperties = nextFramProperties;
    }

    private final JavaMailSenderImpl javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final NextFramProperties nextFramProperties;

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart,UTF_8);
            message.setTo(to);

            message.setFrom(nextFramProperties.getMail().getFrom(),
                    nextFramProperties.getMail().getSenderName());
            message.setSubject(subject);
            message.setText(content, isHtml);

            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (MessagingException | MailException | UnsupportedEncodingException e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }

    @Async
    public void sendEmailWhithFile(String to, String subject, String content, boolean isMultipart, boolean isHtml, ByteArrayOutputStream stream, String contentype, String fileName) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {

            DataSource dataSource = new ByteArrayDataSource(stream.toByteArray(), contentype);
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, org.apache.commons.lang3.CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(nextFramProperties.getMail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            message.addAttachment(fileName, dataSource);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (MessagingException | MailException e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }

    @Async
    public void sendEmailFromTemplate(String email, Object user, String templateName, String title, String langKey) {
        Locale locale = Locale.forLanguageTag(langKey);
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, nextFramProperties.getMail().getBaseUrl());
        context.setVariable(APP_LOGO_URL, nextFramProperties.getMail().getAppLogoUrl());
        String content = templateEngine.process(templateName, context);

        sendEmail(email, title, content, false, true);

    }

    @Async
    public void sendEmailFromFile(String email, Object user, String templateName, String title, String langKey, ByteArrayOutputStream stream, String contentype, String fileName) {
        Locale locale = Locale.forLanguageTag(langKey);
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, nextFramProperties.getMail().getBaseUrl());
        context.setVariable(APP_LOGO_URL, nextFramProperties.getMail().getAppLogoUrl());
        String content = templateEngine.process(templateName, context);

        sendEmailWhithFile(email, title, content, true, true, stream, contentype, fileName);
    }

    @Async
    public void sendEmailFromTemplate(String email, Object user, String templateName, String title, String langKey, String baseUrl) {
        Locale locale = Locale.forLanguageTag(langKey);
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, nextFramProperties.getMail().getBaseUrl() + baseUrl);
        context.setVariable(APP_LOGO_URL, nextFramProperties.getMail().getAppLogoUrl());
        String content = templateEngine.process(templateName, context);

        sendEmail(email, title, content, false, true);

    }

    @Async
    public void sendEmailFromTemplate(String email, Object user, String templateName, String title,
            String langKey, String baseUrl, Map<String, Object> variables) {
        Locale locale = Locale.forLanguageTag(langKey);
        Context context = new Context(locale);

        context.setVariable(USER, user);
        context.setVariable(BASE_URL, nextFramProperties.getMail().getBaseUrl() + baseUrl);
        context.setVariable(APP_LOGO_URL, nextFramProperties.getMail().getAppLogoUrl());

        variables.entrySet().forEach((variable) -> {
            context.setVariable(variable.getKey(), variable.getValue());
        });

        String content = templateEngine.process(templateName, context);

        sendEmail(email, title, content, false, true);

    }

    @Override
    public String toString() {
        return "MailService{" + "messageSource=" + messageSource + '}';
    }

}
