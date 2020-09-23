package coachingmateanalytics.coachingmate.entity;


public class RequestToken {

	private long id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
				"id=" + id +
				", username='" + username + '\'' +
				", token='" + token + '\'' +
				", secret='" + secret + '\'' +
				'}';
	}
}
