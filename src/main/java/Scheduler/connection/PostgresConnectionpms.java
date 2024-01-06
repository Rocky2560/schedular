package Scheduler.connection;

import Scheduler.PMS.Misc.Queries;
import Scheduler.Property.GetProperty;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class PostgresConnectionpms {
    public Connection c = null;
    private Queries queries = new Queries();
    private GetProperty getProperty = new GetProperty();
    private static Logger log = Logger.getLogger(PostgresConnectionpms.class);
    /**
     * Connecting to postgres(10.10.5.30 server)  with pms dB
     * URL parameter is retreive from property file,same for username and password
     *Provides connection to postgres DB
     */
    public PostgresConnectionpms() throws IOException {
        try {
            this.c = DriverManager
//                    .getConnection("jdbc:postgresql://localhost:5432/api_management_system",
                    .getConnection(getProperty.getUrlpms()  ,
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
     * created arraylist createdlist to put created_at from Tokens table
     * Retrieve the query  from Queries class and execute it
     * Fetch createdat from tokens table
     * @return arraylist(i.e.List of CreatedAt)
     */
    public ArrayList<Long> fetchCreatedAt() throws IOException,SQLException{
        Statement statement = null;
        ArrayList<Long> createdlist = new ArrayList<>();
        try {
            statement = this.c.createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.FETCH_TIME);
            while (resultSet.next()) {
                createdlist.add(resultSet.getLong("created_at"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return createdlist;
    }
}