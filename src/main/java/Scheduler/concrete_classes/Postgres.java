package Scheduler.concrete_classes;

import Scheduler.interfaces.DBMS;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Postgres extends DBMS {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(Scheduler.concrete_classes.Postgres.class);
    private String url;
    private String user;
    private String pass;
    private DBMS dbConnection;

    public Postgres(String url, String user, String pass){
        this.url = url;
        this.user = user;
        this.pass = pass;

    }


    /**
     * connect method is called to connect to dB
     * @return the connection parameters i.e.URL of the postgres,username and passphrase.
     * @throws IOException
     */
    @Override
    public Connection connect() throws IOException {
        return connect(url,user,pass);
    }

    /**
     * connectionclose method is called and it is Used to close the connection
     */
    @Override
    public void close() {
      close();
        }

    /**
     * Data is retrieve as Resultset.
     * @return method getData which run the query and give respective data.
     * @throws IOException
     */
    @Override
    public ResultSet getResultSet() throws IOException {
        return getData(connect());
    }

    /**
     * Call the method fetchCreatedAt in which parmaters is passed.
     * Parameters  consist of connection,respective Query to fetch data and column name of table from dB
     */
    @Override
    public ArrayList<Long> fetchCreatedAt() throws IOException, SQLException {
        return fetchCreatedAt(connect(), Scheduler.PMS.Misc.Queries.FETCH_TIME,"created_at");
    }


}
