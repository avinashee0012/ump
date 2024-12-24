package com.rebellion.ump.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.mail.Transport;

public class EmailService {

    // This needs to return status based on response of successful receipt of email
    public static ResponseEntity<?> sendVerificationEmail(String to) throws MessagingException {

        String fromEmail = "rebelliondevemail728@gmail.com";
        String fromPassword = "wwjqyjzfqexvwaeb";

        // String fromEmail = "YOUR_EMAIL";
        // String fromPassword = "YOUR_PASSWORD";

        // Create a property for Session object 
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, fromPassword);
			}
		};

        //  Create a session object with property
        Session session = Session.getInstance(props, auth);

        // Verification URL Algo
        Date dateNow = new Date();
        Integer randomInteger = Integer.valueOf((int) Math.random());
        String token = randomInteger.hashCode() + "." + to.hashCode() + "." + dateNow.hashCode();

        String url = "http://localhost:8080/register/verify/" + to + "?token=" + token;

        String msg = String.format("Hello,\n\nPlease click the link below to verify email:\n\n%s\n\nRegards,\nUMP Registration Service", url);

        // Create a MimeMessage for email in the session
        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(fromEmail));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject("UMP Email Verification");
        email.setText(msg);
        Transport.send(email);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
