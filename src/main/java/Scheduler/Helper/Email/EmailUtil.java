package Scheduler.Helper.Email;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class EmailUtil {

    /**
     * Utility method to send simple HTML email
     * Consist of content-type,format,Content-Transfer-Encoding.
     * @param session(consist of User login details)
     * @param toEmail(address of EmailId in which Email is sent)
     * @param subject(subject of the email)
     * @param body(body of the email)
     */
    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("ekbana@example.com", "test"));

            msg.setReplyTo(InternetAddress.parse("no_reply@mail.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            Transport.send(msg);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

