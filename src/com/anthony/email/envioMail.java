/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anthony.email;

import java.util.*;
import java.util.logging.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author The Thøny
 *
 */
public class envioMail {

    public envioMail() {
    }

    public Properties propiedadesGmail() {
        Properties propiedad = new Properties();
//        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
//        propiedad.setProperty("mail.smtp.starttls.enable", "true");
//        propiedad.setProperty("mail.smtp.port", "587");
//        propiedad.setProperty("mail.smtp.auth", "true");

        propiedad.put("mail.smtp.auth", "true");
        propiedad.put("mail.smtp.starttls.enable", "true");
        propiedad.put("mail.smtp.host", "smtp.gmail.com");
        propiedad.put("mail.smtp.port", "587");
        propiedad.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        propiedad.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        propiedad.put("mail.smtp.debug", "true");
        return propiedad;
    }

    public Session MAIL_ARCHIVO(String correoEnvia, String clave, String destinatario, String asunto, String ruta, String nombreArchivo) {

        Session session = Session.getDefaultInstance(propiedadesGmail());

        MimeMessage mail = new MimeMessage(session);
        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            mail.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            mail.setFileName(nombreArchivo + ".pdf");
            Transport transporte = session.getTransport("smtp");
            transporte.connect(correoEnvia, clave);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
//            DesktopNotify.showDesktopMessage("", "Coreo enviado", DesktopNotify.SUCCESS, 5000L);
        } catch (AddressException e) {
            Logger.getLogger(envioMail.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("" + e);
        } catch (MessagingException ex) {
            Logger.getLogger(envioMail.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("" + ex);
        }
        return session;
    }

    public Session MAIL_NORMAL(String correoEnvia, String clave, String destinatario, String asunto, String nombreArchivo) {

        Session session = Session.getDefaultInstance(propiedadesGmail());

        MimeMessage mail = new MimeMessage(session);
        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            Transport transporte = session.getTransport("smtp");
            transporte.connect(correoEnvia, clave);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            System.out.println("Mensaje enviado");
//            DesktopNotify.showDesktopMessage("", "Coreo enviado", DesktopNotify.SUCCESS, 5000L);
        } catch (AddressException e) {
            Logger.getLogger(envioMail.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("" + e);
        } catch (MessagingException ex) {
            Logger.getLogger(envioMail.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("" + ex);
        }
        return session;
    }

    public static void main(String[] args) {
        String destinatario = "richardanto0726@gmail.com"; //A quien le quieres escribir.
        String asunto = "Correo de prueba enviado desde Java";
        String cuerpo = "Esta es una prueba de correo...";
        envioMail m = new envioMail();
        m.MAIL_ARCHIVO("richardanto0726@gmail.com", "canarianflyke@gmail.com", "richardanto0726@gmail.com", "Asunto", "C:\\FACTURING_V1\\2023\\ENERO\\FACTURAS\\0000000001-Richard Perez 2023-1-21 (1357) S1.pdf", "0000000001-Richard Perez 2023-1-21 (1357) S1");

    }
}
