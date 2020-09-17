package coachingmateanalytics.coachingmate.entity;

public class UserPartner {
	private long userId;
	private String userName;
	private String userAccessToken;
	private String userAccessSecret;

	public String getUserAccessSecret() {
		return userAccessSecret;
	}

	public void setUserAccessSecret(String userAccessSecret) {
		this.userAccessSecret = userAccessSecret;
	}

	public UserPartner() {
		super();
	}

	public UserPartner(String userName, String userAccessToken) {
		super();
		this.userName = userName;
		this.userAccessToken = userAccessToken;

	}
	public UserPartner(String userName, String userAccessToken, String userAccessSecret) {
		super();
		this.userName = userName;
		this.userAccessToken = userAccessToken;
		this.userAccessSecret = userAccessSecret;

	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccessToken() {
		return userAccessToken;
	}

	public void setUserAccessToken(String userAccessToken) {
		this.userAccessToken = userAccessToken;
	}
	  @Override
	    public String toString()
	    {
	        return "UserPartner [userId=" + userId + ", userName=" + userName + ", userAccessToken=" + userAccessToken
	                + ", userAccessSecret=" + userAccessSecret + "]";
	    }
	    

}
