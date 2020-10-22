package coachingmateanalytics.coachingmate.entity;

import io.swagger.annotations.ApiModel;

@ApiModel
public class UserPartner {
    private long userId;
    private String username;
    private String userAccessToken;
    private String userAccessSecret;
    private String password;

    public String getUserAccessSecret() {
        return userAccessSecret;
    }

    public void setUserAccessSecret(String userAccessSecret) {
        this.userAccessSecret = userAccessSecret;
    }

    public UserPartner() {
        super();
    }

    public UserPartner(String username, String userAccessToken) {
        super();
        this.username = username;
        this.userAccessToken = userAccessToken;

    }
    public UserPartner(String username, String userAccessToken, String userAccessSecret) {
        super();
        this.username = username;
        this.userAccessToken = userAccessToken;
        this.userAccessSecret = userAccessSecret;

    }

    public UserPartner(long userId, String username, String userAccessToken, String userAccessSecret, String password) {
        this.userId = userId;
        this.username = username;
        this.userAccessToken = userAccessToken;
        this.userAccessSecret = userAccessSecret;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAccessToken() {
        return userAccessToken;
    }

    public void setUserAccessToken(String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "UserPartner [userId=" + userId + ", userName=" + username + ", userAccessToken=" + userAccessToken
                + ", userAccessSecret=" + userAccessSecret + "]";
    }

}