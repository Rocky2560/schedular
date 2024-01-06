package Scheduler.Property;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.File;

/**
 * Get Property from /home/rocky/Desktop/app.properties file
 * consist of postgres URL with its username and password.
 * consist of Email host,port,authentication,tls enabled or not!
 * consist of scheduletime,difference.
 */
public class GetProperty {
    public Config config = ConfigFactory.parseFile(new File("/home/rocky/Desktop/app.properties"));
    private  String email;
    private String host;
    private String pass;
    private int port;

    private String auth;
    private String tlsenable;
    private int scheduletime;
    private int difference;
    private String urlams;
    private String urlpms;
    private String postgres_user;
    private String postgres_pass;

    public GetProperty(){
        this.host = config.getString("host");
        this.pass = config.getString("pass");
        this.port = config.getInt("port");
        this.auth = config.getString("auth");
        this.tlsenable = config.getString("tlsenable");

        this.scheduletime = config.getInt("scheduletime");
        this.difference = config.getInt("difference");
        this.urlams = config.getString("urlams");
        this.urlpms = config.getString("urlpms");
        this.postgres_user = config.getString("user");

        this.postgres_pass = config.getString("password");
        this.email = config.getString("email");


    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getHost() {
        return host;
    }

    public String getAuth() {
        return auth;
    }

    public String getTlsEnable() {
        return tlsenable;
    }

    public Integer getPort() {
        return port;
    }

    public Integer getScheduletime() {
        return scheduletime;
    }

    public Integer getDifference() {
        return difference;
    }

    public  String getUrlams() {
        return urlams;
    }

    public String getUrlpms() {
        return urlpms;
    }

    public  String getPostgresUser() {
        return postgres_user;
    }

    public  String getPostgresPassword() {
        return postgres_pass;
    }

}
