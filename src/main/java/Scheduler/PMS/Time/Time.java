package Scheduler.PMS.Time;

import Scheduler.PMS.Misc.Queries;
import Scheduler.concrete_classes.Postgres;
import Scheduler.Property.GetProperty;
import Scheduler.connection.PostgresConnectionpms;
import Scheduler.factory.ConnectionFactory;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.util.ArrayList;

/**
 * created arraylist createdat which consist of user login session time
 * ConnectionFactory is for creating object which give access to operation to Postgres dB(PMS).
 * Checks users if login time  greater than 12 hours!If greater than 12 hours,clear the data fromm table.
 * Difference between current time and User login session time is calculated if greater than 12 hour.User is log out!
 * The Query is retrieved and execute from Queries class.
 * close  the Postgres connection
 *
 */
public class Time  implements Job{
    private static Logger log = Logger.getLogger(Time.class);
    private static long unixTimestamp;

    PostgresConnectionpms time = new PostgresConnectionpms();
    GetProperty gp = new GetProperty();

    private static long difference;


   public Time() throws IOException
   {
       this.difference = gp.getDifference();
   }

    @SneakyThrows
    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
//    public static void main(String[] args) throws SQLException {
        try
        {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Postgres psql = connectionFactory.getConnection("PMS");
//            log.info("Searching the User whose login is more than 12 hours");
        ArrayList<Long> createdat = psql.fetchCreatedAt();
//            System.out.println(createdat);
        unixTimestamp = Instant.now().getEpochSecond();

            for (int i = 0; i < createdat.size(); i++) {
                long diff = Math.abs(createdat.get(i) - unixTimestamp);
                if (diff > difference)
                {
                    String query = Queries.checkTime(createdat.get(i));
                    PreparedStatement pstmt = psql.connect().prepareStatement(query);
                    pstmt.executeUpdate();

                }
            }
            psql.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage());
        }


    }

}
