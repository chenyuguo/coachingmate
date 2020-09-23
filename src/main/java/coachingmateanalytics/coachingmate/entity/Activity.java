package coachingmateanalytics.coachingmate.entity;

import java.io.Serializable;

/**
 * @Auther: Saul
 * @Date: 23/9/20 10:19
 * @Description:
 */
public class Activity implements Serializable {
    private String accessToken;
    private String data;

    public Activity(String accessToken, String data) {
        this.accessToken = accessToken;
        this.data = data;
    }

    public Activity() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
