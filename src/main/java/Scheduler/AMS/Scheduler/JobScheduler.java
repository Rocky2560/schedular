package Scheduler.AMS.Scheduler;

import Scheduler.AMS.sendEmail;
import Scheduler.Helper.notification.sendMessage;
import Scheduler.Property.GetProperty;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

/**
 * Created Job as sendEmail Class.
 *  Logger object is used to keep logs of the process.
 *  GetProperty object is used to retrieve properties from a file.
 * Created Trigger which consist of of scheduling time,it is retrieve from getproperty class
 * In case of Exception,message is send to MatterMost.
 * Send emails continuously within interval of 12 hours!
 */
public class JobScheduler {
    private static Logger log = Logger.getLogger(JobScheduler.class);

    public static void main(String[] args) throws IOException{

//        String exp= "0 33 14 1/1 * ? *";
        GetProperty gp = new GetProperty();
        log.info("Starting Scheduler");
        try {
            JobDetail job = JobBuilder.newJob(sendEmail.class)
                    .withIdentity("EmailJob")
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

        } catch (SchedulerException e) {
            e.printStackTrace();
            sendMessage.sendMessageToMattermost(e.getLocalizedMessage());
//            System.out.println("hello");
        }
    }

}