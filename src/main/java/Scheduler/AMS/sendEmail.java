package Scheduler.AMS;

import Scheduler.AMS.Misc.Queries;
import Scheduler.Helper.Email.EmailUtil;
import Scheduler.Helper.Email.Emailprop;
import Scheduler.concrete_classes.Postgres;
import Scheduler.Property.GetProperty;
import Scheduler.factory.ConnectionFactory;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Retrieve the emailid's from dB and sent email to its respective address.
 * Emailprop is used to get email propertied i.e.headers,format.
 * ConnectionFactory is for creating object which give access to operation to Postgres dB(AMS).
 * Resulset consists of Emailid and respective email is sent to its address by making sent column in dB as True.
 * Check email is sent or not!If not,send email to respective address,with subject and body.
 * close  the Postgres connection
 */

public class sendEmail implements Job{
    public static final GetProperty getProperty = new GetProperty();
    private static Logger log = Logger.getLogger(sendEmail.class);
    @SneakyThrows
    public void execute(JobExecutionContext jExeCtx){
//    public static void main(String[] args) {

        try {

        Emailprop emailprop = new Emailprop();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Postgres psql = connectionFactory.getConnection("AMS");

            ResultSet resultSet = psql.getResultSet();

            while (resultSet.next()) {
                EmailUtil.sendEmail(emailprop.Emailprop(), resultSet.getString("to"), resultSet.getString("subject"),resultSet.getString("body"));
                String query = Queries.updateSent(true, resultSet.getString("to"));
                PreparedStatement pstmt = psql.connect().prepareStatement(query);
                pstmt.executeUpdate();
            }

            psql.close();
        }
//            End of code of emails

        catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage());
        }


    }
}
