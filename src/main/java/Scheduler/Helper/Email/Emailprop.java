package Scheduler.Helper.Email;

import Scheduler.Property.GetProperty;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.util.Properties;


public class Emailprop {

    /**
     * Adding properties of mail host,port,auth(from app.properties file) and creating session.
     * @return Session(i.e.Session provide the User to do activities(sending mail))
     * @throws IOException
     * (in case of data not found)
     */
    public Session Emailprop() throws IOException {
        try {
            GetProperty gp = new GetProperty();
            final String fromEmail = gp.getEmail(); //requires valid gmail id
            final String password = gp.getPass(); // correct password for gmail id
            Properties props = new Properties();
            props.put("mail.smtp.host", gp.getHost()); //SMTP Host
            props.put("mail.smtp.port", gp.getPort()); //TLS Port
            props.put("mail.smtp.auth", gp.getAuth()); //enable authentication
            props.put("mail.smtp.starttls.enable", gp.getTlsEnable()); //enable STARTTLS

            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getDefaultInstance(props, auth);
            return session;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}