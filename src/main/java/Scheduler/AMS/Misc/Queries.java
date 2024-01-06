package Scheduler.AMS.Misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
//@NoArgsConstructor

/**
 * Retreive different column data from emails tables inside postgres AMS dB.
 * Created static variables FETCH_SERIALID,FETCH_EMAILID,FETCH_BODY,FETCH_SUBJECT,FETCH_SENTORNOT as string using lombok dependency,constructor are created using above tags.i.e.Getters,AllArgsConstructor.
 * FETCH_SERIALID fetches serial_id from api  table
 * FETCH_EMAILID  fetches to i.e.EmailID from emails table
 * FETCH_BODY fetches  body from emails table
 * FETCH_SUBJECT fetches subject from emails table
 * FETCH_SENTORNOT fetches sent(boolean type) from emails table
 */
public class Queries {

    public static final String FETCH_SERIALID   = "select serial_id from api";

    public static final String FETCH_EMAILID = "select \"to\" from emails";

    public static final String FETCH_BODY = "select body from emails";

    public static final String FETCH_SUBJECT = "select subject from emails";

    public static final String FETCH_SENTORNOT = "select sent from emails";


    /**
     * @param sent is status of the EmailId in which Email is sent or not.
     * @param to is EmailId of  the user.
     * @return update the status of sent to true when email is sent
     */
    public static String updateSent(boolean sent, String to)
    {
        return "UPDATE emails SET sent ='"+sent+"' WHERE  \"to\" ='"+to+"'";
    }

}
