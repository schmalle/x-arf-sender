package org.metams.xarf;

/* code mainly taken from an internet ressource */


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


public class Mailer
{

    private String m_userName = null;
    private String m_password = null;
    private String m_smtpServer = null;


    /**
     * constructor for the mailer class
     *
     * @param un
     * @param pw
     * @param smtpServer
     */
    public Mailer(String un, String pw, String smtpServer)
    {
        m_userName = un;
        m_password = pw;
        m_smtpServer = smtpServer;
    }


    /**
     *
     */
    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {

            return new PasswordAuthentication(m_userName, m_password);
        }
    }


    private class POPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(m_userName, m_password);
        }
    }


    /**
     * send a xard report to a dedicated destination
     *
     * @param to
     * @param messageText
     * @param structuredComplaint
     * @param log
     * @param ip
     * @param date
     * @param service
     * @return true on success, false on fail
     */
    public boolean sendARF(String to, String messageText, String structuredComplaint, String log, String ip, String date, String service)
    {
        // validity check
        if (m_userName == null)
            return false;


        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", m_smtpServer);
        properties.put("mail.smtp.auth", "true");


        Authenticator auth = new SMTPAuthenticator();

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, auth);

        try

        {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(m_userName));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("abuse report about " + ip + " - " + date + " [noreply] service: " + service);


            // set the necessary data for x-arf
            message.setHeader("X-ARF", "yes");
            message.setHeader("Auto-Submitted", "auto-generated");


            // Create a multipart message
            Multipart multipart = new MimeMultipart();


            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message (to abuse Team)
            messageBodyPart.setText(messageText);

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Create the structural  part
            BodyPart structureBodyPart = new MimeBodyPart();

            // Fill the message (to abuse Team)
            structureBodyPart.setText(structuredComplaint);

            // Set text message part
            multipart.addBodyPart(structureBodyPart);

            // Create the structural  part
            BodyPart logBodyPart = new MimeBodyPart();

            // Fill the message (to abuse Team)
            logBodyPart.setText(log);

            // Set text message part
            multipart.addBodyPart(logBodyPart);

            message.setContent(multipart);

            // EMail message
            Transport.send(message);
        }
        catch (Exception mex)
        {
            return false;
        }

        return true;
    }   // sendARF

}      // Mailer class