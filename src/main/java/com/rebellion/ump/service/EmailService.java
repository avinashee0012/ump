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
				return new PasswordAuthentication("avinashee0012@gmail.com", "uwzosrvtzflbodva");
			}
		};

        //  Create a session object with property
        Session session = Session.getInstance(props, auth);

        // Create a MimeMessage for email in the session
        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress("avinashee0012@gmail.com"));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject("UMP Email Verification");
        email.setText("This is the test body! {verificationURL} will be placed here.");
        email.setSentDate(new Date());

        Transport.send(email);

        return new ResponseEntity<>(email, HttpStatus.OK);
    }
}