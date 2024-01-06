package Scheduler.JobDetails;

import Scheduler.Helper.notification.sendMessage;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class jobBuilder {
    public static void scheduler(Class jobqueue,String name) throws IOException {
        try {
            JobDetail job = JobBuilder.newJob(jobqueue)
                    .withIdentity(name)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(20)
                            .repeatForever())
                    .build();

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
