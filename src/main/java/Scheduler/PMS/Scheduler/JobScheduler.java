package Scheduler.PMS.Scheduler;

import Scheduler.Helper.notification.sendMessage;
import Scheduler.PMS.Time.Time;
import Scheduler.Property.GetProperty;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

/**
 *  * Created Job as Time Class.
 *  Logger object is used to keep logs of the process.
 *  GetProperty object is used to retrieve properties from a file.
 *  * Created Trigger which consist of of scheduling time,it is retrieve from getproperty class
 *  * In case of Exception,message is send to MatterMost.
 *  * * Check User within the interval of 12 hours!
 */
public class JobScheduler {
    private static Logger log = Logger.getLogger(JobScheduler.class);
    private static int time;
    GetProperty gp = new GetProperty();

    public JobScheduler() throws IOException {
        this.time = gp.getScheduletime();
    }
//    private static Logger log = Logger.getLogger(Scheduler.PMS.Scheduler.JobScheduler.class);
    public static void main(String[] args) throws IOException {
//        String exp= "0 33 14 1/1 * ? *";
//        log.info("Starting Scheduler");
        try {

            GetProperty gp = new GetProperty();
            JobDetail job = JobBuilder.newJob(Time.class)
                    .withIdentity("TimeJob")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(20)
                            .repeatForever())
                    .build();

//                        Trigger trigger = TriggerBuilder.newTrigger()
//                    .withSchedule(CronScheduleBuilder.cronSchedule(exp))
//                    .build();

            //schedule the job
            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();
            sch.start();

            sch.scheduleJob(job, trigger);
//            log.info("Succesfully Run");
        } catch (SchedulerException e) {
            e.printStackTrace();
            sendMessage.sendMessageToMattermost("Scheduler Error:"+ e.getLocalizedMessage());

            //            Logger.getLogger("Scheduler Error:"+e.getMessage());
//            Logger.getLogger(e.getMessage());

            log.error(e.getMessage());

        }
    }

}