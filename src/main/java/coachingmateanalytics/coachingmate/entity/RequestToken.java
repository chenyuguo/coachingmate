package coachingmateanalytics.coachingmate.entity;


public class RequestToken {
	private String userName;
	private String token;
	private String secret;

	public RequestToken() {
		super();
	}

	public RequestToken(String token, String secret) {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
