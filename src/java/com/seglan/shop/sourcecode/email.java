/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.sourcecode;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Iñaki Garaizabal
 */
public class email {

    Properties props = new Properties();

    public email() {

    }

    public static boolean send(String to, ArrayList house, String Texto, String lang, int idNL, boolean sendCCO) {

        String from = "info@shoppingstage.net";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        String host = "smtp.shoppingstage.net";

        // Create properties for the Session
        Properties props = new Properties();

        // If using static Transport.send(),
        // need to specify the mail server here
        props.put("smtp.shoppingstage.net", host);
        // To see what is going on behind the scene
        props.put("mail.debug", "true");

        // Get a session
        Session session = Session.getInstance(props);

        try {
            // Get a Transport object to send e-mail
            Transport bus = session.getTransport("smtp");

            // Connect only once here
            // Transport.send() disconnects after each send
            // Usually, no username and password is required for SMTP
            //bus.connect();
            bus.connect("smtp.shoppingstage.net", "info@brickberlin.es", "wR5SPeHg");

            // Instantiate a message
            Message msg = new MimeMessage(session);

            // Set message attributes
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};

            msg.setRecipients(Message.RecipientType.TO, address);
            // Parse a comma-separated list of email addresses. Be strict.
            //msg.setRecipients(Message.RecipientType.CC,
            //        InternetAddress.parse(to, true));
            // Parse comma/space-separated list. Cut some slack.
            String adds = to;
            if ((sendCCO) && (!to.equalsIgnoreCase("info@brickberlin.es"))) {
                adds += ",info@brickberlin.es";
            }
            InternetAddress[] alladdress = InternetAddress.parse(adds);
            //InternetAddress[] alladdress = InternetAddress.parse(to);
            if ((sendCCO) && (!to.equalsIgnoreCase("info@brickberlin.es"))) {
                msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("info@brickberlin.es", true));
            }

            MessageResources msgres = MessageResources.getMessageResources("com.enoman.struts.ApplicationResource_" + lang);
            msg.setSubject(msgres.getMessage("newsletter.subject"));
            msg.setSentDate(new Date());

            msg.setDataHandler(new DataHandler(new HTMLDataSource(getHTMLContent(house, true, Texto, lang, idNL))));

            msg.saveChanges();
            bus.sendMessage(msg, alladdress);

            bus.close();
            return true;

        } catch (MessagingException mex) {
            // Prints all nested (chained) exceptions as well
            mex.printStackTrace();
            // How to access nested exceptions
            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) {
                    break;
                } else {
                    mex = (MessagingException) ex;
                }
            }
            return false;
        }
    }

    public static boolean sendActivation(String to, String subject, String Texto) {
        String username = "info@shoppingstage.net";
        String password = "4H8RJxOp";
        String smtphost = "mail.shoppingstage.net";
        String from = "info@shoppingstage.net";
        String mailer = "JavaMail API";
        try {

            // Create properties for the Session
            Properties props = new Properties();
            //props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", "true");

            // To see what is going on behind the scene
            props.put("mail.debug", "true");

            // Get a session
            Session mailSession = Session.getInstance(props);
            MimeMessage msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));

            msg.setSubject(subject);

            msg.setText(Texto,"utf-8", "html");

            msg.setHeader("X-Mailer", mailer);
            msg.setHeader("Content-Type", "text/html");
            msg.setSentDate(new Date());

            Transport tr = mailSession.getTransport("smtp");
            tr.connect(smtphost, username, password);
            msg.saveChanges(); // don't forget this
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
            return true;

        } catch (MessagingException mex) {
            // Prints all nested (chained) exceptions as well
            mex.printStackTrace();
            // How to access nested exceptions
            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) {
                    break;
                } else {
                    mex = (MessagingException) ex;
                }
            }
            return false;
        }
    }

    public static boolean send(String to, String Subject, String Body) {

        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        String from = "info@brickberlin.es";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        String host = "mail.brickberlin.es";

        // Create properties for the Session
        Properties props = new Properties();

        // If using static Transport.send(),
        // need to specify the mail server here
        props.put("mail.brickberlin.es", host);
        // To see what is going on behind the scene
        //props.put("mail.debug", "true");

        // Get a session
        Session session = Session.getInstance(props);

        try {
            // Get a Transport object to send e-mail
            Transport bus = session.getTransport("smtp");

            // Connect only once here
            // Transport.send() disconnects after each send
            // Usually, no username and password is required for SMTP
            //bus.connect();
            bus.connect("mail.brickberlin.es", "info@brickberlin.es", "wR5SPeHg");

            // Instantiate a message
            Message msg = new MimeMessage(session);

            // Set message attributes
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            // Parse a comma-separated list of email addresses. Be strict.
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(to, true));
            // Parse comma/space-separated list. Cut some slack.
            msg.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(to, false));

            msg.setSubject(Subject);
            msg.setSentDate(new Date());

            // Set message content and send
            //setTextContent(msg);
            msg.setContent(Body, "text/plain");
            msg.saveChanges();
            bus.sendMessage(msg, address);

            bus.close();
            return true;

        } catch (MessagingException mex) {
            // Prints all nested (chained) exceptions as well
            mex.printStackTrace();
            // How to access nested exceptions
            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) {
                    break;
                } else {
                    mex = (MessagingException) ex;
                }
            }
            return false;
        }
    }

    // A simple, single-part text/plain e-mail.
    public static void setTextContent(Message msg) throws MessagingException {
        // Set message content
        String mytxt = "This is a test of sending a "
                + "plain text e-mail through Java.\n"
                + "Here is line 2.";
        msg.setText(mytxt);

        // Alternate form
        msg.setContent(mytxt, "text/plain");

    }

    // A simple multipart/mixed e-mail. Both body parts are text/plain.
    public static void setMultipartContent(Message msg) throws MessagingException {
        // Create and fill first part
        MimeBodyPart p1 = new MimeBodyPart();
        p1.setText("This is part one of a test multipart e-mail.");

        // Create and fill second part
        MimeBodyPart p2 = new MimeBodyPart();
        // Here is how to set a charset on textual content
        p2.setText("This is the second part", "us-ascii");

        // Create the Multipart.  Add BodyParts to it.
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(p1);
        mp.addBodyPart(p2);

        // Set Multipart as the message's content
        msg.setContent(mp);
    }

    // Set a file as an attachment.  Uses JAF FileDataSource.
    public static void setFileAsAttachment(Message msg, String filename)
            throws MessagingException {

        // Create and fill first part
        MimeBodyPart p1 = new MimeBodyPart();
        p1.setText("This is part one of a test multipart e-mail."
                + "The second part is file as an attachment");

        // Create second part
        MimeBodyPart p2 = new MimeBodyPart();

        // Put a file in the second part
        FileDataSource fds = new FileDataSource(filename);
        p2.setDataHandler(new DataHandler(fds));
        p2.setFileName(fds.getName());

        // Create the Multipart.  Add BodyParts to it.
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(p1);
        mp.addBodyPart(p2);

        // Set Multipart as the message's content
        msg.setContent(mp);
    }

    // Set a single part html content.
    // Sending data of any type is similar.
    public static String getHTMLContent(ArrayList house, boolean isEmail, String Texto, String lang, int idNL) throws MessagingException {
        MessageResources msgres = MessageResources.getMessageResources("com.enoman.struts.ApplicationResource_" + lang);
        String html = "<html><head><title>BrickBerlin</title><link href=\"http://www.brickberlin.es/expose.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body>";
        if (isEmail) {
            String refs = "";
            for (int i = 0; i < house.size(); i++) {
                //refs += "," + ((flat) house.get(i)).getId();
            }
            html += msgres.getMessage("mail.view") + " <a href='http://www.brickberlin.es/news.jsp?id=" + refs.substring(1) + "&Lang=" + lang + "'>" + msgres.getMessage("mail.here") + "</a><br>";
        }
        html += "<table style=\"width:600px;\"><tr><td><img alt=\"BrickBerlin\" src=\"http://www.brickberlin.es/headexpose.png\" width=\"700px\"/></td></tr>";
        if (Texto.equals("")) {
            html += "<tr><td style=\"font-size:12px;\">" + msgres.getMessage("mail.nl.text") + "<br></td></tr>";
        } else {
            html += "<tr><td style=\"font-size:12px;\"><p>" + Texto.replace(System.getProperty("line.separator"), "</p><p>") + "</p><br></td></tr>";
        }
        /*for (int i = 0; i < house.size(); i++) {
         if(!((flat)house.get(i)).getState().equalsIgnoreCase("V")){
         html += "<tr><td>" + ((flat) house.get(i)).getHtmlMail(lang) + "</td></tr>";
         html += "<tr><td>&nbsp;</td></tr>";
         }
         }*/
        html += "<tr><td style=\"font-size:10px;font-style: italic;\"><p>" + msgres.getMessage("mail.legal") + "</p>";
        if (isEmail) {
            html += "<p>" + msgres.getMessage("mail.unsubscribe") + " <a href='http://www.brickberlin.es/unsubscribe.jsp?idNewsletter=" + idNL + "&Lang=" + lang + "'>" + msgres.getMessage("mail.here") + "</a></p>";
        }
        html += "</td></tr></table></body></html>";
        return html;
    }

    /*
     * Inner class to act as a JAF datasource to send HTML e-mail content
     */
    static class HTMLDataSource implements DataSource {

        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        // Return html string in an InputStream.
        // A new stream must be returned each time.
        public InputStream getInputStream() throws IOException {
            if (html == null) {
                throw new IOException("Null HTML");
            }
            return new ByteArrayInputStream(html.getBytes());
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        public String getContentType() {
            return "text/html";
        }

        public String getName() {
            return "JAF text/html dataSource to send e-mail only";
        }
    }

    public static String getBodyActivation(String Texto) throws MessagingException {
        String html = "<html style=\"margin:0px; padding:0px;text-align:center;display:block;background-color:white; font:normal 12px arial;\"><head><title>The Shopping Stage</title></head><body>";
        html += "<table style=\"width:600px;padding:0px;margin:0px;margin-left:auto;margin-right:auto;background-color:white;border=2px solid #f62d72;\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"background-image:url('http://shoppingstage.net/resources/logo_bk.png');background-repeat:repeat-x;text-align: right;color:#f62d72;\"><img alt=\"The Shopping Stage\" src=\"http://shoppingstage.net/resources/logo.png\"/></td></tr>";
        html += "<tr><td style=\"font-size:12px;margin:10px;\"><p>" + Texto.replace(System.getProperty("line.separator"), "</p><p>") + "</p><br></td></tr>";
        html += "</table></body></html>";
        return html;
    }

} //End of class

