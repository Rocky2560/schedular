package Scheduler.PMS.Misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
/**
 * Retreive data from tokens tables inside postgres PMS dB.
 * Created static variable fetch_time as string using lombok dependency,constructor are created using above tags.i.e.Getters,AllArgsConstructor.
 * FETCH_TIME fetches created_at from tokens table
 * checkTime method,deletes  the user which is logged in for more than 12 hours FROM tokens table.
 */
public class Queries {

    public static final String FETCH_TIME = "select created_at from tokens";

    public static String checkTime(Long createdat)
    {
        return "DELETE FROM tokens WHERE created_at ="+createdat;
    }

}
