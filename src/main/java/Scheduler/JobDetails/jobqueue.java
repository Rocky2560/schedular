package Scheduler.JobDetails;
import Scheduler.AMS.sendEmail;

import java.io.IOException;

public class jobqueue {

    public static void main(String[] args) throws IOException {
        jobBuilder.scheduler(sendEmail.class,"AMS");
        jobBuilder.scheduler(sendEmail.class,"AMS");
        jobBuilder.scheduler(sendEmail.class,"AMS");
    }
}
