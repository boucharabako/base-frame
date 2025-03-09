/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.config;

import com.base.frame.socle.utils.properties.NextFramProperties;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author W.KOUWONOU
 * @since 13-12-2017
 *
 */
@Configuration
public class JavaMailSender {

//    @Bean
//    public AutowireHelper autowireHelper() {
//        return AutowireHelper.getInstance();
//    }
//
    private final NextFramProperties nextFrameProperties;

    public JavaMailSender(NextFramProperties nextFrameProperties) {
        this.nextFrameProperties = nextFrameProperties;
    }

    @Bean
    public JavaMailSenderImpl avaMailSenderImplBean() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(nextFrameProperties.getMail().getHost());
        mailSender.setPort(nextFrameProperties.getMail().getPort());

        mailSender.setUsername(nextFrameProperties.getMail().getFrom());
        mailSender.setPassword(nextFrameProperties.getMail().getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", nextFrameProperties.getMail().getProtocol());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.trust", "*");
        props.put("mail.debug", "true");
        return mailSender;
    }

}
