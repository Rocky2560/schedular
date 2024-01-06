package Scheduler.interfaces;

import Scheduler.connection.PostgresConnection;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public abstract class DBMS implements connection{

    public Connection c = null;
    private static Logger log = Logger.getLogger(PostgresConnection.class);

    /**
     *  * Used  to provide connection to the Postgres dB
     * @param url, is address to connect to dB.
     * @param user is username to connect to dB.
     * @param pass is passphrase to connect to dB.
     * @return connection to dB.
     * @throws IOException
     */
    public Connection connect(String url,String user,String pass) throws IOException{
        try {
            this.c = DriverManager
                    .getConnection(url ,
                           user, pass);
            return this.c;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            log.error(throwables.getMessage());
        }
        return null;
    }

    /**
     * Closing the connection of Postgres AMS
     */
    public void close()
    {
        try {
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
    public ArrayList<Long> fetchCreatedAt(Connection conn,String queries,String column) throws IOException,SQLException{
        Statement statement = null;
        ArrayList<Long> createdlist = new ArrayList<>();
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(queries);
            while (resultSet.next()) {
                createdlist.add(resultSet.getLong(column));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return createdlist;
    }

    /**
     * Statement is created and the query is executed and result is saved in resultset.
     * Retrieve the emailid's with its respective body,subject in which Email needs to be sent.
     * @param conn is used for connecting to dB.
     * @return the emailid's with its respective body,subject
     */
    public ResultSet getData(Connection conn) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from emails where sent=false");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }




}
