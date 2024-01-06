package Scheduler.Eklog_user_info;

import Scheduler.shellscript;
import lombok.SneakyThrows;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;

public class pythonexe implements Job{
    int iExitValue;
    String sCommandString;

    /**
     *runScript is used to run command in case of failure execution failed or permission denied.
     * @param command is provided from execute method to run.
     */
    public void runScript(String command){
        sCommandString = command;
        CommandLine oCmdLine = CommandLine.parse(sCommandString);
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        oDefaultExecutor.setExitValue(0);
        try {
            iExitValue = oDefaultExecutor.execute(oCmdLine);
        } catch (ExecuteException e) {
            System.err.println("Execution failed.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("permission denied.");
            e.printStackTrace();
        }
    }

    /**
     *Create object of shellscript
     *pass that object with command as parameter to execute.
     * command consist of its virtual environment file path and python file to be executed.
     */
    @SneakyThrows
    public void execute(JobExecutionContext jExeCtx) {

//        public static void main(String[] args) throws IOException{
    try {

            shellscript testScript = new shellscript();
            testScript.runScript("/home/rocky/venv/eklog_user_info/bin/python3 /home/rocky/eklog_user_info/update_user_info.py");
        }
    catch (Exception e)
    {
            System.out.println(e.getMessage());
         }

    }
}
