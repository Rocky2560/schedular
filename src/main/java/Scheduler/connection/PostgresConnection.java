package Scheduler.connection;

import Scheduler.AMS.Misc.Queries;
import Scheduler.PMS.Time.Time;
import Scheduler.Property.GetProperty;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class PostgresConnection {
    public Connection c = null;
//    private Queries queries = new Queries();


    private static Logger log = Logger.getLogger(PostgresConnection.class);
    private GetProperty getProperty = new GetProperty();

    /**
     * Connecting to postgres(10.10.5.30 server) with ams2 dB
     * URL parameter is retreive from property file,same for username and password
     *Provides connection to postgres DB
     */
    public PostgresConnection() throws IOException{
        try {
            this.c = DriverManager
                    .getConnection(getProperty.getUrlams()  ,
                            getProperty.getPostgresUser(), getProperty.getPostgresPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            log.error(throwables.getMessage());
        }
    }

    /**
     * Closing the connection of Postgres AMS
     */
    public void connectionClose()
    {
        try {
//            this.c.commit();
            this.c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * created arraylist apilist to put serial_id from API table
     * Retrieve the query  from Queries class and execute it
     * Fetches the serial_id from API table
     * @return apiList(i.e.List of serialID)
     */
//    public ArrayList<String> fetchApiList() throws IOException ,SQLException {
//        Statement statement = null;
//        ArrayList<String> apiList = new ArrayList<>();
//        try {
//            statement = this.c.createStatement();
//            ResultSet resultSet = statement.executeQuery(Queries.FETCH_SERIALID);
//            while (resultSet.next()) {
//                apiList.add(resultSet.getString("serial_id"));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return apiList;
//    }

    /**
     * created arraylist emaillist to put EmailID from Email table
     * Retrieve the query  from Queries class and execute it.
     * Fetch emailID from emails table
     * @return emailslist(i.e.list of emails)
     */


    public ArrayList<String> emailList() throws IOException ,SQLException{
        Statement statement = null;
        ArrayList<String> emaillist = new ArrayList<>();
        try {
            statement = this.c.createStatement();
            ResultSet resultSet = statement.executeQuery("select \"to\" from emails");
            System.out.println(resultSet);
            while (resultSet.next()) {
                emaillist.add(resultSet.getString("to"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return emaillist;
    }

    /**
     * created arraylist emaillist to put body from Email
     * Retrieve the query  from Queries class and execute it.
     * Fetch body from emails table
     * @return body(i.e.list of body message)
     */
    public ArrayList<String> bodyList() throws IOException ,SQLException{
        Statement statement = null;
        ArrayList<String> bodylist = new ArrayList<>();

        try {
            statement = this.c.createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.FETCH_BODY);
            while (resultSet.next()) {

                bodylist.add(resultSet.getString("body"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bodylist;
    }

    /**
    * created arraylist subjectlist to put subject from Email
    * Retrieve the query  from Queries class and execute it.
     * Fetch subject from emails table
     * @return subject(i.e.List of  subjects)
     */
    public ArrayList<String> subjectList() throws IOException ,SQLException{
        Statement statement = null;
        ArrayList<String> subjectlist = new ArrayList<>();

        try {
            statement = this.c.createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.FETCH_SUBJECT);
            while (resultSet.next()) {

                subjectlist.add(resultSet.getString("subject"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subjectlist;
    }

    /**
     * Created arraylist emailcheck to put status of email  is sent or not
     *  Retrieve the query  from Queries class and execute it.
     * Fetch Status of email sent or not from emails table
     * @return emailcheck
     */
    public ArrayList<Boolean> emailCheck() throws IOException ,SQLException{
        Statement statement = null;
        ArrayList<Boolean> emailcheck = new ArrayList<>();
        try {
            statement = this.c.createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.FETCH_SENTORNOT);
            while (resultSet.next()) {
                emailcheck.add(resultSet.getBoolean("sent"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return emailcheck;
    }


}