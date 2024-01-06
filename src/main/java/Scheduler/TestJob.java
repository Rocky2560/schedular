package Scheduler;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;

public class TestJob implements Job {
    private final Logger log = Logger.getLogger(TestJob.class);

    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
//        new RedisConnection().parseMetrics();
    }

}
