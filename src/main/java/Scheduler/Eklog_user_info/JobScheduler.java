package Scheduler.Eklog_user_info;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.IOException;

/**
 * Created Job as pythonexe Class.
 *  Logger object is used to keep logs of the process.
 *  GetProperty object is used to retrieve properties from a file.
 * Created Trigger which consist of of scheduling time,it is retrieve from getproperty class
 * In case of Exception,message is send to MatterMost.
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
                            .withIntervalInSeconds(15)
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