package com.anthony.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author ThonyDev0726
 */
public class ENVIO_MAIL {

    public ENVIO_MAIL() {
    }

//    public void enviarArchivo(String receptor, String receptorNombre, String emisor, String asunto, String claveEmisor, String alias, String ruta, String nombreArchivo) {
//        try {
//
//            Properties props = new Properties();;
//
//            props.put("mail.smtp.host", "smtp-mail.outlook.com");
//            props.put("mail.smtp.port", "587");
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//
//            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(emisor, claveEmisor);
//                }
//            });
//
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(emisor, alias));
//            msg.setSubject(asunto);
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor, receptorNombre));
//            msg.setDataHandler(new DataHandler(new FileDataSource(ruta)));
//            msg.setFileName(nombreArchivo);
//            Transport.send(msg);
//            System.out.println("Email sent successfully...");
//            System.out.println("Email sent successfully...");
//        } catch (AddressException e) {
//            System.out.println(e);
//            System.out.println(e.getMessage());
//        } catch (MessagingException e) {
//            System.out.println(e);
//            System.out.println(e.getMessage());
//        } catch (UnsupportedEncodingException e) {
//            System.out.println(e);
//            System.out.println(e.getMessage());
//        }
//    }
//    public static void main(String[] args) {
//        ENVIO_MAIL mail = new ENVIO_MAIL();
//        mail.sendMail(
//                "perezpalaciospepa@gmail.com",
//                "ALESSIA DE LOS ANGELES PEREZ PALACIOS",
//                "NOMBRE DE EMPRESA",
//                "rperez0726@outlook.com",
//                "07262001Aapp",
//                "COMPROBANTE GENERADO",
//                "FACTURA",
//                "0000000000001", "100", "C:\\FACTURING_V1\\2022\\NOVIEMBRE\\FACTURAS/0000000001-Richard Perez 2022-11-4 (234343) S1.pdf",
//                "0000000001-Richard Perez 2022-11-4 (234343) S1.pdf");
//    }
    
    
    public String sendMail(
            String receptorEmail,
            String receptorNombre,
            String nombreEmpresa,
            String emisorEmail,
            String claveEmisor,
            String asunto,
            String tipoDocumento,
            String numeroComprobante,
            String totalPagar,
            String ruta,
            String nombreArchivo) {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp-mail.outlook.com");
//        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emisorEmail, claveEmisor);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emisorEmail, nombreEmpresa));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptorEmail));
            message.setSubject(asunto);

            BodyPart messageDescripcion = new MimeBodyPart();
            messageDescripcion.setText(tipoDocumento);
            messageDescripcion.setContent(
                    "<center>"
                    + "<div  style=\""
                    + " background-color:#E9F1FB;\n"
                    + "	border-radius:15px;\n"
                    + "	display:inline-block;\n"
                    + "	cursor:pointer;\n"
                    + "	font-family:Arial;\n"
                    + "	font-size:15px;\n"
                    + "	padding:10px 15px;\n"
                    + "	cursor:pointer;\n"
                    + "	text-decoration:none;\n"
                    + "	width:20rem;\n"
                    + "	text-shadow:0px 1px 0px #2f6627;"
                    + "\">"
                    + "<center>"
                    + "<h2 style=\""
                    + "	color:#000;\n"
                    + "\"> "
                    + "COMPROBANTE GENERADO"
                    + "</h2><br/>"
                    + "</center>"
                    + "<div style=\""
                    + "align-content: left;"
                    + "width= 100%"
                    + "\">"
                    + "<span>Estimad@ <b>" + receptorNombre + "</b>,</span><br>"
                    + "<span>Esta es una notificacion automatica de un documeto tributario electronico emitido por <b>" + nombreEmpresa + "</b>.</span><br>"
                    + "<span><b>Tipo de comprobante: </b>" + tipoDocumento + "</span><br>"
                    + "<span><b>Nro. de comprobante: </b>" + numeroComprobante + "</span><br>"
                    + "<span><b>Valor total: </b>" + totalPagar + " USD</span>"
                    + "</div>"
                    + "<br>"
                    + "<br>"
                    + "<p>Este comprobante fue generado por <b>PROVEEDOR SERVICIO</b></p><br>"
                    + ""
                    + "<center>"
                    + "<a href='www.google.com' style=\""
                    + "background-color:#08AAFA;\n"
                    + "	border-radius:28px;\n"
                    + "	display:inline-block;\n"
                    + "	cursor:pointer;\n"
                    + "	color:#fff;\n"
                    + "	font-family:Arial;\n"
                    + "	font-size:15px;\n"
                    + "	padding:10px 15px;\n"
                    + "	cursor:pointer;\n"
                    + "	text-decoration:none;\n"
                    + "	text-shadow:0px 1px 0px #2f6627;"
                    + "\"> "
                    //TEXTO DEL BOTON
                    + "SABER MAS"
                    + "</a> <br><br>"
                    + "</center>"
                    + "</center>",
                    "text/html");

            String filename = ruta;//change accordingly  
            DataSource source = new FileDataSource(filename);
            MimeBodyPart archivoAdjunto = new MimeBodyPart();
            archivoAdjunto.setDataHandler(new DataHandler(source));
            archivoAdjunto.setFileName(nombreArchivo);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageDescripcion);
            multipart.addBodyPart(archivoAdjunto);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return "Error al enviar el email";
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ENVIO_MAIL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Email enviado correctamente";
    }

}
