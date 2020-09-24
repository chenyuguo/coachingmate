package coachingmateanalytics.coachingmate.entity;


public class RequestToken {

	private String username;
	private String token;
	private String secret;

	public RequestToken() {
		super();
	}

	public RequestToken(String username, String token, String secret) {
		super();
		this.username = username;
		this.token = token;
		this.secret = secret;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "RequestToken{" +
				"username='" + username + '\'' +
				", token='" + token + '\'' +
				", secret='" + secret + '\'' +
				'}';
	}
}
