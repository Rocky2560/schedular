package Scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiMetrics {
    private String apiUid;
    private String publicKey;
    private int statusCode;
    private String ip;
    private String browser;
    private String browserType;
    private Double browserMajorVersion;
    private String deviceType;
    private String platform;
    private String platformVersion;
    private Long createdOn;
    private String responseType;
    private int doBR;
}