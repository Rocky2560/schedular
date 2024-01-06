package Scheduler.factory;

import Scheduler.concrete_classes.Postgres;
import Scheduler.Property.GetProperty;

public class ConnectionFactory {

    private GetProperty getProperty = new GetProperty();

    /**
     *When Connectionclass is equals to AMS or PMS,it created object of its respective dB.Otherwise,return null.
     * @param ConnectionClass consist of dB name that is used to create object to connect to its respective dB & access it.
     * @return object of its respective dB by matching the condition in IF statement.
     */
    public Postgres getConnection(String ConnectionClass) {
        if (ConnectionClass == null) {
            return null;
        }
        if (ConnectionClass.equalsIgnoreCase("AMS")) {
            return new Postgres(getProperty.getUrlams(),getProperty.getPostgresUser(), getProperty.getPostgresPassword());
        }
        if (ConnectionClass.equalsIgnoreCase("PMS")) {
            return new Postgres(getProperty.getUrlpms(),getProperty.getPostgresUser(), getProperty.getPostgresPassword());
        }
        return null;
    }
}
