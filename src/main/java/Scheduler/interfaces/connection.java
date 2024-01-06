package Scheduler.interfaces;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Consist of methods
 * connect is used for connecting to dB whereas close is used to close the connection
 * getResultSet is used to retrieve the data by executing the query
 * fetchCreatedAt,Fetchs created at from table
 */
public interface connection {
    Connection connect() throws IOException;

    void close() throws IOException;

    ResultSet getResultSet() throws IOException;

    ArrayList<Long> fetchCreatedAt() throws IOException, SQLException;

}
