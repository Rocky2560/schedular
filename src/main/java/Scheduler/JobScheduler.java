package Scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

/**
 * Check User within the interval of 12 hours!
 */
public class JobScheduler {

//    private static Logger log = Logger.getLogger(Scheduler.PMS.Scheduler.JobScheduler.class);
    public static void main(String[] args) throws IOException {
//        String exp= "0 33 14 1/1 * ? *";
//        log.info("Starting Scheduler");
        try {

            JobDetail job = JobBuilder.newJob(pythonexe.class)
                    .withIdentity("shelljob")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(300)
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

        }
    }

}