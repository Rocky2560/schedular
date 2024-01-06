package Scheduler;

import lombok.SneakyThrows;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.python.util.PythonInterpreter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import java.io.*;
import java.util.Properties;

public class pythonexe implements Job{
    int iExitValue;
    String sCommandString;

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

    @SneakyThrows
    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {

//        public static void main(String[] args) throws IOException{
//try {

        shellscript testScript = new shellscript();
        testScript.runScript("/home/rocky/venv/eklog_user_info/bin/python3 /home/rocky/eklog_user_info/update_user_info.py");
        System.out.println("sucesss");

//    Process p = Runtime.getRuntime().exec("python3 /home/rocky/eklog_user_info/update_user_info.py");

//        Properties properties = System.getProperties();
//        properties.put("python.path", "/usr/bin/python3");
//        PythonInterpreter.initialize(System.getProperties(), properties, new String[0]);
//        PythonInterpreter interp = new PythonInterpreter();
//        interp.execfile("/home/rocky/eklog_user_info/update_user_info.py");
//}
//catch (Exception e)
//{
//    System.out.println(e.getMessage());
//}
        //
//        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//
//        String ret = in.readLine();
//
//        System.out.println("value is : "+ret);

    }
}
