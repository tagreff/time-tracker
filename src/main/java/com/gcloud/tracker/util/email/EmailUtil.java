package com.gcloud.tracker.util.email;

import com.gcloud.tracker.util.PropertiesMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class EmailUtil {

    /*  private static final Logger emailLogger = LoggerFactory.getLogger("emailLogger");*/
    private static final Properties properties = PropertiesMaker.getProps("email.properties");
    private static final String to = properties.getProperty("email.to");
    private static final String from = properties.getProperty("email.from");
    private static final String host = properties.getProperty("email.host");
    private static final String authenticationUserEmail = properties.getProperty("pass.user_name");
    private static final String authenticationUserPass = properties.getProperty("pass.user_pass");

    public static void sendMail(String reportFilePath) {

        Properties systemProperties = System.getProperties();
        systemProperties.put("mail.smtp.host", host);
        systemProperties.put("mail.smtp.port", "465");
        systemProperties.put("mail.smtp.ssl.enable", "true");
        systemProperties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(systemProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(authenticationUserEmail, authenticationUserPass);

            }
        });
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            message.setSubject("Green team report " + LocalDateTime.now().format(formatter));
            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();

            try {
                File f = new File(reportFilePath);
                attachmentPart.attachFile(f);
                textPart.setText("Hello, I'm report sender, here is daily report. Have a nice day!");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);
            } catch (IOException e) {
                /*emailLogger.error("Catch exception while working with file: ", e);*/
                e.printStackTrace();
            }
            message.setContent(multipart);
            Transport.send(message);
            /* emailLogger.info("Sent message successfully....");*/
        } catch (MessagingException mex) {
            /*emailLogger.error("Caught exception while sending message: ", mex);*/
            mex.printStackTrace();
        }
    }
}